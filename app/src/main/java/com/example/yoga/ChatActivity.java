package com.example.yoga;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {

    private static final String TAG = "ChatActivity";
    private static final int MAX_RETRIES = 3;
    private RecyclerView rvChat;
    private ChatAdapter adapter;
    private List<ChatMessage> messages = new ArrayList<>();
    private int retryCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Initialize RecyclerView
        rvChat = findViewById(R.id.rvChat);
        adapter = new ChatAdapter(messages);
        rvChat.setLayoutManager(new LinearLayoutManager(this));
        rvChat.setAdapter(adapter);

        // Setup send button listener
        findViewById(R.id.btnSend).setOnClickListener(v -> {
            String message = ((EditText) findViewById(R.id.etMessage)).getText().toString();
            if (!message.isEmpty()) {
                addMessage(message, true);
                getAIResponse(message);
                ((EditText) findViewById(R.id.etMessage)).setText("");
            }
        });
    }

    private void getAIResponse(String query) {
        HuggingFaceAPI api = RetrofitClient.getClient().create(HuggingFaceAPI.class);

        JsonObject body = new JsonObject();
        body.addProperty("inputs", "Answer concisely under 50 words as yoga expert: " + query);

        JsonObject parameters = new JsonObject();
        parameters.addProperty("max_length", 80); // Shorter responses
        parameters.addProperty("temperature", 0.3); // Less random answers
        parameters.addProperty("repetition_penalty", 1.5); // Avoid repetition
        body.add("parameters", parameters);

        // Add memory optimization
        JsonObject options = new JsonObject();
        options.addProperty("wait_for_model", true);
        options.addProperty("use_cache", false); // Disable cache to reduce memory
        body.add("options", options);

        Log.d(TAG, "Sending API request: " + body.toString());

        api.queryModel(body).enqueue(new Callback<JsonArray>() { // Matches interface
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful() && response.body() != null) {
                    retryCount = 0; // Reset retry counter on success
                    Log.d(TAG, "API response received: " + response.body().toString());

                    try {
                        JsonArray responseArray = response.body();
                        if (responseArray.size() > 0) {
                            JsonObject firstItem = responseArray.get(0).getAsJsonObject();
                            String reply = firstItem.get("generated_text").getAsString();
                            addMessage(reply, false);
                        } else {
                            Log.w(TAG, "Empty response array from API");
                            addMessage("Empty response from API", false);
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Response parsing error: " + e.getMessage(), e);
                        addMessage("Parse error: " + e.getMessage(), false);
                    }
                } else {
                    try {
                        String errorBody = response.errorBody() != null ?
                                response.errorBody().string() : "Unknown error";
                        Log.e(TAG, "API error response [" + response.code() + "]: " + errorBody);
                        handleError(errorBody);
                    } catch (IOException e) {
                        Log.e(TAG, "Error handling failed response: " + e.getMessage());
                        addMessage("HTTP Error: " + response.code(), false);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e(TAG, "API call failed: " + t.getMessage(), t);
                addMessage("Connection Error: " + t.getMessage(), false);
            }
        });
    }

    private void handleError(String errorMessage) {
        if (errorMessage.contains("CUDA out of memory") && retryCount < MAX_RETRIES) {
            long delay = (long) Math.pow(2, retryCount) * 1000; // Exponential backoff
            retryCount++;

            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                getAIResponse(messages.get(messages.size()-2).getContent());
            }, delay);
        } else {
            addMessage("Server overloaded. Please try simpler questions", false);
            retryCount = 0;
        }
    }

    private void addMessage(String text, boolean isUser) {
        runOnUiThread(() -> {
            messages.add(new ChatMessage(isUser ? "user" : "assistant", text));
            adapter.notifyItemInserted(messages.size() - 1);
            rvChat.smoothScrollToPosition(messages.size() - 1);
        });
    }
}
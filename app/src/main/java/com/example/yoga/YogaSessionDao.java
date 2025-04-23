package com.example.yoga;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface YogaSessionDao {

    @Insert
    void insert(YogaSession session);

    @Query("SELECT * FROM YogaSession ORDER BY id DESC")
    List<YogaSession> getAllSessions();
}

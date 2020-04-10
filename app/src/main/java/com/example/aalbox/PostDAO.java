package com.example.aalbox;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PostDAO {

    @Insert
    void addPost(Post post);

    @Update
    void updatePost(Post post);


    @Query("SELECT * from Post_table ORDER BY id DESC")
    LiveData<List<Post>> getAllPost();


}

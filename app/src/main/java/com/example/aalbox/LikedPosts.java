package com.example.aalbox;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Liked_post_table")
public class LikedPosts {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "mobile_id")
    private String mobileId;

    @ColumnInfo (name = "post_id")
    private int postId;

}

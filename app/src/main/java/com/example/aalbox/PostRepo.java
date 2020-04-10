package com.example.aalbox;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;

public class PostRepo {

    private PostDAO postDao;
    private LiveData<List<Post>> allPost;

    public PostRepo(Application application){
        PostDatabase database = PostDatabase.getInstance(application);
        postDao = database.postDAO();
        allPost = postDao.getAllPost();

    }

    public void insert(Post post){
        new InsertPostAsync(postDao).execute(post);
    }
    public LiveData<List<Post>> getAllPost(){
        return allPost;

    }
    public void update(Post post){new UpdatePostAsync(postDao).execute(post);}

    private static class InsertPostAsync extends AsyncTask<Post, Void, Void> {
        private PostDAO postDAO;
        private InsertPostAsync(PostDAO personDao){
            this.postDAO = personDao;
        }

        @Override
        protected Void doInBackground(Post... post) {
            postDAO.addPost(post[0]);
            return null;
        }
    }

    private static class UpdatePostAsync extends AsyncTask<Post, Void, Void> {
        private PostDAO postDAO;
        private UpdatePostAsync(PostDAO personDao){
            this.postDAO = personDao;
        }

        @Override
        protected Void doInBackground(Post... post) {
            postDAO.updatePost(post[0]);
            return null;
        }
    }



}

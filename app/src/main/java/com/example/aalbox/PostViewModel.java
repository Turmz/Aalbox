package com.example.aalbox;

import android.app.Application;
import android.util.Log;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class PostViewModel extends AndroidViewModel {

    private PostRepo repo;
    LiveData<List<Post>> postLiveData;


    public PostViewModel(@NonNull Application application) {
        super(application);
        repo = new PostRepo(application);
        postLiveData = repo.getAllPost();
    }

    public void insert(Post post){
        repo.insert(post);
    }

    public void update(Post post){
        Log.i("OPDATER","OPDATERERERERER"+post.getLike());
        repo.update(post);
    }

    public LiveData<List<Post>> getAllPost(){
        return postLiveData;
    }
}

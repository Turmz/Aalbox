package com.example.aalbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PostViewModel postViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final PostAdapter postAdapter = new PostAdapter();
        recyclerView.setAdapter(postAdapter);

        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        postViewModel.getAllPost().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                postAdapter.setPosts(posts);

            }
        });

        postAdapter.setOnItemClickListener(new PostAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Post post, boolean isLike) {
                Log.i("HEJ", "DER ER KLIKKET");
                if (isLike){
                    Post newPost = new Post(post.getLocation(), post.getCategory(), post.getDescription(), post.getLike()+1, post.getDislike());
                    newPost.setId(post.getId());
                    postViewModel.update(newPost);
                } else {
                    Post newPost = new Post(post.getLocation(), post.getCategory(), post.getDescription(), post.getLike(), post.getDislike()+1);
                    newPost.setId(post.getId());
                    postViewModel.update(newPost);
                }
                postAdapter.notifyDataSetChanged();

            }
        });
    }

    public void changeToAddPostView(View view){
        Intent intent = new Intent(this, AddPostActivity.class);
        startActivity(intent);
    }
}

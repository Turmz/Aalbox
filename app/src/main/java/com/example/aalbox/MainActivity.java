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
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;
/*
import com.example.aalbox.fragments.FeedFragment;
import com.example.aalbox.fragments.MapFragment;
*/

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    private PostViewModel postViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(com.example.aalbox.fragments.FeedFragment.newInstance("", ""));

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

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_feed:
                            openFragment(com.example.aalbox.fragments.FeedFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_map:
                            openFragment(com.example.aalbox.fragments.MapFragment.newInstance("", ""));
                            return true;
                    }
                    return false;
                }
            };

    public void changeToAddPostView(View view){
        Intent intent = new Intent(this, AddPostActivity.class);
        startActivity(intent);
    }
}

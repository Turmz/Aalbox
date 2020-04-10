package com.example.aalbox;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    private List<Post> posts = new ArrayList<>();
    public OnItemClickListener clickListener;

    public void setPosts(List<Post> posts){
        this.posts = posts;
        notifyDataSetChanged();
    }


    public interface OnItemClickListener{
        void onItemClicked(Post post, boolean isLike);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_row, parent, false);
        return new PostHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        Post currentPost = posts.get(position);
        holder.textViewLocation.setText(currentPost.getLocation());
        holder.textViewCategory.setText(currentPost.getCategory());
        holder.textViewDescription.setText(currentPost.getDescription());
        holder.textViewLike.setText(String.valueOf(currentPost.getLike()));
        holder.textViewDislike.setText(String.valueOf(currentPost.getDislike()));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder {

        private TextView textViewLocation;
        private TextView textViewCategory;
        private TextView textViewDescription;
        private TextView textViewLike;
        private TextView textViewDislike;
        private ImageButton btnLike;
        private ImageButton btnDislike;


        public PostHolder(@NonNull View itemView) {
            super(itemView);
            textViewLocation = itemView.findViewById(R.id.text_view_location);
            textViewCategory = itemView.findViewById(R.id.text_view_category);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewLike = itemView.findViewById(R.id.text_view_like);
            textViewDislike = itemView.findViewById(R.id.text_view_dislike);
            btnLike = itemView.findViewById(R.id.btn_like);
            btnDislike = itemView.findViewById(R.id.btn_dislike);

            btnDislike.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (clickListener != null && position!=RecyclerView.NO_POSITION){
                        clickListener.onItemClicked(posts.get(position), false);
                    }
                }
            });

            btnLike.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (clickListener != null && position!=RecyclerView.NO_POSITION){
                        clickListener.onItemClicked(posts.get(position), true);
                    }
                }
            });
        }
    }
}

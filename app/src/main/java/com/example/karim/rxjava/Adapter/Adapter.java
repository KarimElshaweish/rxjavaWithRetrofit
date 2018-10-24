package com.example.karim.rxjava.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.karim.rxjava.Model.Post;
import com.example.karim.rxjava.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context _ctx;
    private List<Post>postList;

    public Adapter(Context _ctx, List<Post> postList) {
        this._ctx = _ctx;
        this.postList = postList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());

        View view=layoutInflater.inflate(R.layout.item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder viewHolder, int i) {
            viewHolder.txtTitle.setText(postList.get(i).getTitle());
            viewHolder.txtContent.setText(postList.get(i).getBody());
            viewHolder.txtAuthor.setText(String.valueOf(postList.get(i).getUserId()));
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle,txtContent,txtAuthor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAuthor=itemView.findViewById(R.id.txt_Author);
            txtContent=itemView.findViewById(R.id.txt_content);
            txtTitle=itemView.findViewById(R.id.txt_title);
        }
    }
}

package com.example.karim.rxjava.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.karim.rxjava.R;

public class PostViewHolder extends RecyclerView.ViewHolder {
    TextView txtTitle,txtContent,txtAuthor;
    public PostViewHolder(@NonNull View itemView) {
        super(itemView);

        txtTitle=itemView.findViewById(R.id.txt_title);
        txtAuthor=itemView.findViewById(R.id.txt_Author);
        txtContent=itemView.findViewById(R.id.txt_content);
    }
}

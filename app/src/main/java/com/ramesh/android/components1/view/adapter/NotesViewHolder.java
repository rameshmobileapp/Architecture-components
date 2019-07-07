package com.ramesh.android.components1.view.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ramesh.android.components1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txt_title)
    TextView txtTittle;

    @BindView(R.id.txt_note)
    TextView txtNote;

    @BindView(R.id.img_edit)
    ImageView imgEdit;

    @BindView(R.id.img_delete)
    ImageView imgDelete;


    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }
}

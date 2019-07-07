package com.ramesh.android.components1.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ramesh.android.components1.R;
import com.ramesh.android.components1.model.data.local.model.Notes;
import com.ramesh.android.components1.view.activity.notes.NotesActivity;
import com.ramesh.android.components1.view.callback.GetNotes;

import java.util.List;

import static com.ramesh.android.components1.utils.AppConstant.ACTION_DELETE;
import static com.ramesh.android.components1.utils.AppConstant.ACTION_EDIT;

public class NotesAdapter extends RecyclerView.Adapter<NotesViewHolder> {

    private Context context;
    private List<Notes> notesList;
    private GetNotes getNotes;

    public NotesAdapter(Context context, List<Notes> notesList, GetNotes getNotes) {
        this.context = context;
        this.notesList = notesList;
        this.getNotes = getNotes;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        try {
            Notes notes = notesList.get(position);
            holder.txtTittle.setText("Tittle : " + notes.getId());
            holder.txtNote.setText(notes.getNotes());
            holder.imgEdit.setOnClickListener(v -> {
                getNotes.getNotes(notes, ACTION_EDIT);
            });

            holder.imgDelete.setOnClickListener(v -> {
                getNotes.getNotes(notes, ACTION_DELETE);
            });


        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public void setNotesList(List<Notes> notesList) {
        this.notesList = notesList;
        notifyDataSetChanged();
    }
}

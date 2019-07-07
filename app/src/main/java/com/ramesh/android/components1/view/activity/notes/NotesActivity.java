package com.ramesh.android.components1.view.activity.notes;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ramesh.android.components1.R;
import com.ramesh.android.components1.model.data.local.model.Notes;
import com.ramesh.android.components1.view.adapter.NotesAdapter;
import com.ramesh.android.components1.view.base.BaseActivity;
import com.ramesh.android.components1.view.callback.GetNotes;
import com.ramesh.android.components1.view.callback.UserNotes;
import com.ramesh.android.components1.viewmodel.NotesViewModel;
import com.ramesh.android.components1.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ramesh.android.components1.utils.AppConstant.ACTION_CREATE;
import static com.ramesh.android.components1.utils.AppConstant.ACTION_EDIT;

public class NotesActivity extends BaseActivity implements UserNotes, GetNotes {

    private static final String TAG = "NotesActivity";

    @BindView(R.id.img_add_note)
    ImageView imgAddNotes;

    @Inject
    ViewModelProviderFactory factory;

    NotesViewModel viewModel;

    @BindView(R.id.rec_view)
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        setUnBinder(ButterKnife.bind(this));
        getActivityComponent().inject(this);
        viewModelInitialization();
    }

    @OnClick(R.id.img_add_note)
    public void addNotes() {
        showDialog(ACTION_CREATE, new Notes("Tittle", ""), this);
    }


    @Override
    public void viewModelInitialization() {
        viewModel = ViewModelProviders.of(this, factory).get(NotesViewModel.class);

        viewModel.getNotesLiveData().observe(this, notes -> {
            NotesAdapter notesAdapter = new NotesAdapter(NotesActivity.this, notes, NotesActivity.this);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.addItemDecoration(new DividerItemDecoration(NotesActivity.this, LinearLayoutManager.VERTICAL));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(notesAdapter);
        });
    }


    @Override
    public void notes(String userAction, Notes notes) {
        if (notes.getNotes().isEmpty())
            showMessage(getResources().getString(R.string.notes_empty));
        else if (userAction.equalsIgnoreCase(ACTION_CREATE))
            viewModel.insertNotes(notes);
        else if (userAction.equalsIgnoreCase(ACTION_EDIT))
            viewModel.updateNotes(notes);
    }

    @Override
    public void getNotes(Notes notes, String actionTag) {
        if (actionTag.equalsIgnoreCase(ACTION_EDIT))
            showDialog(ACTION_EDIT,notes, this);
        else
            viewModel.deleteNotes(notes);
    }
}

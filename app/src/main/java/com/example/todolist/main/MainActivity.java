package com.example.todolist.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.adapter.Note;
import com.example.todolist.adapter.NotesAdapter;
import com.example.todolist.R;
import com.example.todolist.note.CreateNoteActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

	private RecyclerView rvNotes;
	private FloatingActionButton btnAddNote;
	private NotesAdapter notesAdapter;
	private MainViewModel mainViewModel;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

		initViews();

		notesAdapter = new NotesAdapter();
		rvNotes.setAdapter(notesAdapter);

		mainViewModel.getNotes().observe(
				this,
				notes -> notesAdapter.setNotes(notes)
		);

		ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
				new ItemTouchHelper.SimpleCallback(
						0,
						ItemTouchHelper.LEFT
				) {
					@Override
					public boolean onMove(
							@NonNull RecyclerView recyclerView,
							@NonNull RecyclerView.ViewHolder viewHolder,
							@NonNull RecyclerView.ViewHolder target
					) {
						return false;
					}

					@Override
					public void onSwiped(
							@NonNull RecyclerView.ViewHolder viewHolder,
							int direction
					) {
						int position = viewHolder.getAdapterPosition();
						Note note = notesAdapter.getNotes().get(position);

						mainViewModel.delete(note);
					}
				});

		btnAddNote.setOnClickListener(v -> addNote());
		itemTouchHelper.attachToRecyclerView(rvNotes);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	private void addNote() {
		Intent intent = CreateNoteActivity.newIntent(MainActivity.this);
		startActivity(intent);
	}

	private void initViews() {
		rvNotes = findViewById(R.id.rvNotes);
		btnAddNote = findViewById(R.id.btnAddNote);
	}
}
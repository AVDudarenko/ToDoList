package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

	private RecyclerView rvNotes;
	private FloatingActionButton btnAddNote;
	private NotesAdapter notesAdapter;
	private final Database database = Database.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();

		notesAdapter = new NotesAdapter();
		notesAdapter.setOnNoteClickListener(note -> {
			database.delete(note.getId());
			showNotes();
		});
		rvNotes.setAdapter(notesAdapter);

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
						database.delete(notesAdapter.getNotes().get(position).getId());
						showNotes();
					}
				});

		btnAddNote.setOnClickListener(v -> {
			addNote();
		});
		itemTouchHelper.attachToRecyclerView(rvNotes);
	}

	@Override
	protected void onResume() {
		super.onResume();
		showNotes();
	}

	private void addNote() {
		Intent intent = SaveNoteActivity.newIntent(MainActivity.this);
		startActivity(intent);
	}

	private void initViews() {
		rvNotes = findViewById(R.id.rvNotes);
		btnAddNote = findViewById(R.id.btnAddNote);
	}

	private void showNotes() {
		notesAdapter.setNotes(database.getNotes());
	}
}
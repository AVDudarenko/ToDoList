package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

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
		rvNotes.setAdapter(notesAdapter);

		btnAddNote.setOnClickListener(v -> {
			addNote();
		});
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
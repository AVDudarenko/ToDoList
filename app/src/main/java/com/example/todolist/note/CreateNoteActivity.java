package com.example.todolist.note;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.todolist.R;
import com.example.todolist.adapter.Note;


public class CreateNoteActivity extends AppCompatActivity {

	private EditText etNoteText;

	private RadioButton rbLowPriority;
	private RadioButton rbMediumPriority;
	private Button btnSave;
	private CreateNoteViewModel createNoteViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_note);
		createNoteViewModel = new ViewModelProvider(this).get(CreateNoteViewModel.class);
		createNoteViewModel.getShouldCloseScreen().observe(
				this,
				shouldClose -> {
					if (shouldClose) {
						finish();
					}
				}
		);
		initViews();
		btnSave.setOnClickListener(v -> {
			saveNewNote();
		});

	}

	private void saveNewNote() {

		String text = etNoteText.getText().toString().trim();
		int priority = getPriority();
		Note note = new Note(text, priority);

		if (text.isEmpty()) {
			Toast.makeText(this, R.string.empty_note_field, Toast.LENGTH_SHORT).show();
		} else {
			createNoteViewModel.saveNote(note);
		}

	}

	private int getPriority() {

		int priority;
		if (rbLowPriority.isChecked()) {
			priority = 0;
		} else if (rbMediumPriority.isChecked()) {
			priority = 1;
		} else {
			priority = 2;
		}

		return priority;
	}

	private void initViews() {
		etNoteText = findViewById(R.id.etNoteText);
		rbLowPriority = findViewById(R.id.rbLowPriority);
		rbMediumPriority = findViewById(R.id.rbMediumPriority);
		btnSave = findViewById(R.id.btnSave);
	}

	public static Intent newIntent(Context context) {
		return new Intent(context, CreateNoteActivity.class);
	}

}
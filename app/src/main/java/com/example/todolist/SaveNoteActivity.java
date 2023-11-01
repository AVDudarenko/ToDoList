package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class SaveNoteActivity extends AppCompatActivity {

	private EditText etNoteText;

	private RadioButton rbLowPriority;
	private RadioButton rbMediumPriority;
	private Button btnSave;
	private NoteDataBase noteDataBase;

	private Handler handler = new Handler(Looper.getMainLooper());

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_note);
		noteDataBase = NoteDataBase.getInstance(getApplication());
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
			Thread thread = new Thread(() -> {
				noteDataBase.notesDAO().add(note);
				handler.post(this::finish);
			});
			thread.start();
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
		return new Intent(context, SaveNoteActivity.class);
	}

}
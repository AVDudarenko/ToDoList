package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class SaveNoteActivity extends AppCompatActivity {

	private EditText etNoteText;

	private RadioButton rbLowPriority;
	private RadioButton rbMediumPriority;
	private Button btnSave;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_note);
		initViews();
		btnSave.setOnClickListener(v -> {
			saveNewNote();
		});

	}

	private void saveNewNote() {

		String text = etNoteText.getText().toString().trim();
		int priority = getPriority();

		if (text.isEmpty()) {
			Toast.makeText(this, R.string.empty_note_field, Toast.LENGTH_SHORT).show();
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
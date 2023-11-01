package com.example.todolist.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todolist.adapter.Note;
import com.example.todolist.db.NoteDataBase;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

	private NoteDataBase noteDataBase;

	public MainViewModel(@NonNull Application application) {
		super(application);
		noteDataBase = NoteDataBase.getInstance(application);
	}

	/**
	 * method for get list of notes from DB
	 *
	 * @return live data which contains list of notes
	 */
	public LiveData<List<Note>> getNotes() {
		return noteDataBase.notesDAO().getNotes();
	}

	public void delete(Note note) {
		Thread thread = new Thread(() -> {
			noteDataBase.notesDAO().delete(note.getId());
		});
		thread.start();
	}
}

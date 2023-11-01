package com.example.todolist.note;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.todolist.adapter.Note;
import com.example.todolist.db.NoteDataBase;
import com.example.todolist.db.NotesDAO;

public class CreateNoteViewModel extends AndroidViewModel {

	private NotesDAO notesDAO;
	private MutableLiveData<Boolean> shouldCloseScreen = new MutableLiveData<>();

	public CreateNoteViewModel(@NonNull Application application) {
		super(application);

		notesDAO = NoteDataBase.getInstance(application).notesDAO();
	}

	public MutableLiveData<Boolean> getShouldCloseScreen() {
		return shouldCloseScreen;
	}

	public void saveNote(Note note) {
		Thread thread = new Thread(() -> {
			notesDAO.add(note);
			shouldCloseScreen.postValue(true);
		});
		thread.start();
	}

}

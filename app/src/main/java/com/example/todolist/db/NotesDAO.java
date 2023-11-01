package com.example.todolist.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.todolist.adapter.Note;

import java.util.List;

@Dao
public interface NotesDAO {

	@Query("SELECT * FROM notes")
	LiveData<List<Note>> getNotes();

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void add(Note note);

	@Query("DELETE FROM notes WHERE id = :id")
	void delete(int id);
}

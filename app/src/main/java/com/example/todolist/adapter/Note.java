package com.example.todolist.adapter;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

	@PrimaryKey(autoGenerate = true)
	private int id;
	private final String text;
	private final int priority;

	public Note(int id, String text, int priority) {
		this.id = id;
		this.text = text;
		this.priority = priority;
	}

	@Ignore
	public Note(String text, int priority) {
		this.text = text;
		this.priority = priority;
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public int getPriority() {
		return priority;
	}
}
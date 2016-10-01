package com.example.chapter06.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
	public static final String CREATE_BOOK = "create table book(id integer primary key autoincrement,"
+" author text, price real, pages integer, name text, category_id integer)";
	public static final String CREATE_CATEGORY = "create table category (id integer primary key autoincrement,"
+" category_name text, category_code integer)";
	
	private Context mContext;

	public MyDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_BOOK);
		db.execSQL(CREATE_CATEGORY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		switch (oldVersion) {
		case 1:
			db.execSQL(CREATE_CATEGORY);
		case 2:
			db.execSQL("alter table book add column category_id integer");
		default:
		}
		
		db.execSQL("drop table if exists Book");
		db.execSQL("drop table if exists Category");
		onCreate(db);
	}

}

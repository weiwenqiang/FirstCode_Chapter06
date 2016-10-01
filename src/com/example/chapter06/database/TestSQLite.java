package com.example.chapter06.database;

import com.example.chapter06.R;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class TestSQLite extends Activity implements OnClickListener {
	private MyDatabaseHelper dbHelper;
	SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b4_sqlite);
		
		dbHelper = new MyDatabaseHelper(TestSQLite.this, "BookStore.db", null, 2);
		findViewById(R.id.b4_create).setOnClickListener(this);
		findViewById(R.id.b4_insert).setOnClickListener(this);
		findViewById(R.id.b4_update).setOnClickListener(this);
		findViewById(R.id.b4_select).setOnClickListener(this);
		findViewById(R.id.b4_delete).setOnClickListener(this);
		findViewById(R.id.b4_transaction).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b4_create:
			dbHelper.getWritableDatabase();
			break;
		case R.id.b4_insert:
		{
			db = dbHelper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("name", "The Da Vinci Code");
			values.put("author", "Dan Brown");
			values.put("pages", 454);
			values.put("price", 16.96);
			db.insert("Book", null, values);
			values.clear();
			//开始组装第二条数据
			values.put("name", "The Lost Symbol");
			values.put("author", "Dan Brown");
			values.put("pages", 510);
			values.put("price", 19.95);
			db.insert("Book", null, values);
			break;
		}
		case R.id.b4_update:
		{
			db = dbHelper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("price", 10.99);
			db.update("Book", values, "name=?", new String[]{"The Da Vinci Code"});
			break;
		}
		case R.id.b4_select:
		{
			db = dbHelper.getWritableDatabase();
			Cursor cursor = db.query("Book", null, null, null, null, null, null);
			if(cursor.moveToFirst()){
				do{
					String name = cursor.getString(cursor.getColumnIndex("name"));
					String author = cursor.getString(cursor.getColumnIndex("author"));
					int pages = cursor.getInt(cursor.getColumnIndex("pages"));
					double price = cursor.getDouble(cursor.getColumnIndex("price"));
					Log.d("TestSQLite", "book name is "+ name);
					Log.d("TestSQLite", "book author is "+ author);
					Log.d("TestSQLite", "book pages is "+ pages);
					Log.d("TestSQLite", "book price is "+ price);
				}while(cursor.moveToNext());
			}
			cursor.close();
			break;
		}
		case R.id.b4_delete:
		{
			db = dbHelper.getWritableDatabase();
			db.delete("Book", "pages > ?", new String[]{"500"});
			break;
		}
		case R.id.b4_transaction:
		{
			db = dbHelper.getWritableDatabase();
			//开启事务
			db.beginTransaction();
			
			try {
				db.delete("Book", null, null);
				if(true){
					//在这里手动抛出一个异常，让事务失败
					throw new NullPointerException();
				}
				ContentValues values = new ContentValues();
				values.put("name", "Game of Thrones");
				values.put("author", "George Martin");
				values.put("pages", 720);
				values.put("price", 20.85);
				db.insert("Book", null, values);
				//事务已经执行成功
				db.setTransactionSuccessful();
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				//结束事务
				db.endTransaction();
			}
			
			break;
		}
		default:
			break;
		}
	}

}

package com.example.chapter06.sharedpreferences;

import com.example.chapter06.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class TestSharedPreferences extends Activity implements OnClickListener {
	private Button saveData, restoreData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b2_sharedpreferences);
		saveData = (Button) findViewById(R.id.b2_save_data);
		saveData.setOnClickListener(this);
		restoreData = (Button) findViewById(R.id.b2_restore_data);
		restoreData.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b2_save_data:
			SharedPreferences.Editor editor = getSharedPreferences("data",
					MODE_PRIVATE).edit();
			editor.putString("name", "Tom");
			editor.putInt("age", 28);
			editor.putBoolean("married", false);
			editor.commit();
			break;
		case R.id.b2_restore_data:
			SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
			String name = pref.getString("name", "");
			int age = pref.getInt("age", 0);
			boolean married = pref.getBoolean("married", false);
			Log.d("TestSharedPreferences", "name: "+name);
			Log.d("TestSharedPreferences", "age: "+age);
			Log.d("TestSharedPreferences", "married: "+married);
			break;
		default:
			break;
		}
	}
}

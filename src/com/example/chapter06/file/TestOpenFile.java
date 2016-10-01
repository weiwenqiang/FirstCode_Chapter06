package com.example.chapter06.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.example.chapter06.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class TestOpenFile extends Activity {
	EditText edit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b1_filepersistence);
		edit = (EditText) findViewById(R.id.b1_file_edit);
		//读取
		String inputText = load();
		if(!TextUtils.isEmpty(inputText)){
			edit.setText(inputText);
			//将光标移动到文本的末尾，以便于继续输入
			edit.setSelection(inputText.length());
			Toast.makeText(TestOpenFile.this, "Restoring succeeded(恢复成功)", Toast.LENGTH_SHORT).show();
		}
	}

	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		String inputText = edit.getText().toString();
		save(inputText);
	}


	public void save(String inputText){
//		String data = "Data to seve";
		FileOutputStream out = null;
		BufferedWriter writer = null;
		try {
			out = openFileOutput("data", Context.MODE_PRIVATE);
			writer = new BufferedWriter(new OutputStreamWriter(out));
			writer.write(inputText);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try{
				if(writer != null){
					writer.close();
				}
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public String load(){
		FileInputStream in = null;
		BufferedReader reader = null;
		StringBuilder content = new StringBuilder();
		try{
			in = openFileInput("data");
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while((line = reader.readLine())!= null){
				content.append(line);
			}
		} catch(IOException e){
			e.printStackTrace();
		} finally{
			if(reader != null){
				try{
					reader.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		return content.toString();
	}
}

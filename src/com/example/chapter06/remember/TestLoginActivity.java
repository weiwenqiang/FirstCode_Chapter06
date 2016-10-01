package com.example.chapter06.remember;

import com.example.chapter06.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class TestLoginActivity extends BaseActivity implements OnClickListener {
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;

	private CheckBox rememberPass;

	private EditText accountEdit;
	private EditText passwordEdit;
	private Button login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b3_login);

		pref = PreferenceManager.getDefaultSharedPreferences(this);

		accountEdit = (EditText) findViewById(R.id.login_account);
		passwordEdit = (EditText) findViewById(R.id.login_password);
		rememberPass = (CheckBox) findViewById(R.id.b3_remember_pass);
		login = (Button) findViewById(R.id.b3_login);
		login.setOnClickListener(this);
		boolean isRemember = pref.getBoolean("remember_password", false);
		if (isRemember) {
			// 将帐号和密码都设置到文本框中
			String account = pref.getString("account", "");
			String password = pref.getString("password", "");
			accountEdit.setText(account);
			passwordEdit.setText(password);
			rememberPass.setChecked(true);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b3_login:
			String account = accountEdit.getText().toString();
			String password = passwordEdit.getText().toString();
			// 如果帐号是admin且密码是123456，就认为登录成功
			if (account.equals("admin") && password.equals("123456")) {
				editor = pref.edit();
				// 检查是否被选中
				if (rememberPass.isChecked()) {
					editor.putBoolean("remember_password", true);
					editor.putString("account", account);
					editor.putString("password", password);
				} else {
					editor.clear();
				}
				editor.commit();

				Intent intent = new Intent(TestLoginActivity.this,
						TestForceOffline.class);
				startActivity(intent);
				// finish();
			} else {
				Toast.makeText(TestLoginActivity.this,
						"account or password is invalid(帐号或密码错误)",
						Toast.LENGTH_SHORT).show();
			}

			break;

		default:
			break;
		}
	}

}

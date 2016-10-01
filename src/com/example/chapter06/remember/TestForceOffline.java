package com.example.chapter06.remember;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.chapter06.R;

public class TestForceOffline extends BaseActivity implements OnClickListener {
	Button forceOffline;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_forceoffline);
		forceOffline = (Button) findViewById(R.id.force_offline);
		forceOffline.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.force_offline:

			Intent intent = new Intent("chapter06.test.broadcast.FORCE_OFFLINE");
			sendBroadcast(intent);
			break;

		default:
			break;
		}
	}

}

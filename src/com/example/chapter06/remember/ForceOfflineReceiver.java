package com.example.chapter06.remember;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;

public class ForceOfflineReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(final Context context, Intent intent) {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
		dialogBuilder.setTitle("Warning����");
		dialogBuilder.setMessage("You are forced to be offline. Please try to login again.�㱻�����ߡ��볢�����µ�¼��");
		//����false,�Ի��򲻿�ȡ��
		dialogBuilder.setCancelable(false);
		dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//�������л
				ActivityCollector.finishAll();
				Intent intent = new Intent(context, TestForceOffline.class);
				//�������ڹ㲥��������������ģ����һ��Ҫ��Intent����FLAG_ACTIVITY_NEW_TASK��־
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}
		});
		AlertDialog alertDialog = dialogBuilder.create();
		//��Ҫ����AlertDialog�����ͣ���֤�ڹ㲥�������п�����������
		alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		alertDialog.show();
	}

}

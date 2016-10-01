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
		dialogBuilder.setTitle("Warning警告");
		dialogBuilder.setMessage("You are forced to be offline. Please try to login again.你被迫离线。请尝试重新登录。");
		//设置false,对话框不可取消
		dialogBuilder.setCancelable(false);
		dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//销毁所有活动
				ActivityCollector.finishAll();
				Intent intent = new Intent(context, TestForceOffline.class);
				//由于是在广播接收器里启动活动的，因此一定要给Intent加入FLAG_ACTIVITY_NEW_TASK标志
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}
		});
		AlertDialog alertDialog = dialogBuilder.create();
		//需要设置AlertDialog的类型，保证在广播接收器中可以正常弹出
		alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		alertDialog.show();
	}

}

package com.nirvana.exception;

import android.content.Context;
import android.os.AsyncTask;

import com.nirvana.application.MyApplication;
import com.nirvana.config.Config;
import com.nirvana.util.HttpUtil;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;
import java.util.Map;

public class CaughtExceptionHandler implements UncaughtExceptionHandler {
	private MyApplication app;
	private Context context;
	private static CaughtExceptionHandler caughtExceptionHandler;
	
	public synchronized static CaughtExceptionHandler getInstance() {
		if(caughtExceptionHandler == null) {
			caughtExceptionHandler = new CaughtExceptionHandler();
		}
		
		return caughtExceptionHandler;
	}
	
	public void init(Context context, MyApplication app) {
		this.context = context;
		this.app = app;
		Thread.setDefaultUncaughtExceptionHandler(this);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		//Toast.makeText(context, "出现异常", Toast.LENGTH_LONG).show();
		System.out.println("------------------uncaught exception开始-----------------------");
		ex.printStackTrace();
		System.out.println("------------------uncaught exception结束-----------------------");
		
		if(HttpUtil.isConn(context)) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("exception", ex.getMessage());
			new AsyncTasks(Config.SAVE_NAME).execute(params);
		}

		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(1);
	}
	
	/**
	 * 获取到错误,提交到服务器中记录
	 * @author Nirvana
	 *
	 */
	private class AsyncTasks extends AsyncTask<Map<String, String>, Void, Object> {
		private String taskName;
		//private SVProgressHUD loading;
		
		public AsyncTasks(String taskName) {
			this.taskName = taskName;
		}
		
		@Override
		protected void onPreExecute() {
			if(this.taskName.equals(Config.SAVE_NAME)) {
				//loading.showWithStatus("加载中...");
			}
		}
		
		@Override
		protected Object doInBackground(Map<String, String>... params) {
			Object result = null;
			if(taskName.equals(Config.SAVE_NAME)) {
				//result = new ExceptionNet(app).recordException(params[0]);
			}
			return result;
		}
		
		@Override
		protected void onPostExecute(Object result) {
			if(taskName.equals(Config.SAVE_NAME)) {
				/*
				if(result instanceof BaseEntity) {
					//BaseEntity baseEntity = (BaseEntity) result;
					
					///不论是否成功提交错误信息,最后都退出应用程序
					android.os.Process.killProcess(android.os.Process.myPid());
					System.exit(1);
				}
				*/
			}
		}
	}
}

package com.nirvana.application;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.PowerManager;

import com.nirvana.activity.LoginAty;
import com.nirvana.activity.MainAty;
import com.nirvana.http.response.UserEntity;

import java.util.LinkedList;
import java.util.List;

public class AppInstance extends Application {
	private List<Activity> activityList = new LinkedList<Activity>();
	private static AppInstance instance;
	private String sessionContext = "";
	private String userContext = "";
	private UserEntity userEntity;
	private String barcode = "";
	private MainAty mainAty;
	private PowerManager.WakeLock wakeLock;
	
	private AppInstance() {
	}

	public static AppInstance getInstance() {
		if (null == instance) {
			instance = new AppInstance();
		}
		return instance;
	}

	public void addActivity(Activity activity) {
		activityList.add(activity);
		if (activity instanceof MainAty) {
			mainAty = (MainAty) activity;
		}
	}

	public List<Activity> getActivity() {
		return activityList;
	}

	public void retLogin() {
		resetContext();
		for (Activity activity : activityList) {
			if (activity instanceof LoginAty) {
				continue;
			}
			activity.finish();
		}
	}
	
	public void exit() {
		try {
			resetContext();
			for (Activity activity : activityList) {
				activity.finish();
			}
			activityList = null;
			System.exit(0);
			// wakeLock.release();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取当前Activity（最后一个加入的）
	 */
	public Activity currentActivity() {
		Activity activity = activityList.get(activityList.size() - 1);
		return activity;
	}

	/**
	 * 退出应用程序
	 */
	public void exitApp(Context context) {
		try {
			exit();
			ActivityManager activityMgr = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.killBackgroundProcesses(context.getPackageName());
			System.exit(0);
		} catch (Exception e) {
			
		}
	}

	public void resetContext() {
		userContext = "";
		sessionContext = "";
		userEntity = new UserEntity();
		userEntity.setUserName("");
	}

	public MainAty getMainAppActivity() {
		return mainAty;
	}

	public PowerManager.WakeLock getWakeLock() {
		return wakeLock;
	}

	public void setWakeLock(PowerManager.WakeLock wakeLock) {
		this.wakeLock = wakeLock;
	}

	public String getUserContext() {
		return userContext;
	}

	public void setUserContext(String userContext) {
		this.userContext = userContext;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}
	
	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public String getSessionContext() {
		return sessionContext;
	}

	public void setSessionContext(String sessionContext) {
		this.sessionContext = sessionContext;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
}
package com.nirvana.application;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.EditText;

import com.nirvana.exception.CaughtExceptionHandler;
import com.nirvana.http.request.BaseReqParam;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class MyApplication extends Application {

	private String mData;
	public EditText mTv;
	public Vibrator mVibrator01;
	public static String TAG = "JY_SDWL_TMS";
	private boolean isDownload;
	private String Device = android.os.Build.MODEL;
	private String DeviceId = "";
	private String OperSystem = "android";
	private String OperVersion = android.os.Build.VERSION.RELEASE;
	private String myVersion = "";
	private String hostip = ""; // 本机IP
	private String hostmac = ""; // 本机MAC
	private String Pixels = ""; // 屏幕分辨率
	private String MinBulletinId = ""; // 最小通知ID
	private String MaxBulletinId = ""; // 最大通知ID
	private String aliveMaxBulletinId = ""; // 保持心跳时传回来的最大通知ID
	private String updateVersion = "";
	private String phoneNumber = "";

	private String permission;
	private String usercompany;

	private boolean location = false;
	//定位的類型: GPS定位、網絡定位...
	private String locationType = "";
	private double currentLatitude;
	private double currentLongitude;
	private String currentLocation;

	@Override
	public void onCreate() {
		super.onCreate();

		isDownload = false;
		TelephonyManager tm = (TelephonyManager) this
				.getSystemService(TELEPHONY_SERVICE);
		DeviceId = tm.getDeviceId();
		phoneNumber = tm.getLine1Number();
		myVersion = getMyOwnVersion();
		hostip = getLocalIpAddress();
		hostmac = getLocalMacAddress();
		
		//设置异常处理
		CaughtExceptionHandler exceptionHandler = CaughtExceptionHandler.getInstance();
		exceptionHandler.init(getApplicationContext(), this);

	}

	public void logMsg(String str) {
		try {
			mData = str;
			if (mTv != null)
				mTv.setText(mData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isDownload() {
		return isDownload;
	}

	public void setDownload(boolean isDownload) {
		this.isDownload = isDownload;
	}

	/**
	 * 获取版本号
	 * 
	 * @return 当前应用的版本号
	 */
	public String getMyOwnVersion() {
		String version = "";
		try {
			PackageManager manager = getPackageManager();
			PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
			// String version = info.versionName ;
			System.out.println(info.versionName);
			Log.d(TAG, "... Application onCreate... version="
					+ info.versionName);
			version = info.versionName + "." + info.versionCode;
		} catch (Exception e) {
			e.printStackTrace();
			version = "";
		}

		return version;
	}

	public String getLocalIpAddress() {
		try {
			Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces();
			while (en.hasMoreElements()) {
				NetworkInterface nif = en.nextElement();
				Enumeration<InetAddress> enumIpAddr = nif.getInetAddresses();
				while (enumIpAddr.hasMoreElements()) {
					InetAddress mInetAddress = enumIpAddr.nextElement();

					/*
					if (!mInetAddress.isLoopbackAddress()
							&& InetAddressUtils.isIPv4Address(mInetAddress
									.getHostAddress())) {

						return mInetAddress.getHostAddress().toString();
					}
					*/

					return null;
				}
			}
		} catch (SocketException ex) {
			Log.e("Location", "获取本地IP地址失败");
			ex.printStackTrace();
		}
		return null;
	}

	public String getLocalMacAddress() {
		/*
		WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress();
		*/
		return null;
	}

	public String getDevice() {
		return Device;
	}

	public void setDevice(String device) {
		Device = device;
	}

	public String getDeviceId() {
		return DeviceId;
	}

	public void setDeviceId(String deviceId) {
		DeviceId = deviceId;
	}

	public String getOperSystem() {
		return OperSystem;
	}

	public void setOperSystem(String operSystem) {
		OperSystem = operSystem;
	}

	public String getMyVersion() {
		return myVersion;
	}

	public void setMyVersion(String myVersion) {
		this.myVersion = myVersion;
	}

	public String getOperVersion() {
		return OperVersion;
	}

	public void setOperVersion(String operVersion) {
		OperVersion = operVersion;
	}

	public String getPixels() {
		return Pixels;
	}

	public void setPixels(String pixels) {
		Pixels = pixels;
	}

	public String getHostip() {
		return hostip;
	}

	public void setHostip(String hostip) {
		this.hostip = hostip;
	}

	public String getHostmac() {
		return hostmac;
	}

	public void setHostmac(String hostmac) {
		this.hostmac = hostmac;
	}

	public String getMinBulletinId() {
		return MinBulletinId;
	}

	public void setMinBulletinId(String minBulletinId) {
		MinBulletinId = minBulletinId;
	}

	public String getMaxBulletinId() {
		return MaxBulletinId;
	}

	public void setMaxBulletinId(String maxBulletinId) {
		MaxBulletinId = maxBulletinId;
	}

	public String getUpdateVersion() {
		return updateVersion;
	}

	public void setUpdateVersion(String updateVersion) {
		this.updateVersion = updateVersion;
	}

	public String getAliveMaxBulletinId() {
		return aliveMaxBulletinId;
	}

	public void setAliveMaxBulletinId(String aliveMaxBulletinId) {
		this.aliveMaxBulletinId = aliveMaxBulletinId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getUsercompany() {
		return usercompany;
	}

	public void setUsercompany(String usercompany) {
		this.usercompany = usercompany;
	}

	/**
	 * 初始化JSON格式的基础访问参数
	 * @param baseReqParam
	 * @return
	 */
	public BaseReqParam initBaseReqParam(BaseReqParam baseReqParam) {
		if(baseReqParam == null) {
			baseReqParam = new BaseReqParam();
		}
		
		baseReqParam.setDevice(getDevice());
		baseReqParam.setDeviceId(getDeviceId());
		baseReqParam.setOperSystem(getOperSystem());
		baseReqParam.setVersion(getMyVersion());
		baseReqParam.setLoginIp(getLocalIpAddress());
		baseReqParam.setPixels(getPixels());
		
		return baseReqParam;
	}

	// 应用是否在运行
	public boolean isRunning() {
		boolean isrunning = false;
		// 判断应用是否在运行
		ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> list = am.getRunningTasks(100);
		for (RunningTaskInfo info : list) {
			if (info.topActivity.getPackageName().equals(getPackageName())
					|| info.baseActivity.getPackageName().equals(
							getPackageName())) {
				isrunning = true;
				break;
			}
		}
		
		return isrunning;
	}

	public List<PackageInfo> getAllApps() {
		List<PackageInfo> apps = new ArrayList<PackageInfo>();
		PackageManager packageManager = this.getPackageManager();
		// 获取手机内所有应用
		List<PackageInfo> paklist = packageManager.getInstalledPackages(0);
		for (int i = 0; i < paklist.size(); i++) {
			PackageInfo pak = (PackageInfo) paklist.get(i);
			// 判断是否为非系统预装的应用 (大于0为系统预装应用，小于等于0为非系统应用)
			if ((pak.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
				apps.add(pak);
			}
		}
		return apps;
	}

	public void launchApp() {
		PackageManager packageManager = this.getPackageManager();
		
		Intent intent = packageManager
				.getLaunchIntentForPackage("com.idata95_iscan");// "jp.co.johospace.jorte"就是我们获得要启动应用的包名
		if (intent == null) {
			System.out.println("IScan not found!");
		} else {
			startActivity(intent);
		}

	}
	
	public boolean isLocation() {
		return location;
	}
	
	public void setLocation(boolean location) {
		this.location = location;
	}
	
	public String getLocationType() {
		return locationType;
	}
	
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	
	public double getCurrentLatitude() {
		return currentLatitude;
	}

	public void setCurrentLatitude(double currentLatitude) {
		this.currentLatitude = currentLatitude;
	}

	public double getCurrentLongitude() {
		return currentLongitude;
	}

	public void setCurrentLongitude(double currentLongitude) {
		this.currentLongitude = currentLongitude;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

}
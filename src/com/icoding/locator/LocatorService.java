package com.icoding.locator;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public abstract class LocatorService extends Service implements LocatorManagerCallback{
	private static final String DEBUG = LocatorService.class.getSimpleName();
	
	public static final String BROADCAST_LOCATION_START = "com.icoding.locator.BROADCAST_LOCATION_START";
	public static final String BROADCAST_LOCATION_STOP = "com.icoding.locator.BROADCAST_LOCATION_STOP";
	public static final String BROADCAST_LOCATION_ERROR = "com.icoding.locator.BROADCAST_LOCATION_ERROR";
	public static final String BROADCAST_LOCATION_RECEIVED = "com.icoding.locator.BROADCAST_LOCATION_RECEIVED";
	
	public static final String EXTRA_ERROR = "com.icoding.locator.EXTRA_ERROR";
	public static final String EXTRA_LOCATION = "com.icoding.locator.EXTRA_LOCATION";
	public static final String EXTRA_ENABLE_GPS = "com.icoding.locator.EXTRA_ENABLE_GPS";
	private LocatorManager<LocatorManagerCallback> mLocatorManager;
	
	@Override
	@SuppressWarnings("unchecked")
	public void onCreate() {
		super.onCreate();
		mLocatorManager = initializeManager();
		mLocatorManager.setCallback(this);
		Log.d(DEBUG, "定位服务已创建");
	}
	
	@SuppressWarnings("rawtypes")
	protected abstract LocatorManager initializeManager();
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		boolean gpsEnable = intent.getBooleanExtra(EXTRA_ENABLE_GPS, false);
		Log.d(DEBUG, "是否使用GPS:" + gpsEnable);
		if(mLocatorManager != null){
			mLocatorManager.startLocation(gpsEnable);
		}
		return START_FLAG_REDELIVERY;
	}
	
	@Override
	public void onDestroy() {
		if(mLocatorManager != null){
			mLocatorManager.stopLocation();
			mLocatorManager = null;
		}
		super.onDestroy();
		Log.d(DEBUG, "定位服务已销毁");
	}
	
	@Override
	public void onLocationStart() {
		Log.d(DEBUG, "定位服务已开始定位");
		Intent broadcast = new Intent(BROADCAST_LOCATION_START);
		LocalBroadcastManager.getInstance(getApplicationContext())
			.sendBroadcast(broadcast);
		
	}
	
	@Override
	public void onLocationStop() {
		Log.d(DEBUG, "定位服务已停止定位");
		Intent broadcast = new Intent(BROADCAST_LOCATION_STOP);
		LocalBroadcastManager.getInstance(getApplicationContext())
			.sendBroadcast(broadcast);
	}
	
	@Override
	public void onLocationReceived(ILocation location) {
		Log.d(DEBUG, "定位服务已收到位置信息");
		Intent broadcast = new Intent(BROADCAST_LOCATION_RECEIVED);
		broadcast.putExtra(EXTRA_LOCATION, location);
		LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(broadcast);
		stopSelf();
	}
	
	@Override
	public void onLocationError(int errorCode) {
		Log.e(DEBUG, "定位发生错误:" + errorCode);
		Intent broadcast = new Intent(BROADCAST_LOCATION_ERROR);
		broadcast.putExtra(EXTRA_ERROR, errorCode);
		LocalBroadcastManager.getInstance(getApplicationContext())
			.sendBroadcast(broadcast);
	}
}

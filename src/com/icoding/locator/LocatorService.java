package com.icoding.locator;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class LocatorService extends Service {
	
	public static final String BROADCAST_LOCATION_START = "com.icoding.locator.BROADCAST_LOCATION_START";
	public static final String BROADCAST_LOCATION_STOP = "com.icoding.locator.BROADCAST_LOCATION_STOP";
	public static final String BROADCAST_LOCATION_ERROR = "com.icoding.locator.BROADCAST_LOCATION_ERROR";
	
	public static final String EXTRA_TIME = "com.icoding.locator.EXTRA_TIME";
	public static final String EXTRA_ADDRESS = "com.icoding.locator.EXTRA_ADDRESS";
	public static final String EXTRA_COUNTRY = "com.icoding.locator.EXTRA_COUNTRY";
	public static final String EXTRA_LATITUDE = "com.icoding.locator.EXTRA_LATITUDE";
	public static final String EXTRA_LONGITUDE = "com.icoding.locator.EXTRA_LONGITUDE";
	public static final String EXTRA_BEARING = "com.icoding.locator.EXTRA_BEARING";
	public static final String EXTRA_SPEED = "com.icoding.locator.EXTRA_SPEED";
	public static final String EXTRA_ALTITUDE = "com.icoding.locator.EXTRA_ALTITUDE";
	public static final String EXTRA_RADIUS = "com.icoding.locator.EXTRA_RADIUS";
	public static final String EXTRA_ERROR = "com.icoding.locator.EXTRA_ERROR";
	public static final String EXTRA_PROVIDER = "com.icoding.locator.EXTRA_PROVIDER";
	public static final String EXTRA_FLOOR = "com.icoding.locator.EXTRA_FLOOR";
	public static final String EXTRA_ACCURACY = "com.icoding.locator.EXTRA_ACCURACY";

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

}

package com.icoding.locator.amap;

import com.icoding.locator.ILocation;
import com.icoding.locator.LocatorManager;
import com.icoding.locator.LocatorService;

public class AMapLocatorService extends LocatorService implements AMapManagerCallback{
	
	@Override
	@SuppressWarnings("rawtypes")
	protected LocatorManager initializeManager() {
		return new AMapLocatorManager(getApplicationContext());
	}
	
	@Override
	public void onLocationStart() {
		super.onLocationStart();
	}
	
	@Override
	public void onLocationStop() {
		super.onLocationStop();
	}
	
	@Override
	public void onLocationReceived(ILocation location) {
		super.onLocationReceived(location);
	}

	@Override
	public void onLocationError(int errorCode) {
		
	}
}

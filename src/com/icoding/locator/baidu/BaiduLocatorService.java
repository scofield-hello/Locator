package com.icoding.locator.baidu;

import com.icoding.locator.ILocation;
import com.icoding.locator.LocatorManager;
import com.icoding.locator.LocatorService;

public class BaiduLocatorService extends LocatorService implements BaiduManagerCallback{
	
	@Override
	@SuppressWarnings("rawtypes")
	protected LocatorManager initializeManager() {
		return new BaiduLocatorManager(getApplicationContext());
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
		super.onLocationError(errorCode);
	}
}

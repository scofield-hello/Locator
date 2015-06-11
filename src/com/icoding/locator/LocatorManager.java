package com.icoding.locator;

public interface LocatorManager<E extends LocatorManagerCallback> {
	
	public void configuration();
	
	public void startLocation(boolean gpsEnable);
	
	public void stopLocation();
	
	public void setCallback(E callback);
}

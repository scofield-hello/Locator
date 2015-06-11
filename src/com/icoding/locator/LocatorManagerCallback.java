package com.icoding.locator;


public interface LocatorManagerCallback{


	/**
	 * 定位开始时回调该方法
	 */
	public void onLocationStart();
	
	/**
	 * 获取到位置信息时回调该方法
	 * @param location
	 */
	public void onLocationReceived(ILocation location);
	
	/**
	 * 定位停止时回调该方法
	 */
	public void onLocationStop();
	
	public void onLocationError(int errorCode);
}

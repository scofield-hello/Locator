package com.icoding.locator.baidu;

import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.icoding.locator.ILocation;
import com.icoding.locator.LocatorManager;

public class BaiduLocatorManager implements LocatorManager<BaiduManagerCallback>,BDLocationListener{
	
	private Context mContext;
	
	private WakeLock mWakeLock = null;
	
	private BaiduManagerCallback mCallback;
	
	private LocationClient mLocationClient;
	private LocationClientOption mLocationOption;
	public BaiduLocatorManager(Context context){
		mContext = context;
		mLocationClient = new LocationClient(mContext);
		acquireWakeLock();
	}
	
	@Override
	public void startLocation(boolean gpsEnable) {
		if(mLocationClient != null && mLocationClient.isStarted()){
			mLocationOption.setOpenGps(gpsEnable);
			mLocationClient.setLocOption(mLocationOption);
			mLocationClient.requestLocation();
		}
		if(mCallback != null){
			mCallback.onLocationStart();
		}
	}

	@Override
	public void stopLocation() {
		if(mLocationClient != null && mLocationClient.isStarted()){
			mLocationClient.unRegisterLocationListener(this);
			mLocationClient.stop();
		}
		if(mCallback != null){
			mCallback.onLocationStop();
		}
		releaseWakeLock();
	}

	@Override
	public void setCallback(BaiduManagerCallback callback) {
		this.mCallback = callback;
	}
	
	/**
	 * 申请设备电源锁
	 */
    private void acquireWakeLock()
    {	
        if(this.mWakeLock != null){
        	this.mWakeLock.acquire();
        	return;
        }
        PowerManager pm = (PowerManager)mContext.getSystemService(Context.POWER_SERVICE);
        this.mWakeLock = pm.newWakeLock(1,getClass().getCanonicalName());
        this.mWakeLock.setReferenceCounted(false);
        this.mWakeLock.acquire();
    }

    /**
     * 释放设备电源锁
     */
    private void releaseWakeLock(){
        if (null != this.mWakeLock && this.mWakeLock.isHeld()){
            this.mWakeLock.release();
        }
    }

	@Override
	public void configuration() {
		mLocationOption = new LocationClientOption();
		mLocationOption.setLocationMode(LocationMode.Hight_Accuracy);//设置定位模式
		mLocationOption.setAddrType("all");
		mLocationOption.setCoorType("bd09ll");//返回的定位结果是百度经纬度bd09ll,默认值gcj02
		//setScanSpan < 1000 则为app主动请求定位；setScanSpan>=1000,则为定时定位模式
		mLocationOption.setScanSpan(500);
		mLocationOption.setIsNeedAddress(true);//返回的定位结果包含地址信息
		mLocationOption.setNeedDeviceDirect(false);//返回的定位结果包含手机机头的方向
		mLocationOption.setOpenGps(false);
		mLocationOption.setProdName("Locator");
		mLocationClient.setLocOption(mLocationOption);
		mLocationClient.registerLocationListener(this);
		if(!mLocationClient.isStarted()){
			mLocationClient.start();
		}
	}

	@Override
	public void onReceiveLocation(BDLocation location) {
		if(location != null){
			ILocation loc = new ILocation();
			loc.setLocator("BAIDU");
			loc.setTime(location.getTime());
			loc.setAccuracy(location.getRadius());
			loc.setAddress(location.getAddrStr());
			loc.setBearing(location.getDirection());
			loc.setLatitude(location.getLatitude());
			loc.setLongitude(location.getLongitude());
			loc.setAltitude(location.getAltitude());
			loc.setProvince(location.getProvince());
			loc.setStreet(location.getStreet());
			loc.setCityCode(location.getCityCode());
			loc.setCity(location.getCity());
			loc.setCountry(location.getCountry());
			loc.setDistrict(location.getDistrict());
			loc.setErrorCode(location.getLocType());
			loc.setFloor(location.getFloor());
			loc.setSpeed(location.getSpeed());
			if(location.getLocType() == BDLocation.TypeGpsLocation){
				loc.setProvider("gps");
			}else if(location.getLocType() == BDLocation.TypeNetWorkLocation){
				loc.setProvider("lbs");
			}
			if(mCallback != null){
				mCallback.onLocationReceived(loc);
			}
		}
	}
}

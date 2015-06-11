package com.icoding.locator.amap;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.icoding.locator.ILocation;
import com.icoding.locator.LocatorManager;
import com.icoding.utils.DateUtils;

public class AMapLocatorManager implements LocatorManager<AMapManagerCallback>,AMapLocationListener{
	
	private Context mContext;
	
	private WakeLock mWakeLock = null;
	
	private LocationManagerProxy mLocationManagerProxy;
	
	private AMapManagerCallback mCallback;
	
	public AMapLocatorManager(Context context){
		mContext = context;
		acquireWakeLock();
	}
	
	@Override
	public void startLocation(boolean gpsEnable) {
		if(mLocationManagerProxy == null){
			mLocationManagerProxy = LocationManagerProxy.getInstance(this.mContext);
		}
		mLocationManagerProxy.
			requestLocationData(
					LocationProviderProxy.AMapNetwork, -1, 15, this);
		mLocationManagerProxy.setGpsEnable(gpsEnable);
		if(mCallback != null){
			mCallback.onLocationStart();
		}
	}

	@Override
	public void stopLocation() {
		if (mLocationManagerProxy != null) {
			mLocationManagerProxy.removeUpdates(this);
			mLocationManagerProxy.destroy();
			mLocationManagerProxy = null;
		}
		if(mCallback != null){
			mCallback.onLocationStop();
		}
		releaseWakeLock();
	}

	@Override
	public void setCallback(AMapManagerCallback callback) {
		this.mCallback = callback;
	}
	
	/**
	 * 申请设备电源锁
	 */
    private void acquireWakeLock()
    {	
        if(this.mWakeLock != null){
        	this.mWakeLock.acquire();
        	//mLogger.debug("已申请到用于定位的设备电源锁");
        	return;
        }
        PowerManager pm = (PowerManager)mContext.getSystemService(Context.POWER_SERVICE);
        this.mWakeLock = pm.newWakeLock(1,getClass().getCanonicalName());
        this.mWakeLock.setReferenceCounted(false);
        this.mWakeLock.acquire();
        //mLogger.debug("已申请到用于定位的设备电源锁");
    }

    /**
     * 释放设备电源锁
     */
    private void releaseWakeLock(){
        if (null != this.mWakeLock && this.mWakeLock.isHeld()){
            this.mWakeLock.release();
            //mLogger.debug("已释放用于定位的设备电源锁");
        }
    }

	@Override
	public void onLocationChanged(Location location) {
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		
	}

	@Override
	public void onLocationChanged(AMapLocation location) {
		if(location != null){
			if(location.getAMapException().getErrorCode() == 0){
				if(mCallback != null){
					ILocation loc = new ILocation();
					loc.setTime(DateUtils.formateTime(location.getTime(), "yyyy-MM-dd HH:mm:ss"));
					loc.setAddress(location.getAddress());
					loc.setLatitude(location.getLatitude());
					loc.setLongitude(location.getLongitude());
					loc.setBearing(location.getBearing());
					loc.setProvider(location.getProvider());
					loc.setSpeed(location.getSpeed());
					loc.setAltitude(location.getAltitude());
					loc.setFloor(location.getFloor());
					loc.setAccuracy(location.getAccuracy());
					loc.setCountry(location.getCountry());
					loc.setErrorCode(location.getAMapException().getErrorCode());
					loc.setProvince(location.getProvince());
					loc.setAdCode(location.getAdCode());
					loc.setCity(location.getCity());
					loc.setCityCode(location.getCityCode());
					loc.setDistrict(location.getDistrict());
					loc.setStreet(location.getStreet());
					loc.setRoad(location.getRoad());
					mCallback.onLocationReceived(loc);
				}
			}else{
				mCallback.onLocationError(location.getAMapException().getErrorCode());
			}
		}
	}
}

# Locator

一个可以让你快速使用百度或高德定位的开源库，调用定位Service并注册BroadcastReceiver即可接收到当前的位置信息。

#使用方法
将Locator作为Library引入你的项目中，在你的项目AndroidManifest.xml文件中声明定位功能需要使用的权限和必须的服务以及相关的API Key：
```xml
<uses-permission 
    android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<uses-permission 
    android:name="android.permission.INTERNET" />
<uses-permission 
    android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission 
    android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission 
    android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission 
    android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission 
    android:name="android.permission.READ_PHONE_STATE" />
<uses-permission 
    android:name="android.permission.CHANGE_WIFI_STATE" />
<uses-permission 
    android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission 
    android:name="android.permission.CHANGE_CONFIGURATION" />
<uses-permission 
    android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
<uses-permission 
    android:name="android.permission.READ_LOGS" />
<uses-permission 
    android:name="android.permission.WAKE_LOCK"/>
<uses-permission 
	  android:name="android.permission.DEVICE_POWER"/>
	  
<application
      android:allowBackup="true"
      android:icon="@drawable/ic_launcher"
      android:label="@string/app_name"
      android:theme="@style/AppTheme" >
      <!-- 高德地图API Key -->
      <meta-data
          android:name="com.amap.api.v2.apikey"
          android:value="高德API Key,需要申请" />
      <!-- 百度地图API Key -->
      <meta-data
          android:name="com.baidu.lbsapi.API_KEY"
          android:value="百度API Key,需要申请"/>
      <!-- 百度定位需要注册的服务 -->
      <service 
          android:name="com.baidu.location.f" 
          android:enabled="true" 
          android:process=":remote">
	</service>
  	<!--高德定位服务-->
      <service 
  	    android:enabled="true"
  	    android:name="com.icoding.locator.amap.AMapLocatorService">
  	  </service>
  	<!--百度定位服务-->
      <service 
  	    android:enabled="true"
  	    android:name="com.icoding.locator.baidu.BaiduLocatorService">
  	  </service>
      
      <activity
          android:name=".MainActivity"
          android:label="@string/app_name" >
          <intent-filter>
              <action android:name="android.intent.action.MAIN" />
              <category android:name="android.intent.category.LAUNCHER" />
          </intent-filter>
      </activity>
  </application>
  ```
  使用定位功能时，可启动AMapLoactorService(高德定位)，或BaiduLocatorService(百度)     
  服务,并注册注册BroadcastReceiver以接收位置信息
  ```java
Intent intent = new Intent(this,BaiduLocatorService.class);
//设置是否使用GPS定位
intent.putExtra(LocatorService.EXTRA_ENABLE_GPS, true);
startService(intent);
/** 定时定位的写法
Intent intent = new Intent(this,BaiduLocatorService.class);
intent.putExtra(LocatorService.EXTRA_ENABLE_GPS, true);
PendingIntent pending = PendingIntent.getService(getApplicationContext(), 0, 
	intent, PendingIntent.FLAG_UPDATE_CURRENT);
AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
long triggerAtMillis = System.currentTimeMillis();
alarm.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtMillis, 30 * 1000, pending);//30秒定位一次
**/
//注册BroadcastReceiver

IntentFilter filter = new IntentFilter();
filter.addAction(LocatorService.BROADCAST_LOCATION_START);
filter.addAction(LocatorService.BROADCAST_LOCATION_STOP);
filter.addAction(LocatorService.BROADCAST_LOCATION_ERROR);
filter.addAction(LocatorService.BROADCAST_LOCATION_RECEIVED);
LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(receiver, filter);

//receiver的写法
private BroadcastReceiver receiver = new BroadcastReceiver() {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if(LocatorService.BROADCAST_LOCATION_START.equals(action)){
			//Log.d(DEBUG, "定位服务开始定位");
		}else if(LocatorService.BROADCAST_LOCATION_STOP.equals(action)){
			//Log.d(DEBUG, "定位服务停止定位");
		}else if(LocatorService.BROADCAST_LOCATION_ERROR.equals(action)){
			//Log.e(DEBUG, "定位服务出错 error:"+  intent.getIntExtra(LocatorService.EXTRA_ERROR, 0));
		}else if(LocatorService.BROADCAST_LOCATION_RECEIVED.equals(action)){
			//Log.d(DEBUG, "定位服务已收到位置信息");
			ILocation location = (ILocation) intent.getSerializableExtra(LocatorService.EXTRA_LOCATION);
			StringBuffer sb = new StringBuffer(256);
			sb.append("time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : ");
			sb.append(location.getErrorCode());
			sb.append("\ncountry name : ");
			sb.append(location.getCountry());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			sb.append("\naddress: ");
			sb.append(location.getAddress());
			sb.append("\nprovader:");//gps ,lbs
			sb.append(location.getProvider());
			sb.append("\nLocator:");
			sb.append(location.getLocator());
			Log.i(DEBUG,sb.toString());
		}
	}
};
	
```
	
	

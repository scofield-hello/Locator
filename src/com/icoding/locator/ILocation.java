package com.icoding.locator;

import java.io.Serializable;

public class ILocation implements Serializable{

	private static final long serialVersionUID = -8126171534564957161L;
	//返回定位时间
	private String time;
	//返回定位位置的纬度坐标。
	private Double latitude;
	//返回定位位置的经度坐标。
	private Double longitude;
	//返回海拔高度，如果返回0.0，说明没有返回海拔高度，只有在GPS定位时才返回值。
	private Double altitude;
	//返回定位位置的提供者名称
	private String provider;
	//返回地址的详细描述，包括省、市、区和街道。
	private String address;
	//返回定位到的室内地图的楼层，如果不在室内或者无数据，则返回默认值null。
	private String floor;
	//返回定位速度 ，单位：米/秒，如果此位置不具有速度，则返回0.0 。只有在GPS定位时才有返回值。
	private float speed;
	//返回定位方位（方向），以度为单位，与正北方向顺时针的角度。只有在GPS定位时才返回值。
	private float bearing;
	//返回定位精度半径（单位米）
	private float accuracy;
	//返回定位信息中所属国家名称，如“中国”。
	private String country;
	//返回定位信息中所属省名称，如“河北省”，只有在网络定位时才返回值。
	private String province;
	//返回定位信息中所属城市名称，如“北京市”，只有在网络定位时才返回值。
	private String city;
	//返回定位信息中所属区（县）名称，如“朝阳区”，只有在网络定位时才返回
	private String district;
	//返回定位信息中的城市编码
	private String cityCode;
	//返回街道和门牌号，只有在网络定位才返回值(仅限中国大陆、香港、澳门)。
	private String street;
	//返回定位信息中道路名称,如“阜荣街”。
	private String road;
	//返回定位信息中的区域编码，只有在网络定位时才返回值(仅支持中国大陆、香港、澳门)。
	private String adCode;
	private int errorCode;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getAltitude() {
		return altitude;
	}
	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public float getBearing() {
		return bearing;
	}
	public void setBearing(float bearing) {
		this.bearing = bearing;
	}
	public float getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getRoad() {
		return road;
	}
	public void setRoad(String road) {
		this.road = road;
	}
	public String getAdCode() {
		return adCode;
	}
	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}
}

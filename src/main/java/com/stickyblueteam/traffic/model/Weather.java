package com.stickyblueteam.traffic.model;

/**
 * Created by Muhammad Zubair <mzubair.ca> on 12/5/2018.
 */

public class Weather 
{
  
private String longitude;
private String lat;
private float temp;
private String hour;
  
  public Weather(String longitude, String lat, float temp, String hour)
  {
    this.longitude = longitude;
    this.lat = lat;
    this.temp = temp;
    this.hour = hour;
  }
  
  public String getLongitude()
  {
    return this.longitude;
  }
  
  public String getLatitude()
  {
    return this.latitude;
  }
  
  public float getTemp()
  {
    return this.temp;
  }
  
   public String getHour()
  {
    return this.hour;
  }
  
  public void setHour(String newHour)
  {
    this.hour = newHour;
  }
  
  public void setLongitude(String newLongitude)
  {
    this.longitude = newLongitude;
  }
  
 public void setLatitude(String newLatitude)
  {
    this.latitude = newLatitude;
  }
  
  public void setTemp(float newTemp)
  {
    this.temp = newTemp;
  }
  
 
}

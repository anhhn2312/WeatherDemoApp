package com.anhhn.weatherdemoapp.pojo.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.anhhn.weatherdemoapp.pojo.remote.WeatherResponse;

/**
 * Created by Andy Ha on 5/8/18.
 */
public class WeatherDTO implements Parcelable {
    private String name;
    private int id;
    private String weather;
    private String weatherDescription;
    private String icon;
    private int currentTemp;
    private int minTemp;
    private int maxTemp;
    private long sunrise;
    private long sunset;
    private double pressure;
    private int humid;
    private double windSpeed;

    public static WeatherDTO convert(WeatherResponse weatherResponse){
        if(weatherResponse == null) return null;

        WeatherDTO weatherDTO = new WeatherDTO();
        weatherDTO.setId(weatherResponse.id);
        weatherDTO.setName(weatherResponse.name);
        weatherDTO.setSunrise(weatherResponse.sys.sunrise);
        weatherDTO.setSunset(weatherResponse.sys.sunset);
        weatherDTO.setWeather(weatherResponse.weathers.get(0).main);
        weatherDTO.setWeatherDescription(weatherResponse.weathers.get(0).description);
        weatherDTO.setIcon(weatherResponse.weathers.get(0).icon);
        weatherDTO.setCurrentTemp((int)weatherResponse.main.temperature);
        weatherDTO.setMinTemp((int)weatherResponse.main.minTemp);
        weatherDTO.setMaxTemp((int)weatherResponse.main.maxTemp);
        weatherDTO.setWindSpeed(weatherResponse.wind.speed);
        weatherDTO.setHumid((int)weatherResponse.main.humid);
        weatherDTO.setPressure(weatherResponse.main.pressure);

        return weatherDTO;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(int currentTemp) {
        this.currentTemp = currentTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getHumid() {
        return humid;
    }

    public void setHumid(int humid) {
        this.humid = humid;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.id);
        dest.writeString(this.weather);
        dest.writeString(this.weatherDescription);
        dest.writeString(this.icon);
        dest.writeInt(this.currentTemp);
        dest.writeInt(this.minTemp);
        dest.writeInt(this.maxTemp);
        dest.writeLong(this.sunrise);
        dest.writeLong(this.sunset);
        dest.writeDouble(this.pressure);
        dest.writeInt(this.humid);
        dest.writeDouble(this.windSpeed);
    }

    public WeatherDTO() {
    }

    protected WeatherDTO(Parcel in) {
        this.name = in.readString();
        this.id = in.readInt();
        this.weather = in.readString();
        this.weatherDescription = in.readString();
        this.icon = in.readString();
        this.currentTemp = in.readInt();
        this.minTemp = in.readInt();
        this.maxTemp = in.readInt();
        this.sunrise = in.readLong();
        this.sunset = in.readLong();
        this.pressure = in.readDouble();
        this.humid = in.readInt();
        this.windSpeed = in.readDouble();
    }

    public static final Parcelable.Creator<WeatherDTO> CREATOR = new Parcelable.Creator<WeatherDTO>() {
        @Override
        public WeatherDTO createFromParcel(Parcel source) {
            return new WeatherDTO(source);
        }

        @Override
        public WeatherDTO[] newArray(int size) {
            return new WeatherDTO[size];
        }
    };
}

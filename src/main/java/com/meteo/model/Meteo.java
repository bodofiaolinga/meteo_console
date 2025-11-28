package com.meteo.model;  public class Meteo {
    private String description;
    private double temperature;
    private int humidite;
    private String city;

    private double longitude;

    public double getLongitude() {
        return longitude;
    }

    public Meteo(String description, double temperature, int humidite, String city, double longitude) {
        this.description = description;
        this.temperature = temperature;
        this.humidite =  humidite;
        this.city = city;
        this.longitude=longitude;
    }
    public String getDescription() {return description;     }
    public double getTemperature() {         return temperature;     }
    public int getHumidite() {         return humidite;     }
    public String getCity() {         return city;     }
    @Override
    public String toString(){
        return "description :"+ description + "\n" +
                "temperature :"+ temperature+ "°C"+ "\n"+
                "humiditer :" + humidite + "\n" +
                "ville :" + city+"\n" +
                "longitude:" +longitude;

    }
}

package com.sacp.member.client.response;

import java.io.Serializable;

public class AddressResponse implements Serializable {
    private String country;
    private String city;
    private String town;
    private String ip;

    public AddressResponse() {
    }

    public AddressResponse(String country, String city, String town, String ip) {
        this.country = country;
        this.city = city;
        this.town = town;
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "AddressResponse{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", town='" + town + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}

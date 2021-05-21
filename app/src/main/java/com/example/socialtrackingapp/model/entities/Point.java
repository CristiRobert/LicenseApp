package com.example.socialtrackingapp.model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Point implements Serializable {

    private double longitude;
    private double latitude;
    private double altitude;
    private long registerDateTime;

    public Point( double longitude, double latitude, double altitude ) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.registerDateTime = System.currentTimeMillis();
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude( double longitude ) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude( double latitude ) {
        this.latitude = latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude( double altitude ) {
        this.altitude = altitude;
    }

    public long getRegisterDateTime() {
        return registerDateTime;
    }

    public void setRegisterDateTime( long registerDateTime ) {
        this.registerDateTime = registerDateTime;
    }

    @Override
    public boolean equals( Object o ) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Point point = (Point)o;
        return Double.compare(point.longitude, longitude) == 0 &&
                Double.compare(point.latitude, latitude) == 0 &&
                Double.compare(point.altitude, altitude) == 0 &&
                registerDateTime == point.registerDateTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude, altitude, registerDateTime);
    }
}

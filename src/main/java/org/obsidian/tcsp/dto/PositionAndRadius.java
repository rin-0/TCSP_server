package org.obsidian.tcsp.dto;

/**
 * 用于Routepoint的数据库查询条件
 * @Author Rin
 * @Date 2017/11/30
 */
public class PositionAndRadius {
    double latitude;
    double longitude;
    double radius;

    public PositionAndRadius() {
    }

    public PositionAndRadius(double latitude, double longitude, double radius) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}

package com.example.segproject;

public class Point3D {
    private Integer x;
    private Integer y;
    private Integer z;
    
    public Point3D(Integer x, Integer y, Integer z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public void setZ(Integer z) {
        this.z = z;
    }

    public Integer getX() {
        return (this.x);
    }

    public Integer getY() {
        return (this.y);
    }

    public Integer getZ() {
        return (this.z);
    }
}
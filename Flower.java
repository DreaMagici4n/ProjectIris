/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package model;

import java.util.Objects;


/**
 *
 * @author lucas
 */
public class Flower {
    protected int id;
    protected double sepal_length;
    protected double petal_length;
    protected double sepal_width;
    protected double petal_width;
    protected String class_;

    public Flower() {
    }

    
    public Flower(int id, double sepal_length, double petal_length, double sepal_with, double petal_with, String class_) {
        this.id = id;
        this.sepal_length = sepal_length;
        this.petal_length = petal_length;
        this.sepal_width = sepal_with;
        this.petal_width = petal_with;
        this.class_ = class_;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Flower other = (Flower) obj;
        return this.id == other.id;
    }


    
    public double getSepal_length() {
        return sepal_length;
    }

    public void setSepal_length(double sepal_length) {
        this.sepal_length = sepal_length;
    }

    public double getPetal_length() {
        return petal_length;
    }

    public void setPetal_length(double petal_length) {
        this.petal_length = petal_length;
    }

    public double getSepal_width() {
        return sepal_width;
    }

    public void setSepal_width(double sepal_with) {
        this.sepal_width = sepal_with;
    }

    public double getPetal_width() {
        return petal_width;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPetal_width(double petal_with) {
        this.petal_width = petal_with;
    }

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }
    
    
    
}

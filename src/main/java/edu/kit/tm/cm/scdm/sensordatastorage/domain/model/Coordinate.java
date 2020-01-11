package edu.kit.tm.cm.scdm.sensordatastorage.domain.model;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Coordinate {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "latitude", nullable = false)
    private double latitude;
    @Column(name = "longitude", nullable = false)
    private double longitude;

    public Coordinate(double latitude,double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Coordinate(){

    }
}

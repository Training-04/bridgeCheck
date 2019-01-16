package group.bridge.web.entity;

import javax.persistence.*;

@Entity(name="sensors")
public class Sensor {

    @Id
    @Column(name="sensor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@OneToMany(cascade =CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "record_id" )
    private int sensor_id;
    @Column(name="sensor_name")
    private String sensor_name;
    @Column(name="bridge_id")
    //@ManyToOne
    private int bridge_id;
    @Column(name="parameter_unit")
    private String parameter_unit;
    @Column(name="threshold")
    private double threshold;

    public int getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(int sensor_id) {
        this.sensor_id = sensor_id;
    }

    public String getSensor_name() {
        return sensor_name;
    }

    public void setSensor_name(String sensor_name) {
        this.sensor_name = sensor_name;
    }

    public int getBridge_id() {
        return bridge_id;
    }

    public void setBridge_id(int bridge_id) {
        this.bridge_id = bridge_id;
    }

    public String getParameter_unit() {
        return parameter_unit;
    }

    public void setParameter_unit(String parameter_unit) {
        this.parameter_unit = parameter_unit;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }
}

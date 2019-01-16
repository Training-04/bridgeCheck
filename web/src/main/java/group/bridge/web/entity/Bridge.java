package group.bridge.web.entity;

import javax.persistence.*;

@Entity(name="bridges")
public class Bridge {
    @Id
    @Column(name="bridge_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@OneToMany(cascade =CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "sensor_id" )
    private int bridge_id;
    @Column(name="bridge_name")
    private String bridge_name;
    @Column(name="design_life")
    private int design_life;
    @Column(name="bridge_length")
    private double bridge_length;
    @Column(name="bridge_info")
    private String bridge_info;

    public int getBridge_id() {
        return bridge_id;
    }

    public void setBridge_id(int bridge_id) {
        this.bridge_id = bridge_id;
    }

    public String getBridge_name() {
        return bridge_name;
    }

    public void setBridge_name(String bridge_name) {
        this.bridge_name = bridge_name;
    }

    public int getDesign_life() {
        return design_life;
    }

    public void setDesign_life(int design_life) {
        this.design_life = design_life;
    }

    public double getBridge_length() {
        return bridge_length;
    }

    public void setBridge_length(double bridge_length) {
        this.bridge_length = bridge_length;
    }

    public String getBridge_info() {
        return bridge_info;
    }

    public void setBridge_info(String bridge_info) {
        this.bridge_info = bridge_info;
    }
}

package group.bridge.web.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "bridge")
public class Bridge {
    @Id
    @Column(name = "bridge_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bridge_id;
    @Column(name = "bridge_name" )
    private String bridge_name;
    @Column(name = "design_life")
    private String design_life;
    @Column(name = "bridge_length")
    private String bridge_length;
    @Column(name = "bridge_info")
    private String bridge_info;
    @OneToMany(cascade =CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "bridge" )
    private Set<Sensor> sensor;
    public Integer getBridge_id() {
        return bridge_id;
    }

    public void setBridge_id(Integer bridge_id) {
        this.bridge_id = bridge_id;
    }

    public String getBridge_name() {
        return bridge_name;
    }

    public void setBridge_name(String bridge_name) {
        this.bridge_name = bridge_name;
    }

    public String getDesign_life() {
        return design_life;
    }

    public void setDesign_life(String design_life) {
        this.design_life = design_life;
    }

    public String getBridge_length() {
        return bridge_length;
    }

    public void setBridge_length(String bridge_length) {
        this.bridge_length = bridge_length;
    }

    public String getBridge_info() {
        return bridge_info;
    }

    public void setBridge_info(String bridge_info) {
        this.bridge_info = bridge_info;
    }

    public Set<Sensor> getSensor() {
        return sensor;
    }

    public void setSensor(Set<Sensor> sensor) {
        this.sensor = sensor;
    }
}
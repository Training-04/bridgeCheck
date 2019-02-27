package group.bridge.web.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "bridges")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "bridge_id")
public class Bridge {
    @Id
    @Column(name="bridge_id")
   // @OneToMany
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bridge_id;
    @Column(name="bridge_name")
    private String bridge_name;
    @Column(name="design_life")
    private Integer design_life;
    @Column(name="bridge_length")
    private Integer bridge_length;
    @Column(name="bridge_info")
    private String bridge_info;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY,mappedBy="bridge")
    @JSONField(serialize = false)
    private Set<Sensor> sensor;

    public Integer getBridge_id() {
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

    public Integer getDesign_life() {
        return design_life;
    }

    public void setDesign_life(Integer design_life) {
        this.design_life = design_life;
    }

    public Integer getBridge_length() {
        return bridge_length;
    }

    public void setBridge_length(Integer bridge_length) {
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

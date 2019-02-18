package group.bridge.web.entity;

import javax.persistence.*;

@Entity
@Table(name = "sensor")
public class Sensor {

    @Id
    @Column(name = "sensor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sensor_id;
//    @Column(name = "bride_id",insertable = false,updatable = false)
//    @Column(name = "bridge_id")
//    private Integer bridge_id;
    @Column(name = "sensor_name")
    private String sensor_name;
    @Column(name = "parameter_unit")
    private String parameter_unit;
    @Column(name = "threshold1")
    private Double threshold1;
    @Column(name = "threshold2")
    private Double threshold2;
//    表示将外键名设置为bridge_id
    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "bridge_id")
    private Bridge bridge;//所属桥梁

    public Integer getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(Integer sensor_id) {
        this.sensor_id = sensor_id;
    }


    public String getSensor_name() {
        return sensor_name;
    }

    public void setSensor_name(String sensor_name) {
        this.sensor_name = sensor_name;
    }

    public String getParameter_unit() {
        return parameter_unit;
    }

    public void setParameter_unit(String parameter_unit) {
        this.parameter_unit = parameter_unit;
    }

    public Double getThreshold1() {
        return threshold1;
    }

    public void setThreshold1(Double threshold1) {
        this.threshold1 = threshold1;
    }

    public Double getThreshold2() {
        return threshold2;
    }

    public void setThreshold2(Double threshold2) {
        this.threshold2 = threshold2;
    }

    public Bridge getBridge() {
        return bridge;
    }

    public void setBridge(Bridge bridge) {
        this.bridge = bridge;
    }

//    public Integer getBridge_id() {
//        return bridge_id;
//    }
//
//    public void setBridge_id(Integer bridge_id) {
//        this.bridge_id = bridge_id;
//    }
}

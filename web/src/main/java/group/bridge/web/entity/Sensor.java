package group.bridge.web.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sensors")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "sensor_id")
public class Sensor {

    @Id
    @Column(name = "sensor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sensor_id;
    @Column(name = "sensor_name")
    private String sensor_name;
    @Column(name = "para_unit_cn")
    private String para_unit_cn;
    @Column(name = "parameter_unit")
    private String parameter_unit;
    @Column(name = "threshold1")
    private Double threshold1;
    @Column(name = "threshold2")
    private Double threshold2;
//    表示将外键名设置为bridge_id
    @ManyToOne
    @JoinColumn(name = "bridge_id")
    private Bridge bridge;//所属桥梁

//    一个传感器有多个报警记录
    @OneToMany(cascade =CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "sensor" )
    @JSONField(serialize = false)
    private Set<WarnRecord> warnRecord;

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

    public String getPara_unit_cn() {
        return para_unit_cn;
    }

    public void setPara_unit_cn(String para_unit_cn) {
        this.para_unit_cn = para_unit_cn;
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

    @JsonIgnore
    public Bridge getBridge() {
        return bridge;
    }

    public void setBridge(Bridge bridge) {
        this.bridge = bridge;
    }

    public Set<WarnRecord> getWarnRecord() {
        return warnRecord;
    }

    public void setWarnRecord(Set<WarnRecord> warnRecord) {
        this.warnRecord = warnRecord;
    }
}

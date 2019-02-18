package group.bridge.web.entity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;


@Entity(name = "WarnRecord")
public class WarnRecord{

    @Id
    @Column(name = "warnId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer warn_id;

    @Column(name = "status")
    private String status;

    @Column(name = "warn_date")
    private Date warn_date;

    @Column(name = "warn_para")
    private String warn_para;

    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "bridge_id")
    private Bridge bridge;

    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    public Integer getWarn_id() {
        return warn_id;
    }

    public void setWarn_id(Integer warn_id) {
        this.warn_id = warn_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getWarn_date() {
        return warn_date;
    }

    public void setWarn_date(Date warn_date) {
        this.warn_date = warn_date;
    }

    public String getWarn_para() {
        return warn_para;
    }

    public void setWarn_para(String warn_para) {
        this.warn_para = warn_para;
    }

    public Bridge getBridge() {
        return bridge;
    }

    public void setBridge(Bridge bridge) {
        this.bridge = bridge;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}

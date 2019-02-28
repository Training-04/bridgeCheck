package group.bridge.web.entity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;


@Entity(name = "warn_records")
public class WarnRecord {

    @Id
    @Column(name = "warn_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer warn_id;

    @Column(name = "status")
    private String status;

    @Column(name = "warn_date")
    private Date date;

    @Column(name = "relieve_date")
    private Date relieve_date;

    @Column(name = "warn_para")
    private String warn_para;


    @ManyToOne()
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getRelieve_date() {
        return relieve_date;
    }

    public void setRelieve_date(Date relieve_date) {
        this.relieve_date = relieve_date;
    }

    public String getWarn_para() {
        return warn_para;
    }

    public void setWarn_para(String warn_para) {
        this.warn_para = warn_para;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
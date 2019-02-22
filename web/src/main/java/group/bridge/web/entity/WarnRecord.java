package group.bridge.web.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "warn_records")
public class WarnRecord {

    @Id
    @Column(name = "warn_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer warn_id;
    //    报警参数
    @Column(name = "warn_para")
    private String warn_para;
    @Column(name = "warn_date")
    private Date warn_date;
    @Column(name = "relieve_date")
    private Date relieve_date;
    @Column(name = "status")
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bridge_id")
    private Bridge bridge;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    public Integer getWarn_id() {
        return warn_id;
    }

    public void setWarn_id(Integer warn_id) {
        this.warn_id = warn_id;
    }

    public String getWarn_para() {
        return warn_para;
    }

    public void setWarn_para(String warn_para) {
        this.warn_para = warn_para;
    }

    public Date getWarn_date() {
        return warn_date;
    }

    public void setWarn_date(Date warn_date) {
        this.warn_date = warn_date;
    }

    public Date getRelieve_date() {
        return relieve_date;
    }

    public void setRelieve_date(Date relieve_date) {
        this.relieve_date = relieve_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
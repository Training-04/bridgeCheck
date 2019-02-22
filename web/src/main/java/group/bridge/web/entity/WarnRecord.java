package group.bridge.web.entity;

import javax.persistence.*;
import java.util.Date;


@Entity(name = "warn_records")
public class WarnRecord {
    @Id
    @Column(name = "warn_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer warn_id;

    @Column(name = "status")
    private String status;

    @Column(name = "date")
    private Date date;

    // @Column(name = "relieve_date")
    // private Date relieve_date;

    // 报警参数= :数值+单位 > 阈值
    @Column(name = "warn_para")
    private String warn_para;

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

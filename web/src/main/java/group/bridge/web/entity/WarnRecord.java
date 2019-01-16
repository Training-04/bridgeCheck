package group.bridge.web.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "warn_record")
public class WarnRecord {

    @Id
    @Column(name = "warn_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int warn_id;

    @Column(name = "status")
    private String status;

    @Column(name = "date")
    private Date date;

    @Column(name = "bridge_id")
    private int bridge_id;

    @Column(name = "sensor_id")
    private int sensor_id;

    public int getWarn_id() {return warn_id;}
    public void setWarn_id(int warn_id) {this.warn_id = warn_id;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public Date getDate() {return date;}
    public void setDate(Date date) {this.date = date;}

    public int getBridge_id() {return bridge_id;}
    public void setBridge_id(int bridge_id) {this.bridge_id = bridge_id;}

    public int getSensor_id() {return sensor_id;}
    public void setSensor_id(int sensor_id) {this.sensor_id = sensor_id;}
}

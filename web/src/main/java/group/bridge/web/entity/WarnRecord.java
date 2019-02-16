package group.bridge.web.entity;

import javax.persistence.*;
import java.util.Date;


@Entity(name = "WarnRecord")
public class WarnRecord{
    @Id
    @Column(name = "warnId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int warnId;

    @Column(name = "status")
    private String status;

    @Column(name = "date")
    private Date date;

    @Column(name = "bridgeId")
    private int bridgeId;

    @Column(name = "sensorId")
    private int sensorId;

    public int getWarnId() {return warnId;}
    public void setWarnId(int warnId) {this.warnId = warnId;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public Date getDate() {return date;}
    public void setDate(Date date) {this.date = date;}

    public int getBridgeId() {return bridgeId;}
    public void setBridgeId(int bridgeId) {this.bridgeId = bridgeId;}

    public int getSensorId() {return sensorId;}
    public void setSensorId(int sensorId) {this.sensorId = sensorId;}


}

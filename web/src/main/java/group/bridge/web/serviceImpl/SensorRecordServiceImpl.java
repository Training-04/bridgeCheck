package group.bridge.web.serviceImpl;


import group.bridge.web.dao.SensorRecordRepository;
import group.bridge.web.entity.SensorRecord;
import group.bridge.web.entity.WarnRecord;
import group.bridge.web.service.SensorRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Date;
import java.util.List;

@Service
public class SensorRecordServiceImpl extends BaseServiceImpl<SensorRecord, Integer> implements SensorRecordService {

    private Integer record_distance = 1000;//关于数据每分钟多少条在此设置（每条时间间隔，单位 ms）

    @Autowired
    SensorRecordRepository sensorRecordRepository;

    public List<SensorRecord>  getAllDesc(){
        return sensorRecordRepository.findAllByOrderByDateDesc();
    }

    public List<SensorRecord> getAllByBridgeIDDesc(Integer bridge_id){
        return sensorRecordRepository.findAllByBridgeByDateDesc(bridge_id);
    }

    public Page<SensorRecord> getPage(Pageable pageable)
    {
        return sensorRecordRepository.findAllByOrderByDateDesc(pageable);
    }

    public List<SensorRecord> getByBridgeBySensor(Integer bridge_id, String para_unit_cn)
    {
        return sensorRecordRepository.findAddByBridgeBySensorByDateDesc(bridge_id, para_unit_cn);
    }


    @Override
    protected void setRepository(){
        this.repository=sensorRecordRepository;
    }

    @Override
    public List<SensorRecord> getSensor_records(Integer bridge_id, String para_cn){
        return sensorRecordRepository.findByBridgeId(bridge_id, para_cn);
    }

    @Override
    public List<SensorRecord> getLastSensor_records(Integer bridge_id, String para_cn, Date curTime){
        long curtime = curTime.getTime();
        Date endTime = new Date(curtime + record_distance);
        return sensorRecordRepository.findByBridgeId_LIVE(bridge_id, para_cn, curTime, endTime);
    }

    @Override
    public List<SensorRecord> get15SecSensor_records(Integer bridge_id, String para_cn, Date curTime){
        long curtime = curTime.getTime();
        Date startTime = new Date(curtime - 15*record_distance);
        return sensorRecordRepository.findByBridgeID_BEFORE(bridge_id, para_cn, curTime, startTime);
    }

    @Override
    public List<SensorRecord> getWarn1Sensor_records(Date curTime){
        return sensorRecordRepository.findByThreshold1andTime(curTime);
    }

    @Override
    public List<SensorRecord> getWarn2Sensor_records(Date curTime){
        return sensorRecordRepository.findByThreshold2andTime(curTime);
    }


    public List<SensorRecord> getSensorRecordByThreshold2HasWarn(){
        return sensorRecordRepository.getSensorRecordByThreshold2HasWarn();
    }

    public List<SensorRecord> getSensorRecordByThreshold2NoWarn(){
        return sensorRecordRepository.getSensorRecordByThreshold2NoWarn();
    }


    public List<SensorRecord> getSensorRecordByThresholdHasWarn(){
        return sensorRecordRepository.getSensorRecordByThresholdHasWarn();
    }

    public List<SensorRecord> getSensorRecordByThresholdNoWarn(){
        return sensorRecordRepository.getSensorRecordByThresholdNoWarn();
    }
}

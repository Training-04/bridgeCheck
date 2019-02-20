package group.bridge.web.serviceImpl;

import group.bridge.web.dao.SensorRecordRepository;
import group.bridge.web.entity.SensorRecord;
import group.bridge.web.entity.WarnRecord;
import group.bridge.web.service.SensorRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SensorRecordServiceImpl extends BaseServiceImpl<SensorRecord, Integer> implements SensorRecordService {

    @Autowired
    SensorRecordRepository sensorRecordRepository;

    @Override
    protected void setRepository(){
        this.repository=sensorRecordRepository;
    }

    @Override
    public List<SensorRecord> getSensor_records(Integer bridge_id, String sensor_name){
        return sensorRecordRepository.findByBridgeId(bridge_id, sensor_name);
    }

    @Override
    public List<SensorRecord> getLastSensor_records(Integer bridge_id, String sensor_name, Date curTime){
        return sensorRecordRepository.findByBridgeId_LIVE(bridge_id, sensor_name, curTime);
    }
}

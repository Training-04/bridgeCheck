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

    private Integer record_distance = 1000;//关于数据每分钟多少条在此设置（每条时间间隔，单位 ms）

    @Autowired
    SensorRecordRepository sensorRecordRepository;

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
}

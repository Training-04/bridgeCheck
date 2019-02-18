package group.bridge.web.serviceImpl;

import group.bridge.web.dao.SensorRecordRepository;
import group.bridge.web.entity.SensorRecord;
import group.bridge.web.service.SensorRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SensorRecordServiceImpl extends BaseServiceImpl<SensorRecord, Integer> implements SensorRecordService {

    @Autowired
    SensorRecordRepository sensorRecordRepository;

    @Override
    protected void setRepository(){
        this.repository=sensorRecordRepository;
    }

    //@Override
    //public Double getAvg(Specification<SensorRecord> specification){
    //    return 0.0;
    //}
}

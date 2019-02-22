package group.bridge.web.serviceImpl;


import group.bridge.web.dao.SensorRecordRepository;
import group.bridge.web.entity.SensorRecord;
import group.bridge.web.service.SensorRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorRecordServiceImpl extends BaseServiceImpl<SensorRecord, Integer> implements SensorRecordService {

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
        this.repository = sensorRecordRepository;
    }

}

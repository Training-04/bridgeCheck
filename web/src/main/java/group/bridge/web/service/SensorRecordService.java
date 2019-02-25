package group.bridge.web.service;

import group.bridge.web.entity.SensorRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;
import java.util.Dictionary;
import java.util.List;

public interface SensorRecordService extends BaseService<SensorRecord, Integer>{
    //Double getAvg(Specification<SensorRecord> specification);

    //获取指定桥梁指定传感器数据
    List<SensorRecord> getSensor_records(Integer bridge_id, String para_cn);

    //获取指定桥梁的最新传感器数据
    List<SensorRecord> getLastSensor_records(Integer bridge_id, String para_cn, Date curTime);
    List<SensorRecord> getAllDesc();
    List<SensorRecord> getAllByBridgeIDDesc(Integer bridge_id);
    List<SensorRecord> getByBridgeBySensor(Integer bridge_id, String para_unit_cn);

    Page<SensorRecord> getPage(Pageable pageable);


    //获取指定桥梁15秒内传感器数据
    List<SensorRecord> get15SecSensor_records(Integer bridge_id, String para_cn, Date curTime);
}
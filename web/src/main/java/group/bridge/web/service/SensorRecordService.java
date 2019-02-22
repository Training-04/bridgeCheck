package group.bridge.web.service;

import group.bridge.web.entity.SensorRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SensorRecordService extends BaseService<SensorRecord, Integer>{
    List<SensorRecord> getAllDesc();
    List<SensorRecord> getAllByBridgeIDDesc(Integer bridge_id);
    List<SensorRecord> getByBridgeBySensor(Integer bridge_id, String para_unit_cn);

    Page<SensorRecord> getPage(Pageable pageable);
}

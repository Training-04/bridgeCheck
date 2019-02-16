package group.bridge.web.service;

import group.bridge.web.entity.SensorRecord;
import org.springframework.data.jpa.domain.Specification;


public interface SensorRecordService extends BaseService<SensorRecord, Integer>{
    Double getAvg(Specification<SensorRecord> specification);
}

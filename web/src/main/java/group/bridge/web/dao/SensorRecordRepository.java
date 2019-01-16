package group.bridge.web.dao;

import group.bridge.web.entity.SensorRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRecordRepository extends JpaRepository<SensorRecord, Integer> {
}

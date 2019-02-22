package group.bridge.web.dao;

import group.bridge.web.entity.SensorRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SensorRecordRepository extends BaseRepository<SensorRecord, Integer> {
    List<SensorRecord> findAllByOrderByDateDesc();
    Page<SensorRecord> findAllByOrderByDateDesc(Pageable pageable);
    @Query(nativeQuery = true,
        value = "SELECT record_id, date, value, sr.sensor_id FROM  sensor_records sr, sensors s WHERE sr.sensor_id = s.sensor_id AND s.bridge_id = :bridge_id ORDER BY date DESC;")
    List<SensorRecord> findAllByBridgeByDateDesc(@Param("bridge_id") Integer bridge_id);

    @Query(nativeQuery = true,
            value = "SELECT record_id, date, value, sr.sensor_id FROM  sensor_records sr, sensors s WHERE sr.sensor_id = s.sensor_id AND s.bridge_id = :bridge_id  AND s.para_unit_cn = :para_unit_cn ORDER BY date DESC;")
    List<SensorRecord> findAddByBridgeBySensorByDateDesc(@Param("bridge_id") Integer bridge_id, @Param("para_unit_cn") String para_unit_cn);
}

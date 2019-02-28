package group.bridge.web.dao;

import group.bridge.web.entity.SensorRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface SensorRecordRepository extends BaseRepository<SensorRecord, Integer> {

    List<SensorRecord> findAllByOrderByDateDesc();
    Page<SensorRecord> findAllByOrderByDateDesc(Pageable pageable);
    // 按桥梁名称查询，按日期排序,按传感器数量读取相应条数的传感器记录
    @Query(nativeQuery = true,
            value = "SELECT record_id, date, value, sr.sensor_id FROM  sensor_records sr, sensors s WHERE sr.sensor_id = s.sensor_id AND s.bridge_id = :bridge_id ORDER BY date DESC limit :sensor_num ;")
    List<SensorRecord> findAllByBridgeByDateDesc(@Param("bridge_id") Integer bridge_id, @Param("sensor_num") Integer sensor_num);

    // 按桥梁名、传感器名查询，按日期排序
    @Query(nativeQuery = true,
            value = "SELECT record_id, date, value, sr.sensor_id FROM  sensor_records sr, sensors s WHERE sr.sensor_id = s.sensor_id AND s.bridge_id = :bridge_id  AND s.para_unit_cn = :para_unit_cn ORDER BY date DESC;")
    List<SensorRecord> findAddByBridgeBySensorByDateDesc(@Param("bridge_id") Integer bridge_id, @Param("para_unit_cn") String para_unit_cn);

    //需要分类查询的数据：微应变（microstrain）、温度(temperature)、位移(displacement)、相对沉降量(relative sedimentation)、液位变化(liquidlevel change)、OBL


    //根据桥梁id查询所有传感器记录
    @Query(nativeQuery = true,
            value = "SELECT sensor_records.value FROM sensor_records,sensors WHERE sensor_records.sensor_id = sensors.sensor_id AND sensors.bridge_id = :bridge_id")
    List<SensorRecord> findByBridgeId(@Param("bridge_id") Integer bridge_id);

    //根据桥梁id查询指定传感器记录
    @Query(nativeQuery = true,
            value = "SELECT sensor_records.record_id, sensor_records.date, sensor_records.sensor_id, sensor_records.value FROM sensor_records,sensors WHERE sensor_records.sensor_id = sensors.sensor_id AND sensors.bridge_id = :bridge_id AND sensors.para_unit_cn = :para_unit_cn")
    List<SensorRecord> findByBridgeId(@Param("bridge_id") Integer bridge_id, @Param("para_unit_cn") String para_unit_cn);

    //根据桥梁id查询指定传感器记录(实时)
    @Query(nativeQuery = true,
            value = "SELECT sensor_records.record_id, sensor_records.date, sensor_records.sensor_id, sensor_records.value FROM sensor_records,sensors WHERE sensor_records.sensor_id = sensors.sensor_id AND sensors.bridge_id = :bridge_id AND sensors.para_unit_cn = :para_unit_cn AND sensor_records.date >= :curTime AND sensor_records.date < :endTime")
    List<SensorRecord> findByBridgeId_LIVE(@Param("bridge_id") Integer bridge_id, @Param("para_unit_cn") String para_unit_cn, @Param("curTime") Date curTime, @Param("endTime") Date endTime);

    //根据桥梁ID查询传感器记录（前15秒)
    @Query(nativeQuery = true,
            value = "SELECT sensor_records.record_id, sensor_records.date, sensor_records.sensor_id, sensor_records.value FROM sensor_records,sensors WHERE sensor_records.sensor_id = sensors.sensor_id AND sensors.bridge_id = :bridge_id AND sensors.para_unit_cn = :para_unit_cn AND sensor_records.date <= :curTime AND sensor_records.date > :startTime")
    List<SensorRecord> findByBridgeID_BEFORE(@Param("bridge_id") Integer bridge_id, @Param("para_unit_cn") String para_unit_cn, @Param("curTime") Date curTime, @Param("startTime") Date startTime);

    //查询1级报警传感器记录
    @Query(nativeQuery = true,
            value = "SELECT sensor_records.record_id, sensor_records.date, sensor_records.sensor_id, sensor_records.value FROM sensor_records,sensors WHERE sensor_records.sensor_id = sensors.sensor_id AND sensor_records.date > :curTime AND sensor_records.value >= sensors.threshold1 AND sensor_records.value < sensors.threshold2")
    List<SensorRecord> findByThreshold1andTime(@Param("curTime") Date curTime);

    //查询2级报警传感器记录
    @Query(nativeQuery = true,
            value = "SELECT sr.record_id, sr.date, sr.sensor_id, sr.value " +
                    "FROM sensor_records as sr,sensors as s " +
                    "WHERE sr.sensor_id = s.sensor_id AND sr.value >= s.threshold2 AND sr.date > " +
                    "(select warn_date from warn_records  order by warn_date asc limit 1) " +
                    "order by sr.date asc ")
    List<SensorRecord> findByThreshold2andTime(@Param("curTime") Date curTime);


    @Query(nativeQuery = true,
            value = "SELECT sr.record_id, sr.date, sr.sensor_id, sr.value " +
                    "FROM sensor_records as sr,sensors as s " +
                    "WHERE sr.sensor_id = s.sensor_id AND sr.value >= s.threshold2 AND sr.date > " +
                    "(select warn_date from warn_records  order by warn_date desc limit 1) " +
                    "order by sr.date asc ")
    List<SensorRecord> getSensorRecordByThreshold2HasWarn();
    @Query(nativeQuery = true,
            value = "SELECT sr.record_id, sr.date, sr.sensor_id, sr.value " +
                    "FROM sensor_records as sr,sensors as s " +
                    "WHERE sr.sensor_id = s.sensor_id AND sr.value >= s.threshold2  " +
                    "order by sr.date asc ")
    List<SensorRecord> getSensorRecordByThreshold2NoWarn();

    @Query(nativeQuery = true,
            value = "SELECT sr.record_id, sr.date, sr.sensor_id, sr.value " +
                    "FROM sensor_records as sr,sensors as s " +
                    "WHERE sr.sensor_id = s.sensor_id AND sr.value >= s.threshold1 AND sr.value < s.threshold2 " +
                    "AND sr.date > (select warn_date from warn_records  order by warn_date desc limit 1) " +
                    "order by sr.date asc ")
    List<SensorRecord> getSensorRecordByThresholdHasWarn();
    @Query(nativeQuery = true,
            value = "SELECT sr.record_id, sr.date, sr.sensor_id, sr.value " +
                    "FROM sensor_records as sr,sensors as s " +
                    "WHERE sr.sensor_id = s.sensor_id AND sr.value >= s.threshold1 AND sr.value < s.threshold2  " +
                    "order by sr.date asc ")
    List<SensorRecord> getSensorRecordByThresholdNoWarn();

}


package group.bridge.web.dao;

import group.bridge.web.entity.SensorRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface SensorRecordRepository extends BaseRepository<SensorRecord, Integer> {

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
            value = "SELECT sensor_records.value FROM sensor_records,sensors WHERE sensor_records.sensor_id = sensors.sensor_id AND sensors.bridge_id = :bridge_id AND sensors.sensor_name = :sensor_name AND sensor_records.date >= :curTime")
    List<SensorRecord> findByBridgeId_LIVE(@Param("bridge_id") Integer bridge_id, @Param("sensor_name") String sensor_name, @Param("curTime") Date curTime);

/*

    //根据桥梁id查询微应变参数
    @Query("SELECT record FROM sensor_records " +
            //"INNER JOIN sensor ON sensor.sensor_id = sensor_records.sensor_id " +
            "WHERE sensor.bridge_id = :bridge_id AND sensor.sensor_name = '微应变'")
    List<SensorRecord> findMicrostrainByBridgeId(@Param("bridge_id") Integer bridge_id);

    //根据桥梁id查询温度参数
    @Query("SELECT record FROM sensor_records " +
            //"INNER JOIN sensor ON sensor.sensor_id = sensor_records.sensor_id " +
            "WHERE sensor.bridge_id = :bridge_id AND sensor.sensor_name = '温度'")
    List<SensorRecord> findTemperatureByBridgeId(@Param("bridge_id") Integer bridge_id);

    //根据桥梁id查询位移参数
    @Query("SELECT record FROM sensor_records " +
            //"INNER JOIN sensor ON sensor.sensor_id = sensor_records.sensor_id " +
            "WHERE sensor.bridge_id = :bridge_id AND sensor.sensor_name = '位移'")
    List<SensorRecord> findDisplacementByBridgeId(@Param("bridge_id") Integer bridge_id);

    //根据桥梁id查询相对沉降量参数
    @Query("SELECT record FROM sensor_records " +
            //"INNER JOIN sensor ON sensor.sensor_id = sensor_records.sensor_id " +
            "WHERE sensor.bridge_id = :bridge_id AND sensor.sensor_name = '相对沉降量'")
    List<SensorRecord> findRelativeSedimentationByBridgeId(@Param("bridge_id") Integer bridge_id);

    //根据桥梁id查询液位变化参数
    @Query("SELECT record FROM sensor_records " +
            //"INNER JOIN sensor ON sensor.sensor_id = sensor_records.sensor_id " +
            "WHERE sensor.bridge_id = :bridge_id AND sensor.sensor_name = '液位变化'")
    List<SensorRecord> findLiquidchangeByBridgeId(@Param("bridge_id") Integer bridge_id);

    //根据桥梁id查询OBL参数
    @Query("SELECT record FROM sensor_records " +
            //"INNER JOIN sensor ON sensor.sensor_id = sensor_records.sensor_id " +
            "WHERE sensor.bridge_id = :bridge_id AND sensor.sensor_name = 'OBL'")
    List<SensorRecord> findOBLByBridgeId(@Param("bridge_id") Integer bridge_id);

    */
}

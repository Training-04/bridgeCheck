package group.bridge.web.service;

import group.bridge.web.entity.SensorRecord;

import java.util.List;

public interface SensorRecordService {
    SensorRecord addSensorRecord(SensorRecord sensorRecord);
    void deleteSensorRecord(SensorRecord srID);
    List<SensorRecord> getAllSensorRecord();
    SensorRecord updateSensorRecord(SensorRecord sensorRecord);
    SensorRecord getSensorRecordByID(Integer srID);
}

package group.bridge.web.service;

import group.bridge.web.entity.Sensor;

import java.util.List;

public interface SensorService extends BaseService<Sensor, Integer> {

    List<Sensor> getByName(String sensor_name);
    Integer getSensorCount();
}

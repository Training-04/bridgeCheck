package group.bridge.web.service;

import group.bridge.web.entity.Sensor;

public interface SensorService extends BaseService<Sensor, Integer> {

    Sensor addSensor(Sensor sensor);

}

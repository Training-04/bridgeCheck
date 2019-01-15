package group.bridge.web.service;


import group.bridge.web.entity.Sensor;

import java.util.List;

public interface SensorService extends BaseService<Sensor, Integer> {
    //返回所有传感器信息
    List<Sensor> getAllSensor();
    Sensor addSensor(Sensor sensor);
    //修改传感器的阈值
    Sensor updateSensor(Sensor sensor);
    Sensor getSensorById(Integer sensor_id);
}

package group.bridge.web.serviceImpl;

import group.bridge.web.dao.SensorRepository;
import group.bridge.web.entity.Sensor;
import group.bridge.web.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorServiceImpl extends BaseServiceImpl<Sensor,Integer> implements SensorService {
    @Autowired
    SensorRepository sensorRepository;

    @Override
    public List<Sensor> getAllSensor() {
        return sensorRepository.findAll();
    }

    @Override
    public Sensor addSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @Override
    protected void setRepository() {
        this.repository = sensorRepository;
    }

    @Override
    public Sensor updateSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @Override
    public Sensor getSensorById(Integer sensor_id) {
        return sensorRepository.findById(sensor_id).get();
    }
}

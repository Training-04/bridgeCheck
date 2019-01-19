package group.bridge.web.serviceImpl;


import group.bridge.web.dao.SensorRepository;
import group.bridge.web.entity.Sensor;
import group.bridge.web.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SensorServiceImpl extends BaseServiceImpl<Sensor, Integer> implements SensorService {

    @Autowired
    SensorRepository sensorRepository;


    @Override
    protected void setRepository() {this.repository = sensorRepository;}
}

package group.bridge.web.controller;

import group.bridge.web.entity.Sensor;
import group.bridge.web.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sensor")
public class DemoRestController2 {
    @Autowired
    private SensorService sensorService;
    @RequestMapping("/add")
    private Sensor addSensor(Sensor sensor){return sensorService.addSensor(sensor);}
}

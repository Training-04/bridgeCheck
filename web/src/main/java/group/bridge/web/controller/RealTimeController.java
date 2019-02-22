package group.bridge.web.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import group.bridge.web.entity.Bridge;
import group.bridge.web.entity.SensorRecord;
import group.bridge.web.service.BridgeService;
import group.bridge.web.service.SensorRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RealTimeController {
    @Autowired
    public BridgeService bridgeService;
    @Autowired
    public SensorRecordService sensorRecordService;

    @RequestMapping("ajax_realtime")
    public String ajax_realtime(@RequestBody JSONObject params)
    {
        Integer bridge_id = params.getInteger("bridge");
        List<SensorRecord> lists = sensorRecordService.getAllByBridgeIDDesc(bridge_id);

        String json = JSON.toJSONString(lists, SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue);
       // String json = JSON.toJSONString(lists);
        System.out.println(json);
        return json;
    }

    @RequestMapping("ajax_statistic_datalist")
    public String ajax_statistic_datalist(@RequestBody JSONObject params)
    {
        Integer bridge_id = params.getInteger("bridge");
        String para_unit_cn = params.getString("para_unit_cn");
        List<SensorRecord> lists = sensorRecordService.getByBridgeBySensor(bridge_id, para_unit_cn);

        String json = JSON.toJSONString(lists, SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue);
        System.out.println(json);
        return json;
    }


    @RequestMapping("/ajax_test")
    public String Ajax_test(){
        List<Bridge> lists = bridgeService.getAll();
        Bridge b = bridgeService.get(1);
        String json= JSON.toJSONString(lists);
        System.out.println(json);
        return json;
    }


}
/*


[{"date":1547804026000,"record_id":14,
        "sensor":{"bridge":{"bridge_id":1,"bridge_info":"武汉长江大桥","bridge_length":3418,"bridge_name":"WH001","design_life":120,"sensor":[
                {"bridge":{"$ref":"$[0].sensor.bridge"},"para_unit_cn":"微应变","parameter_unit":"με","sensor_id":2,"sensor_name":"VWS-491","threshold1":1000,"threshold2":1450},
                                {"$ref":"$[0].sensor"}]},"para_unit_cn":"微应变","parameter_unit":"με","sensor_id":6,"sensor_name":"VWS-481","threshold1":850,"threshold2":1420},"value":116},
        {"date":1547789626000,"record_id":10,"sensor":{"$ref":"$[0].sensor.bridge.sensor[0]"},"value":133},
        {"date":1547623366000,"record_id":6,"sensor":{"$ref":"$[0].sensor"},"value":120},
        {"date":1547605658000,"record_id":2,"sensor":{"$ref":"$[0].sensor.bridge.sensor[0]"},"value":122}]


[{"date":1547804026000,"record_id":14,"sensor":{"bridge":{"bridge_id":1,"bridge_info":"武汉长江大桥","bridge_length":3418,"bridge_name":"WH001","design_life":120},"para_unit_cn":"微应变","parameter_unit":"με","sensor_id":6,"sensor_name":"VWS-481","threshold1":850,"threshold2":1420},"value":116},
        {"date":1547789626000,"record_id":10,"sensor":{"bridge":{"bridge_id":1,"bridge_info":"武汉长江大桥","bridge_length":3418,"bridge_name":"WH001","design_life":120},"para_unit_cn":"微应变","parameter_unit":"με","sensor_id":2,"sensor_name":"VWS-491","threshold1":1000,"threshold2":1450},"value":133},
        {"date":1547623366000,"record_id":6,"sensor":{"bridge":{"bridge_id":1,"bridge_info":"武汉长江大桥","bridge_length":3418,"bridge_name":"WH001","design_life":120},"para_unit_cn":"微应变","parameter_unit":"με","sensor_id":6,"sensor_name":"VWS-481","threshold1":850,"threshold2":1420},"value":120},
        {"date":1547605658000,"record_id":2,"sensor":{"bridge":{"bridge_id":1,"bridge_info":"武汉长江大桥","bridge_length":3418,"bridge_name":"WH001","design_life":120},"para_unit_cn":"微应变","parameter_unit":"με","sensor_id":2,"sensor_name":"VWS-491","threshold1":1000,"threshold2":1450},"value":122}] */

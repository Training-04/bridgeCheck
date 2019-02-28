package group.bridge.web.controller;


import group.bridge.web.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import group.bridge.web.entity.Bridge;
import group.bridge.web.entity.SensorRecord;
import group.bridge.web.service.BridgeService;
import group.bridge.web.service.SensorRecordService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RealTImeDataListController {
    @Autowired
    public BridgeService bridgeService;
    @Autowired
    public SensorRecordService sensorRecordService;
    @Autowired
    public SensorService sensorService;

    @RequestMapping("ajax_realtime")
    public String ajax_realtime(@RequestBody JSONObject params)
    {
        Integer bridge_id = params.getInteger("bridge");
        Integer sensor_num = sensorService.getSensorCount();

        List<SensorRecord> lists = sensorRecordService.getAllByBridgeIDDesc(bridge_id,sensor_num);

        // 仅读取前12个
     //   lists = lists.subList(0, 12);

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
     //   String json= JSON.toJSONString(lists);
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
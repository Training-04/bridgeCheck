package group.bridge.web.service;

import group.bridge.web.entity.SensorRecord;
import group.bridge.web.entity.WarnRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Date;
import java.util.List;

public interface WarnRecordService extends BaseService<WarnRecord, Integer> {
//    WarnRecord addWarnRecord(WarnRecord warnRecord);
//    void deleteWarnRecord(Integer wrID);
//    List<WarnRecord> getAllWarnRecord();
//    WarnRecord updateWarnRecord(WarnRecord warnRecord);
//    WarnRecord getWarnRecordByID(Integer wrID);

    List<WarnRecord> getWarn_record();
    List<WarnRecord> getRelieveWarn_record();
    List<WarnRecord> getWarnRecord();

    Page<WarnRecord> getPage(Pageable pageable);

    //插入报警记录
    void insertWarn_record(Date warn_date, String status, String warn_para, Integer sensor_id);

    Long getNotRelieveCount();

}

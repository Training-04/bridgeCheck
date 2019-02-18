package group.bridge.web.service;

import group.bridge.web.entity.WarnRecord;


import java.util.List;

public interface WarnRecordService extends BaseService<WarnRecord, Integer> {
//    WarnRecord addWarnRecord(WarnRecord warnRecord);
//    void deleteWarnRecord(Integer wrID);
//    List<WarnRecord> getAllWarnRecord();
//    WarnRecord updateWarnRecord(WarnRecord warnRecord);
//    WarnRecord getWarnRecordByID(Integer wrID);

    List<WarnRecord> getWarn_record();
    List<WarnRecord> getRelieveWarn_record();
}

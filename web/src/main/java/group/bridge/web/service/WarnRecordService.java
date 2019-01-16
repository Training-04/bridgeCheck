package group.bridge.web.service;

import group.bridge.web.entity.WarnRecord;


import java.util.List;

public interface WarnRecordService {
    WarnRecord addWarnRecord(WarnRecord warnRecord);
    void deleteWarnRecord(Integer wrID);
    List<WarnRecord> getAllWarnRecord();
    WarnRecord updateWarnRecord(WarnRecord warnRecord);
    WarnRecord getWarnRecordByID(Integer wrID);
}

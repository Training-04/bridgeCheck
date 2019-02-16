package group.bridge.web.serviceImpl;

import group.bridge.web.dao.WarnRecordRepository;
import group.bridge.web.entity.WarnRecord;
import group.bridge.web.service.WarnRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarnRecordImpl extends BaseServiceImpl<WarnRecord, Integer> implements WarnRecordService {
    @Autowired
    WarnRecordRepository warnRecordRepository;


    @Override
    protected void setRepository() {
        this.repository = warnRecordRepository;
    }
}


package group.bridge.web.serviceImpl;

import group.bridge.web.dao.WarnRecordRepository;
import group.bridge.web.entity.WarnRecord;
import group.bridge.web.service.WarnRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarnRecordServiceImpl extends BaseServiceImpl<WarnRecord, Integer> implements WarnRecordService {
    @Autowired
    WarnRecordRepository warnRecordRepository;

    public Page<WarnRecord> getPage(Pageable pageable){
        return warnRecordRepository.findAllByOrderByDateDesc(pageable);
    }
    @Override
    protected void setRepository() {
        this.repository = warnRecordRepository;
    }

    public List<WarnRecord> getWarn_record(){
        return warnRecordRepository.findAllByStatus("未解除");
    }

    public List<WarnRecord> getRelieveWarn_record(){
        return warnRecordRepository.findAllByStatus("已解除");
    }
}


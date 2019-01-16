package group.bridge.web.serviceImpl;

import group.bridge.web.dao.Warn_recordRepository;
import group.bridge.web.entity.Warn_record;
import group.bridge.web.service.Warn_recordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Warn_recordServiceImpl extends BaseServiceImpl<Warn_record,Integer> implements Warn_recordService {
    @Autowired
    Warn_recordRepository wrRepository;

    @Override
    public Warn_record addWarn_record(Warn_record wr) {
        return wrRepository.save(wr);
    }

    @Override
    protected void setRepository() {
        this.repository = wrRepository;
    }
}

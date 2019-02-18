package group.bridge.web.serviceImpl;

import group.bridge.web.dao.BridgeRepository;
import group.bridge.web.entity.Bridge;
import group.bridge.web.service.BridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BridgeServiceImpl extends BaseServiceImpl<Bridge,Integer> implements BridgeService {
    @Autowired
    private BridgeRepository bridgeRepository;

    @Override
    protected void setRepository() {
        this.repository = bridgeRepository;
    }
}

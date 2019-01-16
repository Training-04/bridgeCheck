package group.bridge.web.serviceImpl;

import group.bridge.web.dao.BridgeRepository;
import group.bridge.web.entity.Bridge;
import group.bridge.web.service.BridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BridgeServiceImpl implements BridgeService {

    @Autowired
    BridgeRepository bridgeRepository;

    @Override
    public Bridge addBridge(Bridge bridge) { return bridgeRepository.save(bridge); }

    public void deleteBridge(Integer bID) { bridgeRepository.deleteById(bID); }
    @Override
    public List<Bridge> getAllBridge(){
        return bridgeRepository.findAll();
    }

    @Override
    public Bridge updateBridge(Bridge student){
        return bridgeRepository.save(student);
    }

    @Override
    public  Bridge getBridgeByID(Integer bID){
        return bridgeRepository.findById(bID).get();
    }
}

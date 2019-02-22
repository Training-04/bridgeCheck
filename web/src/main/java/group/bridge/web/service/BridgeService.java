package group.bridge.web.service;

import group.bridge.web.entity.Bridge;

import java.util.List;

public interface BridgeService extends BaseService<Bridge, Integer>  {
    List<Bridge> getByName(String bridge_name);
}

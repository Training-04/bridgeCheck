package group.bridge.web.service;

import group.bridge.web.entity.Permission;
import group.bridge.web.entity.User;

import java.util.List;

public interface PermissionService extends BaseService<Permission,Integer> {

    Permission updatePer(Permission permission);
    Permission getPerByID(Integer permission_id);
    List<Permission> findPerByName(String permission_name);
    //List<Permission> findPerName();
}

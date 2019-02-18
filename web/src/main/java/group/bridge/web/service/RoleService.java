package group.bridge.web.service;

import group.bridge.web.entity.Permission;
import group.bridge.web.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService extends BaseService<Role,Integer> {
    Role updateRole(Role role);
    Role getRoleByID(Integer role_id);
    List<Role> findRoleByName(String role_name);
//    List<Role>findRolenameByUserId();
}

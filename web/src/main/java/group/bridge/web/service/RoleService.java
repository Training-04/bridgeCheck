package group.bridge.web.service;

import group.bridge.web.entity.Role;

import java.util.List;

public interface RoleService extends BaseService<Role,Integer> {
    Role updateRole(Role role);
    Role getRoleByID(Integer role_id);
    List<Role> findRoleByName(String role_name);
}

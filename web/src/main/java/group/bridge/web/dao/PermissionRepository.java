package group.bridge.web.dao;


import group.bridge.web.entity.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends BaseRepository<Permission,Integer> {
}

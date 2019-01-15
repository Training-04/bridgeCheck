package group.bridge.web.dao;

import group.bridge.web.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User,Integer>{
}

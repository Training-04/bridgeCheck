package group.bridge.web.service;



import group.bridge.web.entity.Role;
import group.bridge.web.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserService extends BaseService<User,Integer> {

    User updateUser(User user);
    User getUserByID(Integer user_id);
    List<User>findUserByName(String user_name);

}

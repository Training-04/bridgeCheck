package group.bridge.web.service;



import group.bridge.web.entity.User;

public interface UserService extends BaseService<User,Integer> {

    User updateUser(User user);
    User getUserByID(Integer user_id);

}

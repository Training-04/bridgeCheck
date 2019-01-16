package group.bridge.web.serviceImpl;

import group.bridge.web.dao.UserRepository;
import group.bridge.web.entity.User;
import group.bridge.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User,Integer> implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    protected void setRepository(){
        this.repository=userRepository;
    }

    @Override
    public User updateUser(User user){
        return userRepository.save(user);
    }

    @Override
    public User getUserByID(Integer user_id){
        return userRepository.findById(user_id).get();
    }

}

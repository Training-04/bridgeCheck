package group.bridge.web.serviceImpl;

import group.bridge.web.dao.UserRepository;
import group.bridge.web.entity.Role;
import group.bridge.web.entity.User;
import group.bridge.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<User> findUserByName(String user_name) {
        Specification<User> userSpecification=new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //predicate 最后要返回的查询对象
                Predicate predicate;
                //root接口代表查询的根对象，通过root来获取需要的查询条件
                //如where id=1 其中的id通过root.get("id")获取
                //一定要使用Path<T>,保证类型安全
                Path<String> name=root.get("user_name");
                //然后通过CriteriaBuilder来构造条件，= <> < >等
                //Expression在java中是一个接口，Predicate实现了这个接口
                //普通and or > < <>
                // gt >
                predicate=cb.equal(name,user_name);
                //and

                //Expression

                return predicate;
            }
        };
        return getByPredicate(userSpecification);
    }


}

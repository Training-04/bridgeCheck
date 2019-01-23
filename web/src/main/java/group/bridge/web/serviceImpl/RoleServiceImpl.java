package group.bridge.web.serviceImpl;


import group.bridge.web.dao.RoleRepository;
import group.bridge.web.entity.Permission;
import group.bridge.web.entity.Role;
import group.bridge.web.entity.User;
import group.bridge.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role,Integer> implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    protected void setRepository(){
        this.repository=roleRepository;
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleByID(Integer role_id) {
        return roleRepository.findById(role_id).get();
    }

    @Override
    public List<Role> findRoleByName(String role_name) {
        Specification<Role> roleSpecification=new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //predicate 最后要返回的查询对象
                Predicate predicate;
                //root接口代表查询的根对象，通过root来获取需要的查询条件
                //如where id=1 其中的id通过root.get("id")获取
                //一定要使用Path<T>,保证类型安全
                Path<String> name=root.get("role_name");
                //然后通过CriteriaBuilder来构造条件，= <> < >等
                //Expression在java中是一个接口，Predicate实现了这个接口
                //普通and or > < <>
                // gt >
                predicate=cb.equal(name,role_name);
                //and

                //Expression

                return predicate;
            }
        };
        return getByPredicate(roleSpecification);
    }
//    @Override
//    public List<Role> findRolenameByUserId() {
//        Specification<Role> roleSpecification=new Specification<Role>() {
//            @Override
//            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
//                //predicate 最后要返回的查询对象
//                Predicate predicate;
//                //root接口代表查询的根对象，通过root来获取需要的查询条件
//                //如where id=1 其中的id通过root.get("id")获取
//                //一定要使用Path<T>,保证类型安全
//                //Path<String> name=root.get("role_name");
//                //然后通过CriteriaBuilder来构造条件，= <> < >等
//                //Expression在java中是一个接口，Predicate实现了这个接口
//                //普通and or > < <>
//                // gt >
//                //根据user_id查询role
//                Join<User,Role> join=root.join("user",JoinType.LEFT);
//                predicate=cb.gt(join.get("user_id"),0);
//
//                //predicate=cb.equal(name,user_name);
//                //and
//
//                //Expression
//
//                return predicate;
//            }
//        };
//        return getByPredicate(roleSpecification);
//    }

}

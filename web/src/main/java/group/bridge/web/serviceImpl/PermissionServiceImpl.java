package group.bridge.web.serviceImpl;

import group.bridge.web.dao.PermissionRepository;
import group.bridge.web.entity.Permission;
import group.bridge.web.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission,Integer> implements PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    protected void setRepository(){
        this.repository=permissionRepository;
    }

    @Override
    public Permission updatePer(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission getPerByID(Integer permission_id) {
        return permissionRepository.findById(permission_id).get();
    }

    @Override
    public List<Permission> findPerByName(String permission_name) {
        Specification<Permission> perSpecification=new Specification<Permission>() {
            @Override
            public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //predicate 最后要返回的查询对象
                Predicate predicate;
                //root接口代表查询的根对象，通过root来获取需要的查询条件
                //如where id=1 其中的id通过root.get("id")获取
                //一定要使用Path<T>,保证类型安全
                Path<String> name=root.get("permission_name");
                //然后通过CriteriaBuilder来构造条件，= <> < >等
                //Expression在java中是一个接口，Predicate实现了这个接口
                //普通and or > < <>
                // gt >
                predicate=cb.equal(name,permission_name);
                //and

                //Expression

                return predicate;
            }
        };
        return getByPredicate(perSpecification);
    }
    /*

    @Override
    public List<Permission> findPerName() {
        Specification<Permission> perSpecification=new Specification<Permission>() {
            @Override
            public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //predicate 最后要返回的查询对象
                Predicate predicate;
                //root接口代表查询的根对象，通过root来获取需要的查询条件
                //如where id=1 其中的id通过root.get("id")获取
                //一定要使用Path<T>,保证类型安全
                //Path<String> name=root.get("permission_name");
                Path<Integer> id=root.get("permission_id");
                //然后通过CriteriaBuilder来构造条件，= <> < >等
                //Expression在java中是一个接口，Predicate实现了这个接口
                //普通and or > < <>
                // gt >
                predicate=cb.gt(id,0);
                //and

                //Expression

                return predicate;
            }
        };
        return getByPredicate(perSpecification);
    }
    */
}

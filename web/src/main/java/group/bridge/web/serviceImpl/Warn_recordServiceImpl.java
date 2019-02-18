package group.bridge.web.serviceImpl;

import group.bridge.web.dao.Warn_recordRepository;
import group.bridge.web.entity.Person;
import group.bridge.web.entity.Warn_record;
import group.bridge.web.service.Warn_recordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

@Service
public class Warn_recordServiceImpl extends BaseServiceImpl<Warn_record,Integer> implements Warn_recordService {
    @Autowired
    Warn_recordRepository wrRepository;

    @Override
    protected void setRepository() {
        this.repository = wrRepository;
    }

    @Override

    public List<Warn_record> getWarn_record() {
//        Specification<Warn_record> warn_recordSpecification = new Specification<Warn_record>() {
//            @Override
//            public Predicate toPredicate(Root<Warn_record> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
//                //predicate 最后要返回的查询对象
//                Predicate predicate;
//                //root接口代表查询的根对象，通过root来获取需要的查询条件
//                //如where id=1 其中的id通过root.get("id")获取
//                //一定要使用Path<T>,保证类型安全
//                Path<String> status=root.get("status");
//                //然后通过CriteriaBuilder来构造条件，= <> < >等
//                //Expression在java中是一个接口，Predicate实现了这个接口
//                //普通and or > < <>
//                // gt >
//                predicate=cb.equal(status,"未解除");
//                //and
//                return predicate;
//            }
//        };
//        return getByPredicate(warn_recordSpecification);
          return wrRepository.findAllByStatus("未解除");
    }

    @Override
    public List<Warn_record> getRelieveWarn_record() {
        Specification<Warn_record> warn_recordSpecification = new Specification<Warn_record>() {
            @Override
            public Predicate toPredicate(Root<Warn_record> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //predicate 最后要返回的查询对象
                Predicate predicate;
                //root接口代表查询的根对象，通过root来获取需要的查询条件
                //如where id=1 其中的id通过root.get("id")获取
                //一定要使用Path<T>,保证类型安全
                Path<String> status=root.get("status");
                //然后通过CriteriaBuilder来构造条件，= <> < >等
                //Expression在java中是一个接口，Predicate实现了这个接口
                //普通and or > < <>
                // gt >
                predicate=cb.equal(status,"已解除");
                //and
                return predicate;
            }
        };
        return getByPredicate(warn_recordSpecification);
    }
}

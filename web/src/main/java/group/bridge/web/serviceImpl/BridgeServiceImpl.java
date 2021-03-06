package group.bridge.web.serviceImpl;

import group.bridge.web.dao.BridgeRepository;
import group.bridge.web.entity.Bridge;
import group.bridge.web.service.BridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.*;
import java.util.List;

@Service
public class BridgeServiceImpl extends BaseServiceImpl<Bridge,Integer> implements BridgeService {
    @Autowired
    private BridgeRepository bridgeRepository;

    @Override
    protected void setRepository() {
        this.repository = bridgeRepository;
    }

    @Override
    public List<Bridge> getByName(String bridge_name) {
        Specification<Bridge> bridgeSpecification=new Specification<Bridge>() {
            @Override
            public Predicate toPredicate(Root<Bridge> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //predicate 最后要返回的查询对象
                Predicate predicate;
                //root接口代表查询的根对象，通过root来获取需要的查询条件
                //如where id=1 其中的id通过root.get("id")获取
                //一定要使用Path<T>,保证类型安全
                Path<String> name=root.get("bridge_name");
                //然后通过CriteriaBuilder来构造条件，= <> < >等
                //Expression在java中是一个接口，Predicate实现了这个接口
                //普通and or > < <>
                // gt >
                //第一个为Path<String> sensor_name=root.get("sensor_name");里边的sensor_name
                //第二个为传进来的参数sensor_name
                predicate=cb.equal(name,bridge_name);
                return predicate;
            }
        };
        return getByPredicate(bridgeSpecification);
    }
}

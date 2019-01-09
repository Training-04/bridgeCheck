package group.bridge.web.controller;

import group.bridge.web.entity.Person;
import group.bridge.web.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.*;
import java.util.List;

@RestController
public class DemoRestController {
    @Autowired
    PersonService personService;

    @RequestMapping("/list")
    public List<Person> hello(){
        Specification<Person> personSpecification=new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //predicate 最后要返回的查询对象
                Predicate predicate;
                //root接口代表查询的根对象，通过root来获取需要的查询条件
                //如where id=1 其中的id通过root.get("id")获取
                //一定要使用Path<T>,保证类型安全
                Path<Integer> id=root.get("id");
                Path<Integer> age=root.get("age");
                //然后通过CriteriaBuilder来构造条件，= <> < >等
                //Expression在java中是一个接口，Predicate实现了这个接口
                //普通and or > < <>
                // gt >
                predicate=cb.gt(id,1);
                //and
                predicate=cb.and(cb.gt(age,20),predicate);
                //Expression

                return predicate;
            }
        };
        return personService.getByPredicate(personSpecification);
    }
    @RequestMapping("/avg")
    public Double avg(){

        Specification<Person> personSpecification=new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //predicate 最后要返回的查询对象
                Predicate predicate;
                Path<Integer> age=root.get("age");
                predicate=cb.gt(age,20);
                //and
                predicate=cb.and(cb.gt(age,20),predicate);
                //Expression

                return predicate;
            }
        };
        return personService.getAvg(null);
    }
    @RequestMapping("/sum")
    public Integer sum(){
        Specification<Person> personSpecification=new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //predicate 最后要返回的查询对象
                Predicate predicate;
                Path<Integer> age=root.get("age");
                predicate=cb.gt(age,20);
                //and
                predicate=cb.and(cb.gt(age,20),predicate);
                //Expression

                return predicate;
            }
        };
        return personService.getSum(personSpecification);
    }
}

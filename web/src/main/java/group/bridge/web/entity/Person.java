package group.bridge.web.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name = "age")
    private Integer age;
//    @mappedBy注解的作用：在JPA中，在@OneToMany里加入mappedBy属性可以避免生成一张中间表。
//    mappedBy(被映射)的值应该为一的一方中该类的属性。
//     // 指定mappedBy属性表明该Person实体不控制关联关系
    @OneToMany(cascade =CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "person" )
    private Set<Car> cars = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}

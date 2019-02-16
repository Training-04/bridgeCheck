package group.bridge.web.entity;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
//    @Column(name = "person_id",insertable = false,updatable = false)
//    private Integer personId;
//    fetch = FetchType.Lazy 懒加载，作用是用到该属性的时候，才会将该属性加载到内存。
//    加载employee对象时，并不会去立即加载dept属性。如果不设置，缺省为eager，急加载。
    @ManyToOne(fetch = FetchType.LAZY ,targetEntity = Person.class)
    @JoinColumn(name="person_id",nullable = false)
    private Person person;

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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}

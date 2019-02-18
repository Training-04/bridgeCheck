package group.bridge.web.entity;


import javax.persistence.*;
import java.util.Set;

@Entity(name = "user")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int user_id;
    @Column(name="user_name")
    private String user_name;
    @Column(name = "password")
    private String password;

    //cascade = {CascadeType.ALL}拥有所有级联操作权限
    //fetch = FetchType.LAZY懒加载，加载一个实体时，定义懒加载的属性不会马上从数据库中加载
    //FetchType.EAGER：急加载，加载一个实体时，定义急加载的属性会立即从数据库中加载
    @ManyToMany(cascade = {CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(
            //用来指定中间表的名称
            name = "user_role_mapping",
            //用于指定本表在中间表的字段名称，以及中间表依赖的是本表的那个字段
            joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "user_id")},
            //用于指定对方表在中间表的字段名称，以及中间表依赖的是对方表的那个字段
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "role_id")}
    )

    //用户和角色（权限组）、用户和权限都是都是多对多都关系，
    //按照面向对象的方法来设计，用集合来记住另一方的数据。
    //但是，在权限的Dao中，查看权限的时候，没有必要列出相应的角色
    //在查看角色时，没有必要列出相应的用户。
    //所以，权限实体没必要使用Set集合来记住角色，角色实体没必要使用Set集合来记住用户！
    private Set<Role> roles;


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public int getUser_id(){return user_id;}
    public void setUser_id(int user_id){this.user_id=user_id;}

    public String getUser_name(){return user_name;}
    public void setUser_name(String user_name){this.user_name=user_name;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password=password;}
}

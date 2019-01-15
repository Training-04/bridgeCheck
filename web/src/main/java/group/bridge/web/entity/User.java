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

    @ManyToMany
    @JoinTable(
            //用来指定中间表的名称
            name = "user_role_mapping",
            //用于指定本表在中间表的字段名称，以及中间表依赖的是本表的那个字段
            joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "user_id")},
            //用于指定对方表在中间表的字段名称，以及中间表依赖的是对方表的那个字段
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "role_id")}
    )
    private Set<Role> roles;
    @ManyToMany
    @JoinTable(
                //用来指定中间表的名称
                name = "user_permission_mapping",
               //用于指定本表在中间表的字段名称，以及中间表依赖的是本表的那个字段
                joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "user_id")},
                //用于指定对方表在中间表的字段名称，以及中间表依赖的是对方表的那个字段
                inverseJoinColumns = {@JoinColumn(name = "permission_id",referencedColumnName = "permission_id")}
        )
    private Set<Permission> permissions;



    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public int getUser_id(){return user_id;}
    public void setUser_id(int user_id){this.user_id=user_id;}

    public String getUser_name(){return user_name;}
    public void setUser_name(String user_name){this.user_name=user_name;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password=password;}
}

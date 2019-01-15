package group.bridge.web.entity;



import javax.persistence.*;
import java.util.Set;

@Entity(name = "role")
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int role_id;
    @Column(name = "role_name")
    private String role_name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @ManyToMany
    @JoinTable(
            //用来指定中间表的名称
            name = "role_permission_mapping",
            //用于指定本表在中间表的字段名称，以及中间表依赖的是本表的那个字段
            joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "role_id")},
            //用于指定对方表在中间表的字段名称，以及中间表依赖的是对方表的那个字段
            inverseJoinColumns = {@JoinColumn(name = "permission_id",referencedColumnName = "permission_id")}
    )
    private Set<Permission> permissions;

    public int getRole_id(){return role_id;}
    public void setRole_id(int role_id){this.role_id=role_id;}

    public String getRole_name(){return role_name;}
    public void setRole_name(String role_name){this.role_name=role_name;}

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}

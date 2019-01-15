package group.bridge.web.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "permission")
public class Permission {
    @Id
    @Column(name = "permission_id")
    private int permission_id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String permission_name;
    @ManyToMany(mappedBy = "permissions")
    private Set<User> users;
    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;

    public int getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(int permission_id) {
        this.permission_id = permission_id;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

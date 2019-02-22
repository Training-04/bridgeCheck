package group.bridge.web.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "permission")
public class Permission {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "permission_id")
    private int permission_id;
    @Column(name = "permission_name")
    private String permission_name;
    @Column(name = "permissionE")
    private String permissionE;
    @Column
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermissionE() {
        return permissionE;
    }

    public void setPermissionE(String permissionE) {
        this.permissionE = permissionE;
    }



    //用户和角色（权限组）、用户和权限都是都是多对多都关系，
    //按照面向对象的方法来设计，用集合来记住另一方的数据。
    //但是，在权限的Dao中，查看权限的时候，没有必要列出相应的角色
    //在查看角色时，没有必要列出相应的用户。
    //所以，权限实体没必要使用Set集合来记住角色，角色实体没必要使用Set集合来记住用户！
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


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    //中间表添加数据
    public void addRoles(Role role) {
        if (roles == null){
            roles = new HashSet<Role>();
        }
        this.roles.add(role);}
}

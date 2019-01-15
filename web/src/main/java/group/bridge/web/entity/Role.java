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

    public int getRole_id(){return role_id;}
    public void setRole_id(int role_id){this.role_id=role_id;}

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getRole_name(){return role_name;}
    public void setRole_name(String role_name){this.role_name=role_name;}
}

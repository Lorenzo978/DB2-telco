package it.polimi.db2telco.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
@NamedQuery(name = "User.checkCredentials",
        query = "SELECT r FROM User r WHERE r.username = ?1 AND r.password = ?2")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String username;
    private String password;
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id_user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    @OrderBy("created_at DESC")
    private List<Order> orderList;

    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return "User{" +
                "id=" + id +
                ", username=" + username + '\'' +
                ", password=" + password + '\'' +
                ", email=" + email + '\'' +
                '}';
    }

}

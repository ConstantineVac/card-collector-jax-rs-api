package gr.konvas.cardcollector.cardcollectorrestapijaxrs.model;

import jakarta.persistence.*;

@Entity
@Table(name = "USERS", schema = "cards")
public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)                                                                        // GenerationType.IDENTITY for auto-incrementing IDs
    private Long id;
    @Column(name = "EMAIL", length = 50)
    private String email;
    @Column(name = "USERNAME", length = 50)
    private String username;
    @Column(name = "PASSWORD", length = 50)
    private String password;
    @Column(name = "FIRSTNAME", length = 50)
    private String firstname;
    @Column(name = "LASTNAME", length = 50)
    private String lastname;


    public User() {}

    public User(String email, String username, String password, String firstname, String lastname) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
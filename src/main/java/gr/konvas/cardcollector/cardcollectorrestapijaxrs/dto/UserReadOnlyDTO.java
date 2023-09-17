package gr.konvas.cardcollector.cardcollectorrestapijaxrs.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class UserReadOnlyDTO extends BaseDTO{

    private String email;
    private String username;
    private String password;
    private String firstname;
    private String lastname;

    public UserReadOnlyDTO() {
    }

    public UserReadOnlyDTO(Long id, String email, String username, String password, String firstname, String lastname) {
        this.setId(id);
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
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

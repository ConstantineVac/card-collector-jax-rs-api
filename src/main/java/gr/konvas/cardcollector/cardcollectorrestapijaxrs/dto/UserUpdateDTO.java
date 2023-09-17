package gr.konvas.cardcollector.cardcollectorrestapijaxrs.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class UserUpdateDTO extends BaseDTO{
    @NotBlank(message = "Please fill in the email.")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",
            message = "Please provide a valid email address (eg. username@gmail.com)")
    private String email;

    @NotBlank(message = "Please fill in the username")
    @Size(min = 3, max = 50, message = "username can be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Please fill in the password")
    @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\\d)(.{8,})$",
            message = "Password requirements not met. At least 1 upper and 1 lowercase, digits, special characters, length min 8 chars.")
    private String password;

    @NotBlank(message = "Please fill in the firstname")
    @Size(min = 3, max = 50, message = "firstname length must be between 3-50 chars")
    private String firstname;

    @NotBlank(message = "Please fill in the lastname")
    @Size(min = 3, max = 50, message = "lastname length must be between 3-50 chars")
    private String lastname;

    //private List<Long> cardIds;

    public UserUpdateDTO() {
    }

    public UserUpdateDTO(String email, String username, String password, String firstname, String lastname) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        //this.cardIds = cardIds;
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

//    public List<Long> getCardIds() {
//        return cardIds;
//    }
//
//    public void setCardIds(List<Long> cardIds) {
//        this.cardIds = cardIds;
//    }
}

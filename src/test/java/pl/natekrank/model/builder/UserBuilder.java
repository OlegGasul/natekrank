package pl.natekrank.model.builder;

import pl.natekrank.model.Role;
import pl.natekrank.model.User;

import java.util.List;

/**
 * Created by ievgenii on 26.09.16.
 */
public class UserBuilder implements Builder<User> {
    long id;
    private String email;
    private String password;
    private String passwordConfirm;
    private List<Role> roles;


    public UserBuilder withId(long id) {
        this.id = id;
        return this;
    }
    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }
    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }
    public UserBuilder withPasswordConfirm(String confirm) {
        this.passwordConfirm = confirm;
        return this;
    }
    public UserBuilder withRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }

    @Override
    public User build(){
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setPasswordConfirm(passwordConfirm);
        user.setRoles(roles);
        return user;
    }
}

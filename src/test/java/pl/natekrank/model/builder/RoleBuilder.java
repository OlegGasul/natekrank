package pl.natekrank.model.builder;

import pl.natekrank.model.Role;
import pl.natekrank.model.User;

/**
 * Created by ievgenii on 26.09.16.
 */


public class RoleBuilder implements Builder<Role> {
    long id;
    long user_id;
    String roleText;


    public RoleBuilder withId(long id) {
        this.id = id;
        return this;
    }
    public RoleBuilder withUserId(long user_id) {
        this.user_id = user_id;
        return this;
    }
    public RoleBuilder withRole(String role) {
        this.roleText = role;
        return this;
    }
    @Override
    public Role build(){
        Role role = new Role();
        role.setId(id);
        role.setUser_id(user_id);
        role.setRole(roleText);
        return role;
    }
}



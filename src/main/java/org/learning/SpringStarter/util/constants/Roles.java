package org.learning.SpringStarter.util.constants;

public enum Roles {
    USER("ROLE_USER"),ADMIN("ROLE_ADMIN"),EDITOR("ROLE_EDITION");
    private String role;
    private Roles(String role){
        this.role=role;
    }
    public String getRole(){
        return role;
    }
}

package org.learning.SpringStarter.util.constants;

public enum Privilages {
    RESET_ANY_USER_PASSWORD(1l,"RESET_ANY_USER_PASSWORD"),
    ACCESS_ADMIN_PANEL(2l,"ACCESS_ADMIN_PANEL");

    private Long id;
    private String privillage;
    private Privilages(Long id, String privillage){
        this.id=id;
        this.privillage=privillage;
    }

    public Long getId(){
        return id;
    }

    public String getPrivillage(){
        return privillage;
    }

}

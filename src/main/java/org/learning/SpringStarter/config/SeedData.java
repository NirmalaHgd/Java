package org.learning.SpringStarter.config;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.learning.SpringStarter.models.Account;
import org.learning.SpringStarter.models.Authority;
import org.learning.SpringStarter.models.Post;
import org.learning.SpringStarter.services.AccountService;
import org.learning.SpringStarter.services.AuthorityService;
import org.learning.SpringStarter.services.PostService;
import org.learning.SpringStarter.util.constants.Privilages;
import org.learning.SpringStarter.util.constants.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
        private PostService postService;

    @Autowired
        private AccountService accountService;

    @Autowired
        private AuthorityService authorityService;

    @Override
    public void run(String... args) throws Exception {

        for (Privilages auth: Privilages.values()){
            Authority authority=new Authority();
            authority.setId(auth.getId());
            authority.setName(auth.getPrivillage());
            authorityService.save(authority);
        }

        Account account01=new Account();
        Account account02=new Account();
        Account account03=new Account();
        Account account04=new Account();
        
        account01.setEmail("user@gmail.com");
        account01.setPassword("password");
        account01.setFirstname("user");
        account01.setLastname("lastname");
        account01.setAge(24);
        account01.setDate_of_birth(LocalDate.parse("2000-02-10"));
        account01.setGender("Male");


        account02.setEmail("admin@gmail.com");
        account02.setPassword("password");
        account02.setFirstname("admin");
        account02.setLastname("lastname");
        account02.setRole(Roles.ADMIN.getRole());
        account02.setAge(24);
        account02.setDate_of_birth(LocalDate.parse("2000-02-10"));
        account02.setGender("Female");

        account03.setEmail("editor@gmail.com");
        account03.setPassword("password");
        account03.setFirstname("editor");
        account03.setLastname("lastname");
        account03.setRole(Roles.EDITOR.getRole());
        account03.setAge(20);
        account03.setDate_of_birth(LocalDate.parse("2004-02-10"));
        account03.setGender("Male");

        account04.setEmail("super_editor@gmail.com");
        account04.setPassword("password");
        account04.setFirstname("super_editor");
        account04.setLastname("lastname");
        account04.setRole(Roles.EDITOR.getRole());
        account04.setAge(23);
        account04.setDate_of_birth(LocalDate.parse("1999-02-10"));
        account04.setGender("Female");


        Set<Authority> authorities=new HashSet<>();
        authorityService.findById(Privilages.RESET_ANY_USER_PASSWORD.getId()).ifPresent(authorities::add);;
        authorityService.findById(Privilages.ACCESS_ADMIN_PANEL.getId()).ifPresent(authorities::add);;
        account04.setAuthority(authorities);

        accountService.save(account01);
        accountService.save(account02);
        accountService.save(account03);
        accountService.save(account04);

        List<Post> posts= postService.findAll();
        if (posts.size()==0){
            Post post01=new Post();
            post01.setTitle("Post 01");
            post01.setBody("Post 01 body.....");
            post01.setAccount(account01);
            postService.save(post01);

            Post post02=new Post();
            post02.setTitle("Post 02");
            post02.setBody("Post 02 body.....");
            post02.setAccount(account02);
            postService.save(post02);

            Post post03=new Post();
            post03.setTitle("Post 03");
            post03.setBody("Post 03 body.....");
            post03.setAccount(account03);
            postService.save(post03);

            Post post04=new Post();
            post04.setTitle("Post 04");
            post04.setBody("Post 04 body.....");
            post04.setAccount(account04);
            postService.save(post04);

            Post post05=new Post();
            post05.setTitle("Post 05");
            post05.setBody("Post 05 body.....");
            post05.setAccount(account01);
            postService.save(post05);

            Post post06=new Post();
            post06.setTitle("Post 06");
            post06.setBody("Post 06 body.....");
            post06.setAccount(account02);
            postService.save(post06);
        }
        
    }

    
}

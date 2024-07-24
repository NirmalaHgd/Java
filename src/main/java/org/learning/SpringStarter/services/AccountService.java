package org.learning.SpringStarter.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import org.learning.SpringStarter.models.Account;
import org.learning.SpringStarter.models.Authority;
import org.learning.SpringStarter.repositories.AccountRepository;
import org.learning.SpringStarter.util.AppUtil;
import org.learning.SpringStarter.util.constants.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService{
    

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    

    
    public Account save(Account account){
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        if(account.getRole()==null){
        account.setRole((Roles.USER.getRole()));
        }
        if(account.getPhoto()==null){
            String fileName = "profile.png";
            String path =AppUtil.set_upload_path(fileName);
            
            
            //String path="/resources/static/images/person.png";
            account.setPhoto(path);
            }
        return accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findOneByEmailIgnoreCase(email);
        
        if(!optionalAccount.isPresent()){
            throw new UsernameNotFoundException("Account not found");
        }
        Account account= optionalAccount.get();

        List<GrantedAuthority> grantedAuthority = new ArrayList<>();
        grantedAuthority.add(new SimpleGrantedAuthority(account.getRole()));
        
        for(Authority _auth : account.getAuthority()){
            grantedAuthority.add(new SimpleGrantedAuthority(_auth.getName()));
        }

        return new User(account.getEmail(),account.getPassword(),grantedAuthority);
    }

    public Optional<Account> findOneByEmail(String authUser) {
        return  accountRepository.findOneByEmailIgnoreCase(authUser);
    } 

    public Optional<Account> findById(Long id) {
        return  accountRepository.findById(id);
    } 

   /*  public Optional<Account> findByToken(String token) {
        return  accountRepository.findByToken(token);
    }
    */
}

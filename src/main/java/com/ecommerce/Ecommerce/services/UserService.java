package com.ecommerce.Ecommerce.services;

import com.ecommerce.Ecommerce.model.User;
import com.ecommerce.Ecommerce.model.UserPrincipal;
import com.ecommerce.Ecommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo repo;



    public List<User> fetchUsersList(){
        return repo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);

        System.out.println(user);

        if(user == null){
            System.out.println("User not found");
            throw new UsernameNotFoundException(username);
        }

        return new UserPrincipal(user);

    }
}

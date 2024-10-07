package com.ecommerce.Ecommerce.services;


import com.ecommerce.Ecommerce.dto.SignUpRequest;
import com.ecommerce.Ecommerce.model.User;
import com.ecommerce.Ecommerce.repository.UserRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.SystemMetricsAutoConfiguration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    private SystemMetricsAutoConfiguration systemMetricsAutoConfiguration;

    public User registerUser(@NotNull SignUpRequest registerRequest) {
       User user = new User();

       user.setEmail(registerRequest.email());
       user.setPassword(encoder.encode(registerRequest.password()));
       user.setUsername(registerRequest.username());

       return userRepo.save(user);
    }

    public String verify(@NotNull  User user){
        Authentication authentication =
                        authManager.authenticate(new UsernamePasswordAuthenticationToken(
                                user.getUsername(),user.getPassword()
                        ));

        String token  = jwtService.generateToken(user.getUsername());


        if (authentication.isAuthenticated()){
            return token;
        }else {
            return "Failed";
        }
    }

}

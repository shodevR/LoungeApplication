package com.app.controller;
import com.app.exceptions.UserException;
import com.app.model.Client;
import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.app.repository.UserRepository;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {



    @Autowired
    private UserRepository userRepository;



    @Autowired
    private PasswordEncoder passwordEncoder;

   /* @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }
*/
   @PostMapping("/signup")
   public ResponseEntity<?> registerUser(@RequestBody User user) {
       if (userRepository.existsByUsername(user.getUsername())) {
           return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists!");
       }

       User newUser = new User();
       newUser.setUsername(user.getUsername());
       newUser.setPassword(passwordEncoder.encode(user.getPassword()));
       newUser.setRole(user.getRole());

       userRepository.save(newUser);

       return new ResponseEntity<>(newUser, HttpStatus.OK);
   }

}
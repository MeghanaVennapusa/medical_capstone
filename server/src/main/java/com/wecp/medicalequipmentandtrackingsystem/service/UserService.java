package com.wecp.medicalequipmentandtrackingsystem.service;

import com.wecp.medicalequipmentandtrackingsystem.entitiy.User;
import com.wecp.medicalequipmentandtrackingsystem.exceptions.ResourceNotFoundException;
import com.wecp.medicalequipmentandtrackingsystem.exceptions.UserAlreadyExists;
import com.wecp.medicalequipmentandtrackingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;
    @Autowired
    public PasswordEncoder passwordEncoder;
    public User createUser(User user) 
    {
         String username=user.getUsername();
         String email=user.getEmail();
         String role=user.getRole();
         if(!role.equalsIgnoreCase("HOSPITAL") && !role.equalsIgnoreCase("TECHNICIAN") && !role.equalsIgnoreCase("SUPPLIER"))
         {
           throw new ResourceNotFoundException("ONLY HOSPITAL OR TECHNICIAN OR SUPPILER IS ALLOWED");
         }
      
         if(userRepository.findByUsername(username)!=null)
         {
          throw new UserAlreadyExists(username+"Already exists");
         }
         if(userRepository.findByEmail(email)!=null)
         {
          throw new UserAlreadyExists(email+"Already exists");
         }
            User u=new User();
            u.setUsername(username);
            u.setEmail(email);
            u.setPassword(passwordEncoder.encode(user.getPassword()));
            u.setRole(role.toUpperCase());
            return userRepository.save(u);
    }
public User getUserByUsername(String username)
{
  

  User user = userRepository.findByUsername(username);
  if (user != null && user.getUsername().equals(username)) {
      return user;
  }
  else
  {
    throw new ResourceNotFoundException("Username not found");
  }

}
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
   User user;
     user=userRepository.findByUsername(username);
     if(user==null)
     {
       throw new UsernameNotFoundException(username+" not found");
     }
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
     
    }
}
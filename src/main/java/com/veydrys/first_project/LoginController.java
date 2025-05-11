package com.veydrys.first_project;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;
import java.util.Optional;

@RestController
public class LoginController {
    @Autowired
    private UserRepository loginRepository;
    @PostMapping("/api/register")
    public String saveUserInfo(@RequestBody UserInfo userInfo){
        Optional<UserInfo> optionalUserInfo = loginRepository.findById(userInfo.getUsername());
        if (optionalUserInfo.isPresent()){
            return "This account already exists.";
        }else{
            loginRepository.save(userInfo);
            return "a";
        }
    }
    @GetMapping("/api/login")
    public String CheckUserInfo(@RequestParam String username, @RequestParam String password,
                                HttpSession session){
        Optional <UserInfo> optionalUserInfo = loginRepository.findById(username);
        if (optionalUserInfo.isPresent()){
            UserInfo temp = optionalUserInfo.get();
            if (Objects.equals(temp.getPassword(), password)){
                session.setAttribute("currentUser", temp);
                return "YES";
            }else{
                return "Wrong password!";
            }
        }else{
            return "This acount does not exist.";
        }
    }
}

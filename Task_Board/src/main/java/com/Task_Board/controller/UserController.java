package com.Task_Board.controller;

import com.Task_Board.entity.User;
import com.Task_Board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/userLogin")
    public String showUserLogin(ModelMap model){
        model.addAttribute("title", "Login-Task Board");
        return "userLogin";
    }
    // Verify Login Page ..
    @PostMapping("/login")
    public String VerifyLogin(@RequestParam("username") String username, @RequestParam("password") String password, ModelMap modelMap ){
        User user = userRepository.findByUsernameAndPassword(username,password);
        if(user!=null) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return"Index";
            }else {
                modelMap.addAttribute("error" , "Invalid Username/ password..");
                return"userLogin";
            }
        }else {
            modelMap.addAttribute("error" , "Invalid Username/ password..");
            return"userLogin";
        }
    }

}

package com.zemoso.springassignment.controller;

import com.zemoso.springassignment.model.Account;
import com.zemoso.springassignment.model.User;
import com.zemoso.springassignment.service.AccountServiceDAO;
import com.zemoso.springassignment.service.UserServiceDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    UserServiceDAO userServiceDAO;

    AccountServiceDAO accountServiceDAO;

    UserController(UserServiceDAO userServiceDAO, AccountServiceDAO accountServiceDAO){
        this.userServiceDAO=userServiceDAO;
        this.accountServiceDAO=accountServiceDAO;
    }

    private static final String REDIRECT_USER_USERS="redirect:/user/users";

    @GetMapping("/get")
    public String getUsers(){
        return "demo";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model){
        List<User> users = userServiceDAO.getAllUser();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/post")
    public String createUser() {
        return "user-form";
    }

    @PostMapping("/addUser")
    public String createUser(@ModelAttribute("user") User user) {
        userServiceDAO.saveUser(user);
        return REDIRECT_USER_USERS;
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userServiceDAO.deleteUser(id);
        return REDIRECT_USER_USERS;
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = userServiceDAO.getUserById(id);
        model.addAttribute("user", user);
        return "user-form";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        userServiceDAO.updateUser(user);
        return REDIRECT_USER_USERS;
    }

    @PostMapping("/linkAccount")
    public String linkAccountToUser(@RequestParam("userId") Long userId, @RequestParam("accountId") Long accountId, Model model) {
        User user = userServiceDAO.getUserById(userId);
        Account account = accountServiceDAO.getAccountById(accountId);

        if (user != null && account != null) {
            account.setUser(user);
            accountServiceDAO.saveAccount(account);
        } else {
            model.addAttribute("error", "Invalid user ID or account ID");
            return "error-page";
        }

        return REDIRECT_USER_USERS;
    }

}

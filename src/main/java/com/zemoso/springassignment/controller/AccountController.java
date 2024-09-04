package com.zemoso.springassignment.controller;

import com.zemoso.springassignment.model.Account;
import com.zemoso.springassignment.service.AccountServiceDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountServiceDAO accountServiceDAO;

    public AccountController(AccountServiceDAO accountServiceDAO) {
        this.accountServiceDAO = accountServiceDAO;
    }

    @GetMapping("/accounts")
    public String getAllAccounts(Model model) {
        List<Account> accounts = accountServiceDAO.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "account-list";
    }

    @GetMapping("/post")
    public String createAccount() {
        return "account-form";
    }

    @PostMapping("/addAccount")
    public String createAccount(@ModelAttribute("account") Account account) {
        accountServiceDAO.saveAccount(account);
        return "redirect:/account/accounts";
    }
}

package com.zemoso.springassignment.controller;

import com.zemoso.springassignment.model.Transaction;
import com.zemoso.springassignment.service.TransactionServiceDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionServiceDAO transactionServiceDAO;

    public TransactionController(TransactionServiceDAO transactionServiceDAO) {
        this.transactionServiceDAO = transactionServiceDAO;
    }

    @GetMapping("/transactions")
    public String getAllTransactions(Model model) {
        List<Transaction> transactions = transactionServiceDAO.getAllTransactions();
        model.addAttribute("transactions", transactions);
        return "transaction-list";
    }

    @GetMapping("/post")
    public String createTransaction() {
        return "transaction-form";
    }

    @PostMapping("/addTransaction")
    public String createTransaction(@RequestParam("amount") Integer amount,
                                    @RequestParam("type") String type,
                                    @RequestParam("accountId") Long accountId) {
        transactionServiceDAO.saveTransaction(amount, type, accountId);
        return "redirect:/transaction/transactions";
    }
}

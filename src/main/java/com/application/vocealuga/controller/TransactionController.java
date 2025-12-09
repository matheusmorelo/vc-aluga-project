package com.application.vocealuga.controller;

import com.application.vocealuga.dto.TransactionDto;
import com.application.vocealuga.service.impl.TransactionServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
@EnableWebMvc
public class TransactionController {
    /* repository */
    private TransactionServiceImpl transactionService;

    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transacoes")
    public String transactions(Model model) {
        model.addAttribute("transactions", transactionService.findAll());
        return "listar-transacoes";
    }

    @GetMapping("/pagamento")
    public String transaction(Model model) {
        model.addAttribute("transaction", new TransactionDto());
        return "transaction";
    }

    @PostMapping("/pagar")
    public String createTransaction(@ModelAttribute TransactionDto transactionDto) {
        try {
            transactionService.saveTransaction(transactionDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/pagamento?error";
        }

        return "redirect:/pagamento?success";
    }

    @PostMapping("/transacao/deletar")
    public String deleteTransaction(@RequestParam("id") Long id) {
        transactionService.deleteTransaction(id);
        return "redirect:/transacoes";
    }
}

package edu.attractor.onlinestore.controllers;

import edu.attractor.onlinestore.dtos.ClientLoginDto;
import edu.attractor.onlinestore.dtos.ClientRegisterDto;
import edu.attractor.onlinestore.entities.Client;
import edu.attractor.onlinestore.exceptions.ResourceNotFoundException;
import edu.attractor.onlinestore.exceptions.UserAlreadyExistException;
import edu.attractor.onlinestore.services.ClientService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService, PasswordEncoder passwordEncoder) {
        this.clientService = clientService;
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        if (!model.containsAttribute("dto")) {
            model.addAttribute("dto", new ClientRegisterDto());
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid ClientRegisterDto clientDto, BindingResult result, RedirectAttributes redirectAttrs){
        redirectAttrs.addFlashAttribute("dto", clientDto);
        Optional<Client> clientByEmail = this.clientService.findByEmail(clientDto.getEmail());
        if (result.hasFieldErrors()){
            redirectAttrs.addFlashAttribute(result.getFieldErrors());
            return "redirect:/client/login";
        }

        if (clientByEmail.isPresent()) {
            throw new UserAlreadyExistException(String.format("User with email %s already exist", clientDto.getEmail()));
        }

        redirectAttrs.addFlashAttribute("client", this.clientService.saveClient(clientDto));
        return "redirect:/products";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model){
        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping("/invalid")
    public String invalidLoginPage(){
        return "invalidlogin";
    }
}

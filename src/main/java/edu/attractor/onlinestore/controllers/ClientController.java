package edu.attractor.onlinestore.controllers;

import edu.attractor.onlinestore.dtos.ClientLoginDto;
import edu.attractor.onlinestore.dtos.ClientRegisterDto;
import edu.attractor.onlinestore.entities.Client;
import edu.attractor.onlinestore.exceptions.ResourceNotFoundException;
import edu.attractor.onlinestore.exceptions.UserAlreadyExistException;
import edu.attractor.onlinestore.services.ClientService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;


    public ClientController(ClientService clientService, PasswordEncoder passwordEncoder) {
        this.clientService = clientService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("clientDto") ClientRegisterDto clientDto, BindingResult result, RedirectAttributes redirectAttrs){
        Optional<Client> clientByEmail = this.clientService.findByEmail(clientDto.getEmail());
        if (result.hasFieldErrors()){
            redirectAttrs.addFlashAttribute(result.getFieldErrors());
            return "redirect:/register";
        }

        if (clientByEmail.isPresent()) {
            throw new UserAlreadyExistException(String.format("User with email %s already exist", clientDto.getEmail()));
        }

        redirectAttrs.addFlashAttribute("client", this.clientService.saveClient(clientDto));
        return "redirect:/products";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/loginPost")
    public String login(@Valid @ModelAttribute("clientDto") ClientLoginDto clientDto, BindingResult result, RedirectAttributes redirectAttributes) throws ResourceNotFoundException {
        if (result.hasFieldErrors()){
            redirectAttributes.addFlashAttribute("errors", result.getFieldErrors());
            return "redirect:/invalidLogin";
        }
        Optional<Client> findToLogin = this.clientService.
                findByEmailAndPassword(clientDto);
        if (findToLogin.isEmpty()) throw new ResourceNotFoundException(String.format( "Client with email %s not found", clientDto.getEmail()));
        redirectAttributes.addFlashAttribute("client", findToLogin.get());
        return "redirect:/products";
    }

    @GetMapping("/invalidLogin")
    public String invalidLoginPage(){
        return "invalidlogin";
    }
}

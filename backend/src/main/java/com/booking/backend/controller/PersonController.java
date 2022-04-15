package com.booking.backend.controller;
import com.booking.backend.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    private PersonService personService;

    public PersonController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping("/client")
    @RequestMapping(value = "/client", params = "id")
    public Client getClientById(@RequestParam Long id) {
        return clientService.getClientById(id);
    }
}

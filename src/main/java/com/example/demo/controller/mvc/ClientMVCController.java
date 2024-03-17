
package com.example.demo.controller.mvc;

import com.example.demo.model.Client;
import com.example.demo.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientMVCController {
    Logger logger = LoggerFactory.getLogger(ClientMVCController.class);

    @Autowired
    private ClientService cs;
//    private static List<Client> clients = new ArrayList<>();
//
//    static {
//        Client client1 = new Client(1,"Ivan", "Ivan@gmail.com", "132", "Male", "No notes", false, new Date(1991, 10, 1), "Tester");
//        Client client2 = new Client(2,"Alex", "Alex@gmail.com", "132", "Male", "No notes", false, new Date(1984, 10, 11), "Developer");
//        clients.add(client1);
//        clients.add(client2);
//    }

    @GetMapping("/register")
    public String showForm(Model model) {
        logger.info("showForm started");
        Client client = new Client();
        model.addAttribute("client", client);
        logger.info("client added");
        model.addAttribute("clients", cs.getAllClients());
        logger.info("client {} added", client);
        List<String> listProfession = Arrays.asList("Developer", "Tester", "Architect");
        model.addAttribute("listProfession", listProfession);
        return "register_form";
    }



    @PostMapping("/register")
    public String submitForm(@ModelAttribute("client") Client client) {
        cs.createClient(client);
        System.out.println(client);
        return "register_success";
    }

    @GetMapping("/edit")
    public String showEditForm(Model model) {
        logger.info("showForm started");
        Client client = new Client();
        model.addAttribute("client", client);
        logger.info("client added");
        model.addAttribute("clients", cs.getAllClients());
        logger.info("client {} added", client);
        List<String> listProfession = Arrays.asList("Developer", "Tester", "Architect");
        model.addAttribute("listProfession", listProfession);
        return "edit_form";
    }

    @PostMapping("/edit")
    public String submitEditForm(@ModelAttribute("client") Client client) {
        System.out.println(client);
        cs.saveOrUpdateClient(client);
        System.out.println(client);
        return "register_success";
    }

    @PostMapping("/delete/{client}")
    public String removeClient(@PathVariable("client") String id) {
        cs.deleteClientById(Integer.parseInt(id));
        return "redirect:/clients/register";
    }

    @GetMapping("/edit/{client}")
    public String editClient(@PathVariable("client") String id, Model model) {
        model.addAttribute("clients", cs.getAllClients());
        List<String> listProfession = Arrays.asList("Developer", "Tester", "Architect");
        model.addAttribute("listProfession", listProfession);
        model.addAttribute("client", cs.getClientById(Integer.parseInt(id)));
        //cs.deleteClientById(Integer.parseInt(id));
        return "edit_form";
    }

}

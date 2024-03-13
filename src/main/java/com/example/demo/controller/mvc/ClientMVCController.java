
package com.example.demo.controller.mvc;

import com.example.demo.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static List<Client> clients = new ArrayList<>();

    static {
        Client client1 = new Client(1,"Ivan", "Ivan@gmail.com", "132", "Male", "No notes", false, new Date(1991, 10, 1), "Tester");
        Client client2 = new Client(2,"Alex", "Alex@gmail.com", "132", "Male", "No notes", false, new Date(1984, 10, 11), "Developer");
        clients.add(client1);
        clients.add(client2);
    }

    @GetMapping("/register")
    public String showForm(Model model) {
        logger.info("showForm started");
        Client client = new Client();
        model.addAttribute("client", client);
        logger.info("client added");
        model.addAttribute("clients", clients);
        logger.info("client {} added", client);
        List<String> listProfession = Arrays.asList("Developer", "Tester", "Architect");
        model.addAttribute("listProfession", listProfession);

        return "register_form";
    }

    @PostMapping("/register")
    public String submitForm(@ModelAttribute("client") Client client) {
        clients.add(client);
        System.out.println(client);
        return "register_success";
    }

    @PostMapping ("/delete/{client}")
    public String removeClient(@PathVariable("client") String email) {
        Iterator<Client> iterator = clients.iterator();
        while (iterator.hasNext()) {
            Client client = iterator.next();
            if (client.getEmail().equals(email)) {
                iterator.remove();
                logger.info("client {} removed", client);
                break;
            }
        }
        return "redirect:/clients/register";
    }

    @GetMapping ("/edit/{client}")
    public String editClient(@PathVariable("client") String email, Model model) {
        model.addAttribute("clients", clients);
        List<String> listProfession = Arrays.asList("Developer", "Tester", "Architect");
        model.addAttribute("listProfession", listProfession);
        Iterator<Client> iterator = clients.iterator();
        while (iterator.hasNext()) {
            Client client = iterator.next();
            if (client.getEmail().equals(email)) {
                model.addAttribute("client", client);
                iterator.remove();
                logger.info("client {} removed", client);
                break;
            }
        }
        return "edit_form";
    }

}

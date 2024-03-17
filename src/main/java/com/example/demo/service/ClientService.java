package com.example.demo.service;

import com.example.demo.model.Client;
import java.util.List;

public interface ClientService {

    List<Client> getAllClients();
    Client getClientById(int id);
    boolean deleteClientById(int id);
    boolean saveOrUpdateClient(Client client);

    boolean createClient(Client client);
}

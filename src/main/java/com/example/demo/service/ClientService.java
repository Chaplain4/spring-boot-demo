package com.example.demo.service;

import com.example.demo.model.Client;
import java.util.List;

public interface ClientService {

    List<Client> getAllClients();
    boolean deleteClientById(int id);
    boolean saveOrUpdateClient(int id);
}

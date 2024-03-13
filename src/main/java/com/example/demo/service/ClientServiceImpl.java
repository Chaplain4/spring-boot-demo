package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    ClientRepository cr;

    @Override
    public List<Client> getAllClients() {
        return null;
    }

    @Override
    public boolean deleteClientById(int id) {
        return false;
    }

    @Override
    public boolean saveOrUpdateClient(int id) {
        return false;
    }
}

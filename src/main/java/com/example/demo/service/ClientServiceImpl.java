package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository cr;

    @Override
    public List<Client> getAllClients() {
        return cr.findAll();
    }

    @Override
    public Client getClientById(int id) {
        return cr.getReferenceById(id);
    }

    @Override
    public boolean deleteClientById(int id) {
        try {
            cr.deleteById(id);
            return true;
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }

    @Override
    @Modifying
    public boolean saveOrUpdateClient(Client client) {
        try {
            cr.saveAndFlush(client);
            return true;
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean createClient(Client client) {
        try {
            cr.save(client);
            return true;
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }
}

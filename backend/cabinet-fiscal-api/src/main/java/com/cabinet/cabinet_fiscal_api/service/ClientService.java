package com.cabinet.cabinet_fiscal_api.service;

import com.cabinet.cabinet_fiscal_api.model.Client;
import com.cabinet.cabinet_fiscal_api.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    // Constructor injection
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // ✅ CREATE CLIENT
    public Client createClient(Client client) {

        // Check email already exists
        if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // Check matricule already exists
        if (clientRepository.findByMatricule(client.getMatricule()).isPresent()) {
            throw new RuntimeException("Matricule already exists");
        }

        return clientRepository.save(client);
    }

    // ✅ GET ALL CLIENTS
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // ✅ GET CLIENT BY ID
    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    // ✅ UPDATE CLIENT
    public Client updateClient(Long id, Client updatedClient) {

        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        // Check email uniqueness only if changed
        if (!existingClient.getEmail().equals(updatedClient.getEmail())
                && clientRepository.findByEmail(updatedClient.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // Check matricule uniqueness only if changed
        if (!existingClient.getMatricule().equals(updatedClient.getMatricule())
                && clientRepository.findByMatricule(updatedClient.getMatricule()).isPresent()) {
            throw new RuntimeException("Matricule already exists");
        }

        existingClient.setMatricule(updatedClient.getMatricule());
        existingClient.setNom(updatedClient.getNom());
        existingClient.setEmail(updatedClient.getEmail());
        existingClient.setTelephone(updatedClient.getTelephone());
        existingClient.setAdresse(updatedClient.getAdresse());

        return clientRepository.save(existingClient);
    }

    // ✅ DELETE CLIENT
    public void deleteClient(Long id) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        clientRepository.delete(existingClient);
    }
}
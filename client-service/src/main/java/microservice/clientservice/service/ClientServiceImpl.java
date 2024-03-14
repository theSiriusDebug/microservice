package microservice.clientservice.service;


import microservice.clientservice.domain.Client;
import microservice.clientservice.domain.ClientRegistrationRequest;
import microservice.clientservice.repos.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;

    @Autowired
    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Client createClient(ClientRegistrationRequest request) {
        return repository.save(Client.builder()
                .username(request.username())
                .firstname(request.firstname())
                .surname(request.surname())
                .build());
    }
}

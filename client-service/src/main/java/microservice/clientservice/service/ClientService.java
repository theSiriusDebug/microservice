package microservice.clientservice.service;

import microservice.clientservice.domain.Client;
import microservice.clientservice.domain.ClientRegistrationRequest;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public interface ClientService {
    @Transactional
    Client createClient(ClientRegistrationRequest request);
}

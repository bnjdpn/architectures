package fr.xebia.architectures.layered.persistence.repository;

import fr.xebia.architectures.layered.persistence.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {

}

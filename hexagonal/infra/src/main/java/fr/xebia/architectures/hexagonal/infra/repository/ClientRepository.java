package fr.xebia.architectures.hexagonal.infra.repository;


import fr.xebia.architectures.hexagonal.infra.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {

}

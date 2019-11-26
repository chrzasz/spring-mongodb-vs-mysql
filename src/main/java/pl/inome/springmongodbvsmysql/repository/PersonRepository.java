package pl.inome.springmongodbvsmysql.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.inome.springmongodbvsmysql.model.Person;

import java.util.List;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {

    List<Person> findByFirstName(String firstName);
    List<Person> findByLastName(String lastName);
    Person findByEmail(String email);

}

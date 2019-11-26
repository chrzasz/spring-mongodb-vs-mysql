package pl.inome.springmongodbvsmysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.inome.springmongodbvsmysql.model.Person;
import pl.inome.springmongodbvsmysql.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person create(String firstName, String lastName, String email) {
        return repository.save(new Person(firstName, lastName, email));
    }

    public List<Person> getAll() {
        return repository.findAll();
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<Person> getByFirstName(String firstName) {
        return repository.findByFirstName(firstName);
    }

    public List<Person> getByLastName(String lastName) {
        return repository.findByLastName(lastName);
    }

    public Person getByEmail(String email) {
        return repository.findByEmail(email);
    }


}

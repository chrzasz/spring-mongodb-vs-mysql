package pl.inome.springmongodbvsmysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.inome.springmongodbvsmysql.model.Person;
import pl.inome.springmongodbvsmysql.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person create(String firstName, String lastName, String email) {
        return personRepository.save(new Person(firstName, lastName, email));
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public List<Person> getByFirstName(String firstName) {
        return personRepository.findByFirstName(firstName);
    }

    public List<Person> getByLastName(String lastName) {
        return personRepository.findByLastName(lastName);
    }

    public Person getByEmail(String email) {
        return personRepository.findByEmail(email);
    }


}

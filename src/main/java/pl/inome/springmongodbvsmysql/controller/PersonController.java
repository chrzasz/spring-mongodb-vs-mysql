package pl.inome.springmongodbvsmysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.inome.springmongodbvsmysql.model.Person;
import pl.inome.springmongodbvsmysql.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/persons")
class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAll() {
        return personService.getAllFromMongo();
    }

    @GetMapping("/{email}")
    public Person getPerson(@PathVariable String email) {
        return personService.getByEmail(email);
    }

    @PostMapping
    public String createPerson(@RequestBody Person person) {
        personService.create(person.getFirstName(), person.getLastName(), person.getEmail());
        return person.toString();
    }
}

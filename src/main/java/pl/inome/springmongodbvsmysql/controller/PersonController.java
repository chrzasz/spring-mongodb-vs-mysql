package pl.inome.springmongodbvsmysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.inome.springmongodbvsmysql.model.Person;
import pl.inome.springmongodbvsmysql.service.PersonService;

import java.util.List;

@RestController
class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("/getAll")
    public List<Person> getAll() {
        return personService.getAll();
    }

    @RequestMapping("/get")
    public Person getPerson(@RequestParam String email) {
        return personService.getByEmail(email);
    }

    @RequestMapping("/create")
    public String createPerson(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String email) {
        Person p = personService.create(firstName, lastName, email);
        return p.toString();
    }
}

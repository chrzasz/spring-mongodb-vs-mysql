package pl.inome.springmongodbvsmysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.inome.springmongodbvsmysql.model.PersonSql;
import pl.inome.springmongodbvsmysql.repository.PersonSqlRepository;

import java.util.List;

@Service
public class PersonSqlService {

    private PersonSqlRepository repository;

    @Autowired
    public PersonSqlService(PersonSqlRepository repository) {
        this.repository = repository;
    }

    public PersonSql create(String firstName, String lastName, String email) {
        return repository.save(new PersonSql(firstName, lastName, email));
    }

    public List<PersonSql> getAll() {
        return repository.findAll();
    }

    public void deleteAll() {
        repository.deleteAll();
    }


}

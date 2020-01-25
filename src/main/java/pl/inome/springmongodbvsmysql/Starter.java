package pl.inome.springmongodbvsmysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.inome.springmongodbvsmysql.model.Person;
import pl.inome.springmongodbvsmysql.model.PersonSql;
import pl.inome.springmongodbvsmysql.service.PersonService;
import pl.inome.springmongodbvsmysql.service.PersonSqlService;
import pl.inome.springmongodbvsmysql.utils.CsvReader;

import java.util.ArrayList;
import java.util.List;

@Component
public class Starter implements CommandLineRunner {

    PersonService personService;
    PersonSqlService personSqlService;

    List<List<String>> metadata = new ArrayList<>();
    List<Person> personList = new ArrayList<>();
    List<PersonSql> personSqlList = new ArrayList<>();


    @Autowired
    public Starter(PersonService personService, PersonSqlService personSqlService) {
        this.personService = personService;
        this.personSqlService = personSqlService;
    }

    @Override
    public void run(String... args) throws Exception {

        personService.deleteAllFromMongo();
        personSqlService.deleteAllFromSql();

        CsvReader csvReader = new CsvReader("data.csv", ";");
        metadata = csvReader.getRecords();

        int firstNameIdx = metadata.get(0).indexOf("first_name");
        int lastNameIdx = metadata.get(0).indexOf("last_name");
        int emailIdx = metadata.get(0).indexOf("email");

        for (int i = 1; i < metadata.size(); i++) { //skip header
            personList.add(personService.create(
                    metadata.get(i).get(firstNameIdx),
                    metadata.get(i).get(lastNameIdx),
                    metadata.get(i).get(emailIdx)));
            personSqlList.add(personSqlService.create(
                    metadata.get(i).get(firstNameIdx),
                    metadata.get(i).get(lastNameIdx),
                    metadata.get(i).get(emailIdx)));
        }

        personService.saveAllToMongo(personList);
        personSqlService.saveAllToSql(personSqlList);

        personService.getAllFromMongo();
        personService.getAllFromMongo();
        personService.getAllFromMongo();
        personService.getAllFromMongo();

        personSqlService.getAllFromSql();
        personSqlService.getAllFromSql();
        personSqlService.getAllFromSql();
        personSqlService.getAllFromSql();
    }
}

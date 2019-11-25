package pl.inome.springmongodbvsmysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.inome.springmongodbvsmysql.service.PersonService;

import java.io.*;

@Component
public class Starter implements CommandLineRunner {

    PersonService personService;

    @Autowired
    public Starter(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void run(String... args) throws Exception {
        File file = new File("data.csv");
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = bufferedReader.readLine();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                String[] person = line.split(";");
                personService.create(person[1], person[2], person[3]);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

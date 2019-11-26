package pl.inome.springmongodbvsmysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.inome.springmongodbvsmysql.service.PersonService;
import pl.inome.springmongodbvsmysql.service.PersonSqlService;

import java.io.*;

@Component
public class Starter implements CommandLineRunner {

    PersonService personService;
    PersonSqlService personSqlService;

    String line;
    long startTime;
    long elapsedTime;

    @Autowired
    public Starter(PersonService personService, PersonSqlService personSqlService) {
        this.personService = personService;
        this.personSqlService = personSqlService;
    }

    @Override
    public void run(String... args) throws Exception {
        startTime = System.nanoTime();
        personService.deleteAll();
        elapsedTime = System.nanoTime() - startTime;
        System.out.println(elapsedTime / 1000000 + "\t\tTotal execution time to delete 3000K objects in MongoDb");

        startTime = System.nanoTime();
        personSqlService.deleteAll();
        elapsedTime = System.nanoTime() - startTime;
        System.out.println(elapsedTime / 1000000 + "\tTotal execution time to delete 3000K objects in MySQL");

        File file = new File("data.csv");

        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        line = bufferedReader.readLine();

        startTime = System.nanoTime();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                String[] person = line.split(";");
                personService.create(person[1], person[2], person[3]);
//                personSqlService.create(person[1], person[2], person[3]);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        elapsedTime = System.nanoTime() - startTime;
        System.out.println(elapsedTime / 1000000 + "\tTotal execution time to create 3000K objects in MongoDb");

        FileInputStream fileInputStream1 = new FileInputStream(file);
        InputStreamReader inputStreamReader1 = new InputStreamReader(fileInputStream1);
        BufferedReader bufferedReader1 = new BufferedReader(inputStreamReader1);
        line = bufferedReader1.readLine();
        startTime = System.nanoTime();
        try {
            while ((line = bufferedReader1.readLine()) != null) {
                String[] person = line.split(";");
//                personService.create(person[1], person[2], person[3]);
                personSqlService.create(person[1], person[2], person[3]);
            }
            bufferedReader1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        elapsedTime = System.nanoTime() - startTime;
        System.out.println(elapsedTime / 1000000 + "\tTotal execution time to create 3000K objects in MySQL");

    }
}

package pl.inome.springmongodbvsmysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inome.springmongodbvsmysql.model.PersonSql;

@Repository
public interface PersonSqlRepository extends JpaRepository<PersonSql, Long> {

}

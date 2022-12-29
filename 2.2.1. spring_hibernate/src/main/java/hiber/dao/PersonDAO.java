package hiber.dao;

import hiber.model.Person;
import java.util.List;

public interface PersonDAO {
    List<Person> index() ;
    Person show(int id);
    void save(Person person);
    void update(Person updatedPerson);
    void delete(int id);
}
package hiber.service;

import hiber.model.Person;
import java.util.List;

public interface PersonService {
    List<Person> index() ;
    Person show(int id) ;
    void save(Person person) ;
    void update(Person updatedPerson);
    void delete(int id);
}

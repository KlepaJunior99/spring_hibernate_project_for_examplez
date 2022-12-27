package hiber.dao;


import hiber.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional
public class PersonDAO {

    private final SessionFactory sessionFactory;
    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        return entityManager.createQuery("select p from Person p", Person.class).getResultList();
    }

    @Transactional()
    public Person show(int id) {
        return entityManager.find(Person.class,id);
    }

    @Transactional
    public void save(Person person) {
        entityManager.persist(person);
    }

    @Transactional
    public void update(Person updatedPerson) {
        entityManager.merge(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        entityManager.remove(show(id));
    }
}
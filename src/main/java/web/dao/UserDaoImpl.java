package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;

import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    private EntityManager manager;

    @PersistenceContext
    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(User user) {
        manager.persist(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = manager.find(User.class, id);
        if (user != null) {
            manager.remove(user);
        }
    }

    @Override
    public void update(User user) {
        manager.merge(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return manager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return manager.find(User.class, id);
    }
}

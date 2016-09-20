package pl.natekrank.repository;

import com.sun.javafx.fxml.expression.Expression;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import pl.natekrank.model.Task;
import pl.natekrank.model.User;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskDAOImpl extends HibernateDaoSupport implements TaskDAO {
    public TaskDAOImpl(SessionFactory sessionfactory) {
        setSessionFactory(sessionfactory);
    }

    @Override
    public List<Task> findTasks() {
        Session session = getSessionFactory().openSession();
        List<Task> list = session.createCriteria(Task.class).addOrder(Order.asc("id")).list();
        for(Task task: list) {
            Hibernate.initialize(task.getQuestions());
        }

        if (session.isOpen()) {
            session.close();
        }

        return list;
    }

    @Override
    public Task save(Task task) {
        Session session = getSessionFactory().openSession();

        session.saveOrUpdate(task);

        if (session.isOpen()) {
            session.close();
        }

        return task;
    }

    public Task getTask(Long id) {
        Session session = getSessionFactory().openSession();
        Task task = (Task) session.createCriteria(Task.class).add(Restrictions.eq("id", id)).setCacheable(false).uniqueResult();
        Hibernate.initialize(task.getQuestions());

        if (session.isOpen()) {
            session.close();
        }

        return task;
    }
}

package pl.natekrank.repository;

import org.hibernate.CacheMode;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import pl.natekrank.model.TestTaken;

import java.util.List;

@Service
public class HibernateTestTakenDAO extends HibernateDaoSupport implements TestTakenDAO {
    public HibernateTestTakenDAO(SessionFactory sessionfactory) {
        setSessionFactory(sessionfactory);
    }

    @Autowired
    private TaskDAO taskDAO;

    @Override
    public List<TestTaken> getAllTickets() {
        Session session = getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);

        List<TestTaken> list = session.createCriteria(TestTaken.class).addOrder(Order.asc("id")).list();
        for(TestTaken testTaken: list) {
            Hibernate.initialize(testTaken.getTestTakenAnswers());
        }

        if (session.isOpen()) {
            session.close();
        }

        return list;
    }

    @Override
    public TestTaken save(TestTaken testTaken) {
        Session session = getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);

        testTaken.setTask(taskDAO.getTask(testTaken.getTaskId()));

        session.saveOrUpdate(testTaken);
        session.flush();

        if (session.isOpen()) {
            session.close();
        }

        return testTaken;
    }

    public TestTaken getTestTaken(Long id) {
        Session session = getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);

        TestTaken testTaken = (TestTaken) session.createCriteria(TestTaken.class).add(Restrictions.eq("id", id)).setCacheable(false).uniqueResult();
        Hibernate.initialize(testTaken.getTestTakenAnswers());

        if (session.isOpen()) {
            session.close();
        }

        return testTaken;
    }
}

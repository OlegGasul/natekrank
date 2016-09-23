package pl.natekrank.repository;

import org.hibernate.CacheMode;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import pl.natekrank.model.TestTaken;

import java.util.List;

@Service
public class TicketDAOImpl extends HibernateDaoSupport implements TicketDAO {
    public TicketDAOImpl(SessionFactory sessionfactory) {
        setSessionFactory(sessionfactory);
    }

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
    public TestTaken save(TestTaken ticket) {
        Session session = getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);

        session.saveOrUpdate(ticket);
        session.flush();

        if (session.isOpen()) {
            session.close();
        }

        return ticket;
    }

    public TestTaken getTicket(Long id) {
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

package pl.natekrank.repository;

import org.hibernate.CacheMode;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import pl.natekrank.model.Task;
import pl.natekrank.model.Ticket;

import java.util.List;

@Service
public class TicketDAOImpl extends HibernateDaoSupport implements TicketDAO {
    public TicketDAOImpl(SessionFactory sessionfactory) {
        setSessionFactory(sessionfactory);
    }

    @Override
    public List<Ticket> getAllTickets() {
        Session session = getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);

        List<Ticket> list = session.createCriteria(Ticket.class).addOrder(Order.asc("id")).list();
        for(Ticket ticket: list) {
            Hibernate.initialize(ticket.getTicketAnswers());
        }

        if (session.isOpen()) {
            session.close();
        }

        return list;
    }

    @Override
    public Ticket save(Ticket ticket) {
        Session session = getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);

        session.saveOrUpdate(ticket);
        session.flush();

        if (session.isOpen()) {
            session.close();
        }

        return ticket;
    }

    public Ticket getTicket(Long id) {
        Session session = getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);

        Ticket ticket = (Ticket) session.createCriteria(Ticket.class).add(Restrictions.eq("id", id)).setCacheable(false).uniqueResult();
        Hibernate.initialize(ticket.getTicketAnswers());

        if (session.isOpen()) {
            session.close();
        }

        return ticket;
    }
}

package pl.natekrank.repository;

import pl.natekrank.model.Task;
import pl.natekrank.model.Ticket;

import java.util.List;

public interface TicketDAO {
    List<Ticket> getAllTickets();
    Ticket save(Ticket task);
    Ticket getTicket(Long id);
}

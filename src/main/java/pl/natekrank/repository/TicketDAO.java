package pl.natekrank.repository;

import pl.natekrank.model.TestTaken;

import java.util.List;

public interface TicketDAO {
    List<TestTaken> getAllTickets();
    TestTaken save(TestTaken task);
    TestTaken getTicket(Long id);
}

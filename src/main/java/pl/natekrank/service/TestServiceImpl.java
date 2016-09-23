package pl.natekrank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.natekrank.model.TestTaken;
import pl.natekrank.repository.TicketDAO;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TicketDAO ticketDAO;

    @Override
    public List<TestTaken> getAllTickets() {
        return ticketDAO.getAllTickets();
    }
}

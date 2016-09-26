package pl.natekrank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.natekrank.model.TestTaken;
import pl.natekrank.repository.TestTakenDAO;
import java.util.List;

@Service
public class TestTakenServiceImpl implements TestTakenService {
    @Autowired
    private TestTakenDAO testTakenDAO;

    @Override
    public TestTaken save(TestTaken testTaken) {
        return testTakenDAO.save(testTaken);
    }

    @Override
    public List<TestTaken> getAllTickets() {
        return testTakenDAO.getAllTickets();
    }
}

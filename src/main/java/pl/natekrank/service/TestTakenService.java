package pl.natekrank.service;

import pl.natekrank.model.Task;
import pl.natekrank.model.TestTaken;
import java.util.List;

public interface TestTakenService {
    TestTaken save(TestTaken task);
    List<TestTaken> getAllTickets();
}

package pl.natekrank.repository;

import pl.natekrank.model.TestTaken;

import java.util.List;

public interface TestTakenDAO {
    List<TestTaken> getAllTickets();
    TestTaken save(TestTaken task);
    TestTaken getTestTaken(Long id);
}

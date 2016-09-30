package pl.natekrank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.natekrank.repository.SurveyRepository;

@Service
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    private SurveyRepository repository;

    @Override
    public boolean checkKey(String testKey) {
        repository.findByToken(testKey);
        return false;
    }
}

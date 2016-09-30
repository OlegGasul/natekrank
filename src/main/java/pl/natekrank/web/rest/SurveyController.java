package pl.natekrank.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.natekrank.model.Survey;
import pl.natekrank.model.Task;
import pl.natekrank.service.SurveyService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/rest/survey")
@Transactional
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Survey> insert(@RequestBody Survey survey) {
        return new ResponseEntity<>(surveyService.save(survey), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Survey>> getList() {
        return new ResponseEntity<>(surveyService.getAllSurveys(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Survey> getTaskById(@PathVariable Long id) {
        return new ResponseEntity<>(surveyService.getSurveyById(id), HttpStatus.OK);
    }
}

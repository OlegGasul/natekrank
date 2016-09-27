package pl.natekrank.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.natekrank.model.Survey;
import pl.natekrank.service.SurveyService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/survey")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Survey> insert(Principal user, @RequestBody Survey survey) {
        return new ResponseEntity<>(surveyService.save(survey), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Survey>> getList() {
        return new ResponseEntity<>(surveyService.getAllSurveys(), HttpStatus.OK);
    }
}

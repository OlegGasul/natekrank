package pl.natekrank.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.natekrank.model.Survey;
import pl.natekrank.model.User;
import pl.natekrank.service.SurveyService;

@Controller
@RequestMapping("/result/{surveyKey}")
public class ResultController {
    @Autowired
    private SurveyService surveyService;

    @RequestMapping(method = RequestMethod.GET)
    public String result(@PathVariable("testKey") String testKey, Model model) {
        Survey survey = surveyService.getSurveyByKey(testKey);

        return "result/index";
    }
}

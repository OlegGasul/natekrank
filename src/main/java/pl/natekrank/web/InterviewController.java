package pl.natekrank.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.natekrank.model.Survey;
import pl.natekrank.model.User;
import pl.natekrank.model.dto.SurveyDto;
import pl.natekrank.service.SurveyService;

@Controller
@RequestMapping("/survey")
public class InterviewController {
    @Autowired
    private SurveyService surveyService;

    @RequestMapping(value = "/{surveyKey}", method = RequestMethod.GET)
    public String index(@PathVariable("surveyKey") String surveyKey, @RequestParam("start") String start, Model model) {
        Survey survey = surveyService.getSurveyByKey(surveyKey);
        if (survey == null) {
            return error(model, "Survey does't exists.");
        }
        if (survey.getFinished() != null) {
            return error(model, "Survey has been ended.");
        }

        model.addAttribute("survey", survey);



        if ("1".equals(start) || survey.getStarted() != null) {
            return "survey/survey";
        } else {
            return "survey/welcome";
        }
    }

    public String error(Model model, String errorMessage) {
        model.addAttribute("errorMessage", errorMessage);
        return "survey/error";
    }
}

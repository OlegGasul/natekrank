package pl.natekrank.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
    public String index(@PathVariable("surveyKey") String testKey, Model model) {
        Survey survey = surveyService.getSurveyByKey(testKey);
        if (survey == null) {
            return error(model, "Survey does't exists.");
        }
        if (survey.getFinished() != null) {
            return error(model, "Survey has been ended.");
        }

        model.addAttribute("survey", survey);

        return "survey/welcome";
    }

    public String error(Model model, String errorMessage) {
        model.addAttribute("errorMessage", errorMessage);
        return "survey/error";
    }
}

package pl.natekrank.web;

import org.codehaus.jackson.map.ObjectMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.natekrank.model.Survey;
import pl.natekrank.model.dto.SurveyDto;
import pl.natekrank.service.SurveyService;
import pl.natekrank.web.helper.DtoFactory;
import pl.natekrank.web.helper.JsonFactory;

import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/survey")
public class InterviewController {
    @Autowired
    private SurveyService surveyService;
    @Autowired
    private DtoFactory dtoFactory;
    @Autowired
    private JsonFactory jsonFactory;

    @RequestMapping(value = "/{token}", method = RequestMethod.POST)
    public String submitSurvey(@PathVariable("token") String token, @RequestBody SurveyDto survey) {
        surveyService.submitSurvey(survey);
        return "survey/result";
    }

    @RequestMapping(value = "/{token}", method = RequestMethod.GET)
    public String index(@PathVariable("token") String token, @RequestParam(value = "start", required = false) String start, Model model) {
        Survey survey = surveyService.getSurveyByToken(token);
        if (survey == null) {
            return errorResponse(model, "Survey does't exists.");
        }
        if (survey.getFinished() != null) {
            return errorResponse(model, "Survey has been ended.");
        }
        if (start != null && survey.getStarted() == null) {
            survey = surveyService.startSurvey(survey);
        }

        SurveyDto surveyDto = dtoFactory.createDto(survey);
        surveyDto.setServerRequest(new Date());

        if (start != null || survey.getStarted() != null) {
            model.addAttribute("survey", jsonFactory.generateJson(surveyDto));
            return "survey/survey";
        } else {
            model.addAttribute("survey", surveyDto);
            return "survey/welcome";
        }
    }

    public String errorResponse(Model model, String errorMessage) {
        model.addAttribute("errorMessage", errorMessage);
        return "survey/error";
    }
}

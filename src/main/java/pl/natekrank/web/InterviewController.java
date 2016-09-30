package pl.natekrank.web;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.codehaus.jackson.map.ObjectMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.natekrank.model.Survey;
import pl.natekrank.model.User;
import pl.natekrank.model.dto.SurveyDto;
import pl.natekrank.service.SurveyService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Controller
@RequestMapping("/survey")
public class InterviewController {
    @Autowired
    private SurveyService surveyService;

    @RequestMapping(value = "/{token}", method = RequestMethod.POST)
    public String submitSurvey(@PathVariable("token") String token, @RequestBody SurveyDto survey) {
        surveyService.submitSurvey(survey);
        return "survey/result";
    }

    @RequestMapping(value = "/{token}", method = RequestMethod.GET)
    public String index(@PathVariable("token") String token, @RequestParam(value = "start", required = false) String start, Model model) {
        Survey survey = surveyService.getSurveyByToken(token);
        if (survey == null) {
            return error(model, "Survey does't exists.");
        }
        if (survey.getFinished() != null) {
            return error(model, "Survey has been ended.");
        }
        if ("1".equals(start) && survey.getStarted() == null) {
            survey = surveyService.startSurvey(survey);
        }
        
        SurveyDto surveyDto = createSurveyDto(survey);
        model.addAttribute("survey", generateJson(surveyDto));

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

    private SurveyDto createSurveyDto(Survey survey) {
        Mapper mapper = new DozerBeanMapper();
        return mapper.map(survey, SurveyDto.class);
    }

    private String generateJson(SurveyDto surveyDto) {
        ObjectMapper jsonMapper = new ObjectMapper();
        String json = "{}";
        try {
            json = jsonMapper.writeValueAsString(surveyDto);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }
}

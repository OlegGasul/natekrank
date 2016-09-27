package pl.natekrank.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.natekrank.model.dto.SurveyDto;

@RestController
@RequestMapping("/survey/{testKey}")
public class InterviewController {
    @RequestMapping(method = RequestMethod.GET)
    public SurveyDto getTest(@PathVariable("testKey") String testKey) {

        return new SurveyDto();
    }
}

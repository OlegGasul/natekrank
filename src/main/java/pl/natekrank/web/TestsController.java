package pl.natekrank.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.natekrank.model.TestTaken;
import pl.natekrank.service.TestService;

import java.util.List;

@Controller
@RequestMapping("/tests")
public class TestsController {
    @Autowired
    private TestService testService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TestTaken>> getList() {
        return new ResponseEntity<>(testService.getAllTickets(), HttpStatus.OK);
    }
}

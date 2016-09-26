package pl.natekrank.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.natekrank.model.TestTaken;
import pl.natekrank.service.TestTakenService;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestTakenService testTakenService;

    @RequestMapping(method = RequestMethod.POST, consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<TestTaken> insert(@RequestBody TestTaken testTaken) {
        return new ResponseEntity<>(testTakenService.save(testTaken), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TestTaken>> getList() {
        return new ResponseEntity<>(testTakenService.getAllTickets(), HttpStatus.OK);
    }
}

package pl.natekrank.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.natekrank.model.Ticket;
import pl.natekrank.service.TicketService;

import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Ticket>> getList() {
        return new ResponseEntity<>(ticketService.getAllTickets(), HttpStatus.OK);
    }
}

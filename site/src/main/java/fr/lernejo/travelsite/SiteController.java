package fr.lernejo.travelsite;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SiteController {

    @PostMapping(value = "/api/inscription", consumes = {"application/json"})
    ResponseEntity<InscriptionDTO> postInscription(@RequestBody InscriptionDTO inscription) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/travels")
    ResponseEntity<List<TravelDTO>> getUserTravels(@RequestParam("userName") String userName) {
        return new ResponseEntity<>(List.of(new TravelDTO("Australia", 35.1),
            new TravelDTO("Caribbean", 32.4)), HttpStatus.OK);
    }
}

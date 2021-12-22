package fr.lernejo.travelsite;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SiteController {
    private final TravelSiteService travelSiteService;

    public SiteController(TravelSiteService travelSiteService) {
        this.travelSiteService = travelSiteService;
    }

    @PostMapping(value = "/api/inscription", consumes = {"application/json"})
    ResponseEntity<InscriptionDTO> postInscription(@RequestBody InscriptionDTO inscription) {
        try {
            travelSiteService.registerUser(inscription);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/api/travels")
    ResponseEntity<List<TravelDTO>> getUserTravels(@RequestParam("userName") String userName) {
        try {
            return ResponseEntity.ok(travelSiteService.getTravelsByUsername(userName));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}

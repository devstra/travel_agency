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
    void postInscription(@RequestBody InscriptionDTO inscription) {
        travelSiteService.registerUser(inscription);
    }

    @GetMapping("/api/travels")
    ResponseEntity<List<TravelDTO>> getUserTravels(@RequestParam("userName") String userName) {
        return ResponseEntity.ok(travelSiteService.getTravelsByUsername(userName));
    }
}

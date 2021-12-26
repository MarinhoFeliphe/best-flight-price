package com.src.bestflightprice.companies.resources;

import com.src.bestflightprice.companies.services.BestFlightPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/best-flight-price")
public class BestFlightPriceResource {

    @Autowired
    private BestFlightPriceService _bestFlightPriceService;

    @GetMapping
    public ResponseEntity<Object> get(
            @RequestParam(value="origin") String origin,
            @RequestParam(value="destiny") String destiny,
            @RequestParam(value="departure") String departure,
            @RequestParam(value="arrival") String arrival) {

        _bestFlightPriceService.get(origin, destiny, departure, arrival);

        return ResponseEntity.ok().body("success");
    }
}

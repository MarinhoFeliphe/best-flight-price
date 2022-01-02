package com.src.bestflightprice.companies.resources;

import com.src.bestflightprice.companies.dto.FlightOffer;
import com.src.bestflightprice.companies.services.BestFlightPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value="/best-flight-price")
public class BestFlightPriceResource {

    @Autowired
    private BestFlightPriceService _bestFlightPriceService;

    @PostMapping
    public ResponseEntity<List<FlightOffer>> post(
            @RequestParam(value="origin") String origin,
            @RequestParam(value="destiny") String destiny,
            @RequestParam(value="departure") String departure,
            @RequestParam(value="arrival") String arrival) {
        return ResponseEntity.ok().body(
                Stream.of(
                        _bestFlightPriceService.post(origin, destiny, departure, arrival)
                ).flatMap(
                        List::stream
                ).map(
                        FlightOffer::new
                ).collect(
                        Collectors.toList()
                ));
    }

    @GetMapping
    public ResponseEntity<List<FlightOffer>> get(
            @RequestParam(value="type") String type,
            @RequestParam(value="priceType") String priceType) {
        return ResponseEntity.ok().body(
                Stream.of(
                        _bestFlightPriceService.get(type, priceType)
                ).flatMap(
                        List::stream
                ).map(
                        FlightOffer::new
                ).collect(
                        Collectors.toList()
                ));
    }
}

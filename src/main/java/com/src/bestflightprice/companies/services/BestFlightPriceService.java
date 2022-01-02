package com.src.bestflightprice.companies.services;

import com.src.bestflightprice.companies.domain.FlightOffer;
import com.src.bestflightprice.companies.latam.air.lines.service.LatamAirLinesServiceImpl;
import com.src.bestflightprice.companies.voe.azul.service.VoeAzulServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BestFlightPriceService {

    @Autowired
    private LatamAirLinesServiceImpl _latamAirLinesService;
    @Autowired
    private VoeAzulServiceImpl _voeAzulServiceImpl;

    public List<FlightOffer> get(String origin, String destiny, String departure, String arrival) {

        List<FlightOffer> flightOffers = new ArrayList<>();

        flightOffers.addAll(_voeAzulServiceImpl.get(origin, destiny, departure, arrival));
        flightOffers.addAll(_latamAirLinesService.get(origin, destiny, departure, arrival));

        return flightOffers;
    }
}

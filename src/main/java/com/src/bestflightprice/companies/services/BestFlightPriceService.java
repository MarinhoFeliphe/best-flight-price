package com.src.bestflightprice.companies.services;

import com.src.bestflightprice.companies.domain.FlightOffer;
import com.src.bestflightprice.companies.latam.air.lines.service.LatamAirLinesServiceImpl;
import com.src.bestflightprice.companies.repository.FlightOfferRepository;
import com.src.bestflightprice.companies.voe.azul.service.VoeAzulServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BestFlightPriceService {

    @Autowired
    private FlightOfferRepository _flightOfferRepository;
    @Autowired
    private LatamAirLinesServiceImpl _latamAirLinesService;
    @Autowired
    private VoeAzulServiceImpl _voeAzulServiceImpl;

    public List<FlightOffer> post(String origin, String destiny, String departure, String arrival) {

        _flightOfferRepository.deleteAll();

        List<FlightOffer> flightOffers = new ArrayList<>();

        flightOffers.addAll(_voeAzulServiceImpl.post(origin, destiny, departure, arrival));
        flightOffers.addAll(_latamAirLinesService.post(origin, destiny, departure, arrival));

        _flightOfferRepository.saveAll(flightOffers);

        return flightOffers;
    }

    public List<FlightOffer> get(String type, String priceType) {
        return _flightOfferRepository.findByTypeAndPriceTypeOrderByPriceAsc(type, priceType);
    }
}

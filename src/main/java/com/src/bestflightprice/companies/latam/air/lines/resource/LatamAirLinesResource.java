package com.src.bestflightprice.companies.latam.air.lines.resource;

import com.src.bestflightprice.companies.latam.air.lines.service.LatamAirLinesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/voe-azul")
public class LatamAirLinesResource {

    @Autowired
    private LatamAirLinesServiceImpl _latamAirLinesServiceImpl;

    @GetMapping
    public ResponseEntity<Object> get() {

        return ResponseEntity.ok().body(_latamAirLinesServiceImpl.get());
    }
}

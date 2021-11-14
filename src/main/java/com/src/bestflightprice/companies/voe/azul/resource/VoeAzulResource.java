package com.src.bestflightprice.companies.voe.azul.resource;

import com.src.bestflightprice.companies.voe.azul.service.VoeAzulServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/voe-azul")
public class VoeAzulResource {

    @Autowired
    private VoeAzulServiceImpl _voeAzulServiceImpl;

    @GetMapping
    public ResponseEntity<Object> get() {

        return ResponseEntity.ok().body(_voeAzulServiceImpl.get());
    }
}

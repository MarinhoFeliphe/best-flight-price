package com.src.bestflightprice.companies.repository;

import com.src.bestflightprice.companies.domain.FlightOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightOfferRepository extends JpaRepository<FlightOffer, Long> {

    @Query("SELECT f from FlightOffer f where f.type =:type and f.priceType =:priceType order by f.price asc")
    List<FlightOffer> findByTypeAndPriceTypeOrderByPriceAsc(String type, String priceType);

}

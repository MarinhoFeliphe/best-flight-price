package com.src.bestflightprice.companies.voe.azul.service;

import com.src.bestflightprice.companies.domain.Companies;
import com.src.bestflightprice.companies.domain.ErrorLog;
import com.src.bestflightprice.companies.domain.FlightOffer;
import com.src.bestflightprice.companies.repository.ErrorLogRepository;
import com.src.bestflightprice.companies.services.AbstractCompanyService;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VoeAzulServiceImpl extends AbstractCompanyService {

    @Autowired
    private ErrorLogRepository _errorLogRepository;

    private int retryCount = 0;

    public List<FlightOffer> post(String origin, String destiny, String departure, String arrival) {
        WebDriver webDriver = initChromeWebDriver(Companies.VOE_AZUL.getSite());

        try {
            WebDriverWait webDriverWait = new WebDriverWait(webDriver, 15);

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("origin1")));

            WebElement origin1 = webDriver.findElement(By.name("origin1"));

            origin1.sendKeys("(" + origin + ")");
            origin1.sendKeys(Keys.ENTER);

            WebElement destination1 = webDriver.findElement(By.name("destination1"));

            destination1.sendKeys("(" + destiny + ")");
            destination1.sendKeys("(" + destiny+ ")");
            destination1.sendKeys(Keys.ENTER);

            WebElement departure1 = webDriver.findElement(By.name("departure1"));

            departure1.sendKeys(getStringDate(departure));

            WebElement arrival1 = webDriver.findElement(By.name("arrival"));

            arrival1.sendKeys(getStringDate(arrival));

            waitOneSecond();

            WebElement money = webDriver.findElement(By.cssSelector("[value='R']"));

            money.click();
            money.click();

            waitOneSecond();

            WebElement searchTicketsButton = webDriver.findElement(By.id("searchTicketsButton"));

            searchTicketsButton.click();

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbl-depart-flights")));

            List<FlightOffer> flightOffers = new ArrayList<>();

            _setMoneyData(flightOffers, webDriver.findElement(By.id("tbl-depart-flights")), DEPART);
            _setMoneyData(flightOffers, webDriver.findElement(By.id("tbl-return-flights")), RETURN);

            WebElement points = webDriver.findElement(By.cssSelector("[data-value-price='points']"));

            points.click();

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("overview-bx-points")));

            _setPointsData(flightOffers, webDriver.findElement(By.id("tbl-depart-flights")), DEPART);
            _setPointsData(flightOffers, webDriver.findElement(By.id("tbl-return-flights")), RETURN);

            webDriver.quit();

            return flightOffers;

        } catch (Exception exception) {
            webDriver.quit();
            _errorLogRepository.save(
                    new ErrorLog(exception.getMessage(), Companies.VOE_AZUL.getName()));
            if (retryCount <= 4) {
                retryCount++;
                this.post(origin, destiny, departure, arrival);
            }
        }

        return Collections.emptyList();
    }

    private void _setPointsData(List<FlightOffer> flightOffers, WebElement tblDepartFlights, String type) {
        for (WebElement flightItem: tblDepartFlights.findElements(By.className("flight-item"))) {

            FlightOffer flightOffer = new FlightOffer();

            flightOffer.setType(type);
            flightOffer.setCompany(Companies.VOE_AZUL.getName());
            flightOffer.setSite(Companies.VOE_AZUL.getSite());
            flightOffer.setPriceType(POINTS);

            _setDetails(flightItem, flightOffer);

            WebElement flightPriceContainer = flightItem.findElement(
                    By.cssSelector("[data-t='FormatModalPointsAndMoney']"));

            WebElement flightPriceOptions = flightPriceContainer.findElement(By.className("flight-price-options"));

            WebElement firstOption = flightPriceOptions.findElements(By.className("area-radio")).get(0);

            flightOffer.setLog(firstOption.getText());

            WebElement flightPrice = firstOption.findElement(By.className("flight-price"));

            WebElement pricePoints = flightPrice.findElement(By.className("price-points"));

            WebElement farePrice = pricePoints.findElement(By.className("fare-price"));

            flightOffer.setPoints(Long.parseLong(farePrice.getText().replaceAll("\\.", "")));

            flightOffers.add(flightOffer);
        }
    }

    private void _setMoneyData(List<FlightOffer> flightOffers, WebElement tblDepartFlights, String type) {

        for (WebElement flightItem: tblDepartFlights.findElements(By.className("flight-item"))) {

            FlightOffer maisAzulFlightOffer = new FlightOffer();

            maisAzulFlightOffer.setType(type);
            maisAzulFlightOffer.setCompany(Companies.VOE_AZUL.getName());
            maisAzulFlightOffer.setSite(Companies.VOE_AZUL.getSite());
            maisAzulFlightOffer.setPriceType(MONEY);

            _setDetails(flightItem, maisAzulFlightOffer);

            WebElement maisAzulFlightPriceContainer =
                    flightItem.findElements(By.className("flight-price-container")).get(0);

            WebElement maisAzulAreaRadio = maisAzulFlightPriceContainer.findElement(By.className("area-radio"));

            maisAzulFlightOffer.setLog("Mais Azul -> " + maisAzulAreaRadio.getText());

            this._setPriceDetails(maisAzulFlightOffer, maisAzulAreaRadio);

            flightOffers.add(maisAzulFlightOffer);

            FlightOffer azulFlightOffer = new FlightOffer(maisAzulFlightOffer);

            WebElement azulFlightPriceContainer =
                    flightItem.findElements(By.className("flight-price-container")).get(1);

            WebElement azulAreaRadio = azulFlightPriceContainer.findElement(By.className("area-radio"));

            azulFlightOffer.setLog("Azul -> " + azulAreaRadio.getText());

            this._setPriceDetails(azulFlightOffer, azulAreaRadio);

            flightOffers.add(azulFlightOffer);
        }
    }

    private void _setPriceDetails(FlightOffer flightOffer, WebElement radio) {
        if (!"---".equals(radio.getText())) {
            WebElement flightPrice = radio.findElement(By.className("flight-price"));

            flightOffer.setCurrency(flightPrice.findElements(By.className("currency")).get(0).getText());
            flightOffer.setPrice(toDouble(flightPrice.findElement(By.className("fare-price")).getText()));
        }
    }

    private void _setDetails(WebElement flightItem, FlightOffer flightOffer) {
        WebElement flightDetailsContainer =
                flightItem.findElement(By.className("flight-details-container"));

        WebElement clearFix =
                flightDetailsContainer.findElement(By.className("clearfix"));

        WebElement flightDetails =
                clearFix.findElement(By.className("flight-details"));

        WebElement detail =
                flightDetails.findElement(By.className("detail"));

        WebElement showInfo =
                detail.findElement(By.className("show-info"));

        flightOffer.setArrivalTime(showInfo.getAttribute("arrivaltime"));
        flightOffer.setDepartureTime(showInfo.getAttribute("departuretime"));
        flightOffer.setFlightNumber(showInfo.getAttribute("flightnumber"));
    }

}

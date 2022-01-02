package com.src.bestflightprice.companies.latam.air.lines.service;

import com.src.bestflightprice.companies.domain.Companies;
import com.src.bestflightprice.companies.domain.FlightOffer;
import com.src.bestflightprice.companies.services.CompanyService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class LatamAirLinesServiceImpl extends CompanyService {

    private int retryCount = 0;

    public List<FlightOffer> get(String origin, String destiny, String departure, String arrival) {
        WebDriver webDriver = initChromeWebDriver(Companies.LATAM_AIR_LINES.getSite());

        List<FlightOffer> flightOffers = new ArrayList<>();

        try {
            webDriver.findElement(By.id("cookies-politics-button")).click();

            webDriver.findElement(By.id("id-tab-flight")).click();

            WebElement txtInputOrigin_field = webDriver.findElement(By.id("txtInputOrigin_field"));
            txtInputOrigin_field.click();
            txtInputOrigin_field.clear();
            txtInputOrigin_field.sendKeys(origin);
            webDriver.findElement(By.id("btnItemAutoComplete_0")).click();

            WebElement destination = webDriver.findElement(By.id("txtInputDestination_field"));
            destination.click();
            destination.clear();

            waitOneSecond();

            destination.sendKeys(destiny);

            waitOneSecond();

            webDriver.findElement(By.id("btnItemAutoComplete_0")).click();

            webDriver.findElement(By.id("departureDate")).click();

            for(WebElement calendarDay: webDriver.findElements(By.className("CalendarDay"))) {
                if (calendarDay.getAttribute("aria-label").contains(getStringDate(departure))) {
                    calendarDay.click();
                    break;
                }
            }

            for(WebElement calendarDay: webDriver.findElements(By.className("CalendarDay"))) {
                if (calendarDay.getAttribute("aria-label").contains(getStringDate(arrival))) {
                    calendarDay.click();
                    break;
                }
            }

            webDriver.findElement(By.id("btnSearchCTA")).click();
            WebDriverWait webDriverWait = new WebDriverWait(webDriver, 15);

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("WrapperBodyFlights")));

            WebElement sortByDropdownButton = webDriver.findElement(
                    By.cssSelector("[data-testid='sort-by-dropdown-dropdown-button']"));

            sortByDropdownButton.click();

            WebElement priceASC = webDriver.findElement(
                    By.cssSelector("[data-value='PRICE,asc']"));

            priceASC.click();

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("WrapperBodyFlights")));

            _setMoney(flightOffers, webDriver, DEPART);

            webDriver.findElement(By.className("sc-fdQOMr")).click();

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bundle-0-0")));

            webDriver.findElement(By.id("bundle-0-0")).click();

            waitOneSecond();
            waitOneSecond();

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("WrapperCardFlight0")));

            _setMoney(flightOffers, webDriver, RETURN);

            webDriver.quit();

            retryCount = 0;

            return flightOffers;
        } catch (Exception exception) {
            webDriver.quit();
            if (retryCount <= 4) {
                retryCount++;
                this.get(origin, destiny, departure, arrival);
            }
        }

        return flightOffers;
    }

    private void _setMoney(List<FlightOffer> flightOffers, WebDriver webDriver, String type) {
        for (WebElement sceAudoH : webDriver.findElements(By.className("sc-eAudoH"))) {

            FlightOffer flightOffer = new FlightOffer();

            flightOffer.setCompany(Companies.LATAM_AIR_LINES.getName());
            flightOffer.setSite(Companies.LATAM_AIR_LINES.getSite());
            flightOffer.setType(type);

            WebElement ckQlvf = sceAudoH.findElement(By.className("ckQlvf"));

            flightOffer.setLog(ckQlvf.getText());

            _setDetails(ckQlvf, flightOffer);
            _setMoneyPrice(ckQlvf, flightOffer);

            flightOffers.add(flightOffer);
        }
    }

    private void _setDetails(WebElement ckQlvf, FlightOffer flightOffer) {
        WebElement sccbMPqi = ckQlvf.findElement(By.className("sc-cbMPqi"));

        WebElement departure_scizvnbC = sccbMPqi.findElements(By.className("sc-izvnbC")).get(0);

        flightOffer.setDepartureTime(
                departure_scizvnbC.findElement(By.className("sc-gMcBNU")).getText());

        WebElement arrival_scizvnbC = sccbMPqi.findElements(By.className("sc-izvnbC")).get(2);

        flightOffer.setArrivalTime(
                arrival_scizvnbC.findElement(By.className("sc-gMcBNU")).getText());

    }

    private void _setMoneyPrice(WebElement ckQlvf, FlightOffer flightOffer) {
        WebElement scfAJaQT = ckQlvf.findElement(By.className("sc-fAJaQT"));

        WebElement sckWHCRG = scfAJaQT.findElement(By.className("sc-kWHCRG"));

        WebElement scSFOxd = sckWHCRG.findElement(By.className("sc-SFOxd"));

        String[] priceInfo = scSFOxd.getText().split(" ");

        flightOffer.setCurrency(priceInfo[0]);
        flightOffer.setPrice(toDouble(priceInfo[1]));
    }

    @Override
    public String getStringDate(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "MMMM d, yyyy", new Locale("pt", "BR"));

        String[] split = localDate.format(formatter).split("\\s+");

        return split[1].replace(",", "") + " de " + split[0] + " de " + split[2];
    }
}

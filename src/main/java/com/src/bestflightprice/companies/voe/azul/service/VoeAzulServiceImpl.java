package com.src.bestflightprice.companies.voe.azul.service;

import com.src.bestflightprice.companies.services.CompanyService;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Service
public class VoeAzulServiceImpl extends CompanyService {

    public void get(String origin, String destiny, String departure, String arrival) {
        WebDriver webDriver = initChromeWebDriver("https://www.voeazul.com.br/");

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

        WebElement money = webDriver.findElement(By.cssSelector("[value='R']"));

        money.click();
        money.click();

        waitOneSecond();

        WebElement searchTicketsButton = webDriver.findElement(By.id("searchTicketsButton"));

        searchTicketsButton.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbl-depart-flights")));

        System.out.println("MONEY DEPART");

        _setMoneyData(webDriver.findElement(By.id("tbl-depart-flights")));

        System.out.println();
        System.out.println();

        System.out.println("MONEY RETURN");

        _setMoneyData(webDriver.findElement(By.id("tbl-return-flights")));

        WebElement points = webDriver.findElement(By.cssSelector("[data-value-price='points']"));

        points.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("overview-bx-points")));

        System.out.println();
        System.out.println();

        System.out.println("POINTS DEPART");

        _setPointsData(webDriver.findElement(By.id("tbl-depart-flights")));

        System.out.println();

        System.out.println("POINTS RETURN");

        _setPointsData(webDriver.findElement(By.id("tbl-return-flights")));

        webDriver.quit();
    }

    private void _setMoneyData(WebElement tblDepartFlights) {
        for (WebElement flightItem: tblDepartFlights.findElements(By.className("flight-item"))) {

            WebElement maisAzulFlightPriceContainer =
                    flightItem.findElements(By.className("flight-price-container")).get(0);

            WebElement maisAzulAreaRadio = maisAzulFlightPriceContainer.findElement(By.className("area-radio"));

            System.out.println("Mais Azul -> " + maisAzulAreaRadio.getText());

            WebElement azulFlightPriceContainer =
                    flightItem.findElements(By.className("flight-price-container")).get(1);

            WebElement azulAreaRadio = azulFlightPriceContainer.findElement(By.className("area-radio"));

            System.out.println("Azul -> " + azulAreaRadio.getText());

            System.out.println();
            System.out.println();
        }
    }

    private void _setPointsData(WebElement tblDepartFlights) {
        for (WebElement flightItem: tblDepartFlights.findElements(By.className("flight-item"))) {

            WebElement flightPriceContainer = flightItem.findElement(
                    By.cssSelector("[data-t='FormatModalPointsAndMoney']"));

            System.out.println(flightPriceContainer.getText());

            System.out.println();
            System.out.println();
        }
    }

    /*if (!"---".equals(areaRadio.getText())) {
        WebElement flightPrice = areaRadio.findElement(By.className("flight-price"));
        String currency = flightPrice.findElements(By.className("currency")).get(0).getText();
        String farePrice = flightPrice.findElement(By.className("fare-price")).getText();
        System.out.println(currency + ": " + farePrice);
    }*/
}

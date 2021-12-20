package com.src.bestflightprice.companies.voe.azul.service;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

@Service
public class VoeAzulServiceImpl {

    public String get() {
        WebDriverManager.chromedriver().setup();

        WebDriver webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();

        webDriver.get(_SITE);

        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 15);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("origin1")));

        WebElement origin = webDriver.findElement(By.name("origin1"));

        origin.sendKeys(_ORIGIN);
        origin.sendKeys(Keys.ENTER);

        WebElement destination = webDriver.findElement(By.name("destination1"));

        destination.sendKeys(_DESTINATION);
        destination.sendKeys(_DESTINATION);
        destination.sendKeys(Keys.ENTER);

        WebElement departure = webDriver.findElement(By.name("departure1"));

        departure.sendKeys(_DEPARTURE);

        WebElement arrival = webDriver.findElement(By.name("arrival"));

        arrival.sendKeys(_ARRIVAL);

        WebElement money = webDriver.findElement(By.cssSelector("[value='R']"));

        money.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

        return "";
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

            WebElement flightPriceContainer = flightItem.findElement(By.cssSelector("[data-t='FormatModalPointsAndMoney']"));

            System.out.println(flightPriceContainer.getText());

            System.out.println();
            System.out.println();
        }
    }

    private static String _SITE = "https://www.voeazul.com.br/";
    private static String _ORIGIN = "Curitiba (CWB)";
    private static String _DESTINATION = "Sao Paulo - Todos os Aeroportos (SAO)";
    private static String _DEPARTURE = "10/01/2022";
    private static String _ARRIVAL = "20/01/2022";

    /*if (!"---".equals(areaRadio.getText())) {
        WebElement flightPrice = areaRadio.findElement(By.className("flight-price"));
        String currency = flightPrice.findElements(By.className("currency")).get(0).getText();
        String farePrice = flightPrice.findElement(By.className("fare-price")).getText();
        System.out.println(currency + ": " + farePrice);
    }*/
}

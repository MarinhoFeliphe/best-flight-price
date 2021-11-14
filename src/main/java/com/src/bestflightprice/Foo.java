package com.src.bestflightprice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Foo {

    public static void main(String[] args) {

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

        WebElement points = webDriver.findElement(By.cssSelector("[value='TP']"));

        points.click();

        WebElement searchTicketsButton = webDriver.findElement(By.id("searchTicketsButton"));

        searchTicketsButton.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("flight-item")));
        List<WebElement> flightItems = webDriver.findElements(By.className("flight-item"));

        for (WebElement flightItem: flightItems) {

            WebElement flightPriceContainer = flightItem.findElement(By.className("flight-price-container"));

            WebElement flightPriceOptionsContainer = flightPriceContainer.findElement(By.className("flight-price-options"));

            List<WebElement> priceOptions = flightPriceOptionsContainer.findElements(By.className("area-radio"));

            for (WebElement priceOption: priceOptions) {

                WebElement pricePoints = priceOption.findElement(By.className("price-points"));

                String pointsRequired = pricePoints.findElement(By.className("fare-price")).getText();

                try {
                    WebElement priceMoney = priceOption.findElement(By.className("price-money"));

                    System.out.println(pointsRequired + " + R$ " + priceMoney.findElement(By.className("fare-price")).getText());
                } catch (Exception e) {
                    System.out.println(pointsRequired);
                }
            }

            System.out.println();
        }
    }

    private static String _SITE = "https://www.voeazul.com.br/";
    private static String _ORIGIN = "Curitiba (CWB)";
    private static String _DESTINATION = "Sao Paulo - Todos os Aeroportos (SAO)";
    private static String _DEPARTURE = "26/11/2021";
    private static String _ARRIVAL = "10/12/2021";
}

package com.src.bestflightprice.companies.latam.air.lines.service;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

@Service
public class LatamAirLinesServiceImpl {

    public String get() {

        WebDriverManager.chromedriver().setup();

        WebDriver webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();

        webDriver.get("https://www.latamairlines.com/br/pt");

        webDriver.findElement(By.id("cookies-politics-button")).click();

        webDriver.findElement(By.id("id-tab-flight")).click();

        WebElement origin = webDriver.findElement(By.id("txtInputOrigin_field"));
        origin.click();
        origin.clear();
        origin.sendKeys("Curitiba");
        webDriver.findElement(By.id("btnItemAutoComplete_0")).click();

        WebElement destination = webDriver.findElement(By.id("txtInputDestination_field"));
        destination.click();
        destination.clear();
        destination.sendKeys("Sao Paulo");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        webDriver.findElement(By.id("btnItemAutoComplete_0")).click();

        webDriver.findElement(By.id("departureDate")).click();

        for(WebElement calendarDay: webDriver.findElements(By.className("CalendarDay"))) {
            if (calendarDay.getAttribute("aria-label").contains("10 de janeiro de 2022")) {
                calendarDay.click();
                break;
            }
        }

        for(WebElement calendarDay: webDriver.findElements(By.className("CalendarDay"))) {
            if (calendarDay.getAttribute("aria-label").contains("20 de janeiro de 2022")) {
                calendarDay.click();
                break;
            }
        }

        webDriver.findElement(By.id("btnSearchCTA")).click();
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 15);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("WrapperBodyFlights")));

        WebElement wrapperBodyFlights = webDriver.findElement(By.id("WrapperBodyFlights"));

        WebElement availableFlightsOL =
                wrapperBodyFlights.findElement(By.cssSelector("[aria-label='Voos disponíveis.']"));

        for (WebElement option : availableFlightsOL.findElements(By.className("sc-eAudoH"))) {
            System.out.println(option.findElement(By.className("ckQlvf")).getText());
            System.out.println();
            System.out.println();
        }

        webDriver.quit();

        return "success";
    }
}

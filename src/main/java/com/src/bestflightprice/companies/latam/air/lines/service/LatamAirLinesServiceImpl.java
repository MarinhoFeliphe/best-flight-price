package com.src.bestflightprice.companies.latam.air.lines.service;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

        webDriver.findElement(By.id("cookies-politics-button")).click();

        return "success";
    }
}

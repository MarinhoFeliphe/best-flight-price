package com.src.bestflightprice.companies.voe.azul.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

@Service
public class VoeAzulServiceImpl {

    public String get() {

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.setBinary("/app/.apt/usr/bin/google-chrome");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--no-sandbox");

        ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);

        if (System.getProperty("webdriver.chrome.driver") == null) {
            System.setProperty("webdriver.chrome.driver", "/app/.chromedriver/bin/chromedriver");
        }

        chromeDriver.manage().window().maximize();

        chromeDriver.get("https://www.latamairlines.com/br/pt");

        chromeDriver.findElement(By.id("cookies-politics-button")).click();

        chromeDriver.findElement(By.id("id-tab-flight")).click();

        /*WebElement origin = chromeDriver.findElement(By.id("txtInputOrigin_field"));
        origin.click();
        origin.clear();
        origin.sendKeys("Curitiba");
        chromeDriver.findElement(By.id("btnItemAutoComplete_0")).click();

        WebElement destination = chromeDriver.findElement(By.id("txtInputDestination_field"));
        destination.click();
        destination.clear();
        destination.sendKeys("Sao Paulo");
        chromeDriver.findElement(By.id("btnItemAutoComplete_0")).click();

        chromeDriver.findElement(By.id("departureDate")).click();

        for(WebElement calendarDay: chromeDriver.findElements(By.className("CalendarDay"))) {
            if (calendarDay.getAttribute("aria-label").contains("17 de novembro de 2021")) {
                calendarDay.click();
                break;
            }
        }

        for(WebElement calendarDay: chromeDriver.findElements(By.className("CalendarDay"))) {
            if (calendarDay.getAttribute("aria-label").contains("8 de dezembro de 2021")) {
                calendarDay.click();
                break;
            }
        }

        chromeDriver.findElement(By.id("btnSearchCTA")).click();

        chromeDriver.findElement(By.id("cookies-politics-button")).click();*/

        return chromeDriver.getPageSource();
    }
}

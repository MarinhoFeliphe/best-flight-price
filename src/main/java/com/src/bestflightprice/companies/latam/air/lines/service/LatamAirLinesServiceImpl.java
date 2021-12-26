package com.src.bestflightprice.companies.latam.air.lines.service;

import com.src.bestflightprice.companies.services.CompanyService;
import org.openqa.selenium.By;
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
public class LatamAirLinesServiceImpl extends CompanyService {

    public void get(String origin, String destiny, String departure, String arrival) {
        WebDriver webDriver = initChromeWebDriver("https://www.latamairlines.com/br/pt");

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

        for (WebElement option : webDriver.findElements(By.className("sc-eAudoH"))) {
            System.out.println(option.findElement(By.className("ckQlvf")).getText());
            System.out.println();
            System.out.println();
        }

        webDriver.quit();
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

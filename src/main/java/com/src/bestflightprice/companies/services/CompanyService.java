package com.src.bestflightprice.companies.services;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class CompanyService implements ICompanyService {

    protected WebDriver initChromeWebDriver(String site) {
        WebDriverManager.chromedriver().setup();

        WebDriver webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();

        webDriver.get(site);

        return webDriver;
    }

    protected void waitOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getStringDate(String date) {
        return date;
    }
}

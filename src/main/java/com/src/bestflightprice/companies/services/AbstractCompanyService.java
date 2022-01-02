package com.src.bestflightprice.companies.services;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class AbstractCompanyService implements ICompanyService {

    protected final String DEPART = "DEPART";
    protected final String RETURN = "RETURN";
    protected final String MONEY = "MONEY";
    protected final String POINTS = "POINTS";

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

    protected void waitSeconds(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected double toDouble(String price) {
        return Double.parseDouble(price.replaceAll("\\.", "").replace(",", "."));
    }

    @Override
    public String getStringDate(String date) {
        return date;
    }
}

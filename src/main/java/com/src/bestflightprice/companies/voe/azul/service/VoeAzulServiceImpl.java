package com.src.bestflightprice.companies.voe.azul.service;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

@Service
public class VoeAzulServiceImpl {

    public String get() {

        WebDriverManager.chromedriver().setup();

        WebDriver webDriver = new ChromeDriver();

        webDriver.get("https://www.google.com");

        return webDriver.getPageSource();
    }

}

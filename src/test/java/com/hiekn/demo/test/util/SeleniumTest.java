//package com.hiekn.demo.test;
//
//import java.util.List;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
//
//public class SeleniumTest {
//	
//	private static WebDriver driver;
//	
//	private static final String URL = "http://news.sohu.com/20170320/n483936679.shtml";
////	private static final String URL = "https://www.crunchbase.com/organization/microsoft/people";
//	
//	public static void main(String[] args) throws Exception {
//		
//		System.setProperty("webdriver.gecko.driver", "driver/geckodriver.exe");
//		
//		driver = new FirefoxDriver();
////		driver.manage().window().maximize();
//		driver.get(URL);
//
//		Thread.sleep(2000);
//		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
//		Thread.sleep(1000);
////		List<WebElement> eles = driver.findElements(By.cssSelector(".section-list li .info-block h4 a"));
//		System.out.println("find");
//		List<WebElement> eles = driver.findElements(By.cssSelector("div.content-box h1"));
//		System.out.println(eles.size()+"条数据");
//		for (WebElement e : eles) {
//			System.out.println(e.getText());
//		}
//		
//		System.out.println("finish");
//	}
//
//}

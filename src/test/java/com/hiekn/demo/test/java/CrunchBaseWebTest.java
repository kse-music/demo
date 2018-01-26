//package com.hiekn.demo.test;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import org.apache.log4j.Logger;
//import org.bson.Document;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//
//import com.google.common.collect.Lists;
//import com.mongodb.MongoClient;
//
//public class CrunchBaseWebTest {
//	
//	private static MongoClient mongoClient = new MongoClient("127.0.0.1",27017);
//
//	static Logger log = Logger.getLogger(CrunchBaseWebTest.class);
//	static String firefoxPath = "D:\\Program Files\\Mozilla Firefox\\firefox.exe";
////	static String wdPath = "D:\\Download\\geckodriver-v0.15.0-win64\\geckodriver.exe";
//	static String wdPath = "chromedriver.exe";
//	public static void main(String[] args) throws InterruptedException {
//		System.setProperty("webdriver.chrome.driver", wdPath); 
//		System.setProperty("webdriver.gecko.driver", "driver/geckodriver.exe");
////		WebDriver driver = new ChromeDriver();
//		WebDriver driver = new FirefoxDriver();
//		List<String> keywordList = Lists.newArrayList("Google");
//		for (String orgName : keywordList) {
//			String orgUrl = "https://www.crunchbase.com/organization/" + orgName; //公司详情页
//			driver.get(orgUrl);
//			String orgSource = driver.getPageSource();
//			try {
//				mongoClient.getDatabase("crunchbase").getCollection("com").insertOne(new Document("source",orgSource).append("com", orgName).append("url", orgUrl));
//			} catch (Exception e) {
//				log.error(e);
//			}
//			log.info("com insert success");
//			TimeUnit.SECONDS.sleep(3);
//			String orgTeamUrl = "https://www.crunchbase.com/organization/" + orgName + "/people"; //公司人员列表页
//			driver.get(orgTeamUrl);;
//			String peoSource = driver.getPageSource();
//			Elements peoElements = Jsoup.parse(peoSource).select("ul.section-list.container > li");
//			for (Element element : peoElements) {
//				String peoUrl = "https://www.crunchbase.com" + element.select("a").attr("href");
//				driver.get(peoUrl);
//				String peoOriSource = driver.getPageSource();
//				try {
//					String peoDetail = Jsoup.parse(peoOriSource).select("div.description-ellipsis").text();
//					mongoClient.getDatabase("crunchbase").getCollection("people").insertOne(new Document("source",peoOriSource)
//					.append("com", orgName).append("url", peoUrl).append("detail", peoDetail));
//					log.info(peoUrl + " insert success " + peoDetail);
//				} catch (Exception e) {
//					log.error(e);
//				}
//				TimeUnit.SECONDS.sleep(5);
//			}
//			log.info("more people start");
//			//获得更多的人员
//			int count = 0;
//			try {
//				count = Integer.parseInt(Jsoup.parse(peoSource).select("h2#current_team > span").text().trim().replace("(", "").replace(")", "").replace(",", "").replace(" ", ""));
//				log.info(count);
//			} catch (Exception e) {
//				log.error(e);
//			}
//			
//			for (int i = 2; i < (count-6)/14 + 2; i++) {
//				log.info("page " + i + " start");
//				String peoMoreUrl = "https://www.crunchbase.com/organization/" + orgName + "/people?page=" + i;
//				driver.get(peoMoreUrl);
//				String peoMoreSource = driver.getPageSource();
//				Elements peoMoreElements = Jsoup.parse(peoMoreSource).select("ul.section-list.container > li");
//				if (peoMoreElements != null) {
//					for (Element element : peoMoreElements) {
//						String peoMoreOriUrl = "https://www.crunchbase.com" + element.select("a").attr("href");
//						driver.get(peoMoreOriUrl);
//						String peoMoreOriSource = driver.getPageSource();
//						try {
//							String peoDetail = Jsoup.parse(peoMoreOriUrl).select("div.description-ellipsis").text();
//							mongoClient.getDatabase("crunchbase").getCollection("people").insertOne(new Document("source",peoMoreOriSource)
//							.append("com", orgName).append("url", peoMoreOriUrl).append("detail", peoDetail));
//							log.info(peoMoreOriUrl + " insert success " + peoDetail);
//						} catch (Exception e) {
//							log.error(e);
//						}
//						TimeUnit.SECONDS.sleep(5);
//					}
//				}
//				TimeUnit.SECONDS.sleep(3);
//			}
//		}
//	}
//	
//}

package base;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	public static WebDriver driver;
	
	public static WebDriver setUp() throws IOException {
		
		if(driver==null) {
//			/Users/bhumidobariya/eclipse-workspace/castle_hill_example/src/test/resources/configfiles/config.properties
			FileReader fr = new FileReader(System.getProperty("user.dir")+"/src/test/resources/configfiles/config.properties");
//			FileReader fr = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configfiles\\config.properties");
			
			Properties property = new Properties();
			property.load(fr);
			String browser = property.getProperty("browser");
			// below line to use http-jdk-client library to resolve selenium own error
			System.setProperty("webdriver.http.factory", "jdk-http-client");
			
			if(browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}
		}
		return driver;
	}
	
	public static void tearDown() {
		driver.quit();
	}
}

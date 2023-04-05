package stepdefinitions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.Base;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login {
	
	WebDriver driver;
	
	@Given("I am on the Sauce Demo Login Page")
	public void userOnLoginPage() throws IOException {
		
		driver = Base.setUp();

		FileReader fr = new FileReader(System.getProperty("user.dir")+"/src/test/resources/configfiles/config.properties");
		Properties property = new Properties();
		property.load(fr);
		
		driver.get(property.getProperty("testurl"));
	}
	
	@When("I fill the account information for account User into the Username field and the Password field")
	public void fillDetails(DataTable dt) throws IOException {
				
		driver.findElement(By.id("user-name")).sendKeys(dt.row(1).get(0));
		driver.findElement(By.id("password")).sendKeys(dt.row(1).get(1));
	}
	
	@And("I click the Login button")
	public void clickOnLoginButton() {
		driver.findElement(By.id("login-button")).click();
	}
	
	@Then("I am redirected to the Sauce Demo Main Page")
	public void mainPageVerification() {
		String actualtext= driver.getCurrentUrl();
		Assert.assertEquals(actualtext, "https://www.saucedemo.com/inventory.html" );
	}

	@And("I verify the App Logo exists")
	public void logoVerification() {
		String actualtext= driver.findElement(By.className("app_logo")).getText();
		Assert.assertEquals(actualtext, "Swag Labs" );
	}
	
	//-------------------------------------------------second scenario-------------------------------------------

	@Then("I verify the Error Message contains the text")
	public void errorMessageVerification() {
		String actualtext = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
		Assert.assertEquals(actualtext, "Epic sadface: Sorry, this user has been locked out." );
		
		Base.tearDown(); // closing after all operation.
	}
}

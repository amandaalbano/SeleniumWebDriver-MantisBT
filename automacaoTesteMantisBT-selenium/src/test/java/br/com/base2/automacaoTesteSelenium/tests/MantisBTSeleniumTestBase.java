package br.com.base2.automacaoTesteSelenium.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class MantisBTSeleniumTestBase {

	// para conexão entre TestNG e browser
	private WebDriver driver;

	public WebDriver getDriver() {
		return this.driver;
	}

	public void setUrl(String url) {
		this.driver.get(url);
	}

	@BeforeClass
	// Inicialização do objeto driver
	public void setup() {
		System.setProperty("webdriver.gecko.driver", "/home/amanda/Selenium/Firefox/geckodriver");
		this.driver = new FirefoxDriver();
		driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterClass
	public void afterTests() {
		if (this.driver != null) {
			this.driver.close();
		}
	}
}

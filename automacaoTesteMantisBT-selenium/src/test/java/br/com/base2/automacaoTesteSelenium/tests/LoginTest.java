package br.com.base2.automacaoTesteSelenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

public class LoginTest extends MantisBTSeleniumTestBase {

	@Test
	public void verificaUsuarioESenhaEmBranco() {
		setUrl("https://mantis-prova.base2.com.br/login_page.php?return=%2Fmy_view_page.php");
		highLightElement(getDriver(), getDriver().findElement(By.name("username")));
		getDriver().findElement(By.name("username")).sendKeys("");
		highLightElement(getDriver(), getDriver().findElement(By.name("password")));
		getDriver().findElement(By.name("password")).sendKeys("");
		getDriver().findElement(By.className("button")).click();
		Assert.assertTrue(getDriver().findElement(By.xpath("//div/font")).isDisplayed());
		highLightElement(getDriver(), getDriver().findElement(By.xpath("//div/font")));
		Assert.assertTrue(getDriver().findElement(By.xpath("//div/font")).isDisplayed());
		Assert.assertEquals(getDriver().findElement(By.xpath("//div/font")).getText(),
				"Your account may be disabled or blocked or the username/password you entered is incorrect.");

	}

	@Test
	public void verificarUsuarioInvalido() {
		setUrl("https://mantis-prova.base2.com.br/login_page.php?return=%2Fmy_view_page.php");
		highLightElement(getDriver(), getDriver().findElement(By.name("username")));
		getDriver().findElement(By.name("username")).sendKeys("amanda");
		highLightElement(getDriver(), getDriver().findElement(By.name("password")));
		getDriver().findElement(By.name("password")).sendKeys("x9vp70");
		getDriver().findElement(By.className("button")).click();
		Assert.assertTrue(getDriver().findElement(By.xpath("//div/font")).isDisplayed());
		highLightElement(getDriver(), getDriver().findElement(By.xpath("//div/font")));
		Assert.assertTrue(getDriver().findElement(By.xpath("//div/font")).isDisplayed());
		Assert.assertEquals(getDriver().findElement(By.xpath("//div/font")).getText(),
				"Your account may be disabled or blocked or the username/password you entered is incorrect.");

	}

	@Test
	public void verificarSenhaInvalida() {
		setUrl("https://mantis-prova.base2.com.br/login_page.php?return=%2Fmy_view_page.php");
		highLightElement(getDriver(), getDriver().findElement(By.name("username")));
		getDriver().findElement(By.name("username")).sendKeys("amanda.goncalves");
		highLightElement(getDriver(), getDriver().findElement(By.name("password")));
		getDriver().findElement(By.name("password")).sendKeys("123456");
		getDriver().findElement(By.className("button")).click();
		Assert.assertTrue(getDriver().findElement(By.xpath("//div/font")).isDisplayed());
		highLightElement(getDriver(), getDriver().findElement(By.xpath("//div/font")));
		Assert.assertTrue(getDriver().findElement(By.xpath("//div/font")).isDisplayed());
		Assert.assertEquals(getDriver().findElement(By.xpath("//div/font")).getText(),
				"Your account may be disabled or blocked or the username/password you entered is incorrect.");

	}

	@Test
	public void efetuarLoginComSucesso() {
		setUrl("https://mantis-prova.base2.com.br/login_page.php?return=%2Fmy_view_page.php");
		highLightElement(getDriver(), getDriver().findElement(By.name("username")));
		getDriver().findElement(By.name("username")).sendKeys("amanda.goncalves");
		highLightElement(getDriver(), getDriver().findElement(By.name("password")));
		getDriver().findElement(By.name("password")).sendKeys("x9vp70");
		getDriver().findElement(By.className("button")).click();
		String urlAtual = getDriver().getCurrentUrl();
		Assert.assertEquals(urlAtual, "https://mantis-prova.base2.com.br/my_view_page.php");

	}

	@Test
	public void efetuarLogout() {
		Assert.assertEquals(getDriver().getCurrentUrl(), "https://mantis-prova.base2.com.br/my_view_page.php");
		highLightElement(getDriver(), getDriver().findElement(By.linkText("Logout")));
		getDriver().findElement(By.linkText("Logout")).click();
		Assert.assertEquals(getDriver().getCurrentUrl(), "https://mantis-prova.base2.com.br/login_page.php");

	}

	public void highLightElement(WebDriver getDriver, WebElement elemento) {
		((JavascriptExecutor) getDriver).executeScript("arguments[0].style.border='3px solid yellow'", elemento);

	}

}

package br.com.base2.automacaoTesteSelenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditarReportIssueTest extends MantisBTSeleniumTestBase {

	@Test
	public void editarReportIssue() {
		paginaLogin();
		getDriver().findElement(By.linkText("View Issues")).click();
		highLightElement(getDriver(), getDriver().findElement(By.xpath("//tr[4]/td[2]/a/img")));
		getDriver().findElement(By.xpath("//tr[4]/td[2]/a/img")).click();
		Select selecao = new Select(getDriver().findElement(By.name("severity")));
		selecao.selectByVisibleText("major");
		getDriver().findElement(By.cssSelector("input[value='Update Information']")).click();

	}

	public void paginaLogin() {
		setUrl("https://mantis-prova.base2.com.br/login_page.php?return=%2Fmy_view_page.php");
		highLightElement(getDriver(), getDriver().findElement(By.name("username")));
		getDriver().findElement(By.name("username")).sendKeys("amanda.goncalves");
		highLightElement(getDriver(), getDriver().findElement(By.name("password")));
		getDriver().findElement(By.name("password")).sendKeys("x9vp70");
		getDriver().findElement(By.className("button")).click();
		String urlAtual = getDriver().getCurrentUrl();
		Assert.assertEquals(urlAtual, "https://mantis-prova.base2.com.br/my_view_page.php");
	}
	public void highLightElement(WebDriver getDriver, WebElement elemento) {
		((JavascriptExecutor) getDriver).executeScript("arguments[0].style.border='3px solid yellow'", elemento);
	}

}

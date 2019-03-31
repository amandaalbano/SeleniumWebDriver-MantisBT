package br.com.base2.automacaoTesteSelenium.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ReportissueTest extends MantisBTSeleniumTestBase {

	@Test
	public void selecionarProjeto() {
		paginaLogin();
		highLightElement(getDriver(), getDriver().findElement(By.linkText("Report Issue")));
		getDriver().findElement(By.linkText("Report Issue")).click();
		Assert.assertEquals(getDriver().getCurrentUrl(),
				"https://mantis-prova.base2.com.br/login_select_proj_page.php?ref=bug_report_page.php");
		highLightElement(getDriver(), getDriver().findElement(By.cssSelector("input[value='Select Project']")));
		getDriver().findElement(By.cssSelector("input[value='Select Project']")).click();
		Assert.assertEquals(getDriver().getCurrentUrl(), "https://mantis-prova.base2.com.br/bug_report_page.php");

	}

	@Test
	public void relatarBugCamposVazios() {
		getDriver().findElement(By.cssSelector("input[value='Submit Report']")).click();
		Assert.assertEquals(getDriver().getCurrentUrl(), "https://mantis-prova.base2.com.br/bug_report.php");
		Assert.assertTrue(getDriver().findElement(By.xpath("//table[@class='width50']/tbody")).isDisplayed());
		Assert.assertEquals(getDriver()
				.findElement(By.xpath("//table[@class='width50']/tbody/tr/td[@class='form-title']")).getText(),
				"APPLICATION ERROR #11");
		highLightElement(getDriver(), getDriver().findElement(By.xpath("//table[@class='width50']/tbody/tr[2]/td/p")));
		Assert.assertEquals(getDriver().findElement(By.xpath("//table[@class='width50']/tbody/tr[2]/td/p")).getText(),
				"A necessary field \"Summary\" was empty. Please recheck your inputs.");
		Assert.assertEquals(getDriver().findElement(By.xpath("//table[@class='width50']/tbody/tr[3]/td/p")).getText(),
				"Please use the \"Back\" button in your web browser to return to the previous page. There you can correct whatever problems were identified in this error or select another action. You can also click an option from the menu bar to go directly to a new section.");
		getDriver().findElement(By.linkText("Report Issue")).click();

	}

	@Test
	public void verificarCampoSumarioNaoPreenchido() {
		highLightElement(getDriver(), getDriver().findElement(By.name("category_id")));
		Select selecao = new Select(getDriver().findElement(By.name("category_id")));
		selecao.selectByVisibleText("[All Projects] Desafio jMeter");
		getDriver().findElement(By.cssSelector("input[value='Submit Report']")).click();
		Assert.assertEquals(getDriver().getCurrentUrl(), "https://mantis-prova.base2.com.br/bug_report.php");
		Assert.assertTrue(getDriver().findElement(By.xpath("//table[@class='width50']/tbody")).isDisplayed());
		Assert.assertEquals(getDriver()
				.findElement(By.xpath("//table[@class='width50']/tbody/tr/td[@class='form-title']")).getText(),
				"APPLICATION ERROR #11");
		highLightElement(getDriver(), getDriver().findElement(By.xpath("//table[@class='width50']/tbody/tr[2]/td/p")));
		Assert.assertEquals(getDriver().findElement(By.xpath("//table[@class='width50']/tbody/tr[2]/td/p")).getText(),
				"A necessary field \"Summary\" was empty. Please recheck your inputs.");
		Assert.assertEquals(getDriver().findElement(By.xpath("//table[@class='width50']/tbody/tr[3]/td/p")).getText(),
				"Please use the \"Back\" button in your web browser to return to the previous page. There you can correct whatever problems were identified in this error or select another action. You can also click an option from the menu bar to go directly to a new section.");
		getDriver().findElement(By.linkText("Report Issue")).click();

	}

	@Test
	public void verificarCampoDescricaoNaoPreenchido() {
		highLightElement(getDriver(), getDriver().findElement(By.name("category_id")));
		Select selecao = new Select(getDriver().findElement(By.name("category_id")));
		selecao.selectByVisibleText("[All Projects] Desafio jMeter");
		getDriver().findElement(By.name("summary")).sendKeys("Teste - automação de teste campo resumo");
		getDriver().findElement(By.cssSelector("input[value='Submit Report']")).click();
		Assert.assertEquals(getDriver().getCurrentUrl(), "https://mantis-prova.base2.com.br/bug_report.php");
		Assert.assertTrue(getDriver().findElement(By.xpath("//table[@class='width50']/tbody")).isDisplayed());
		Assert.assertEquals(getDriver()
				.findElement(By.xpath("//table[@class='width50']/tbody/tr/td[@class='form-title']")).getText(),
				"APPLICATION ERROR #11");
		highLightElement(getDriver(), getDriver().findElement(By.xpath("//table[@class='width50']/tbody/tr[2]/td/p")));
		Assert.assertEquals(getDriver().findElement(By.xpath("//table[@class='width50']/tbody/tr[2]/td/p")).getText(),
				"A necessary field \"Description\" was empty. Please recheck your inputs.");
		Assert.assertEquals(getDriver().findElement(By.xpath("//table[@class='width50']/tbody/tr[3]/td/p")).getText(),
				"Please use the \"Back\" button in your web browser to return to the previous page. There you can correct whatever problems were identified in this error or select another action. You can also click an option from the menu bar to go directly to a new section.");
		getDriver().findElement(By.linkText("Report Issue")).click();

	}

	@Test
	public void enviarRelatorioDeBug() {
		highLightElement(getDriver(), getDriver().findElement(By.name("category_id")));
		Select selecao = new Select(getDriver().findElement(By.name("category_id")));
		selecao.selectByVisibleText("[All Projects] Desafio jMeter");
		getDriver().findElement(By.name("summary")).sendKeys("Teste - automação de teste campo resumo");
		getDriver().findElement(By.name("description")).sendKeys("Teste campo Descrição");
		getDriver().findElement(By.cssSelector("input[value='Submit Report']")).click();
		Assert.assertTrue(getDriver().findElement(By.xpath("//table[@class='width100']")).isDisplayed());
		Assert.assertTrue(getDriver().findElement(By.id("buglist")).isDisplayed());
		getDriver().findElement(By.linkText("Report Issue")).click();

	}

	@Test
	public void uploadArquivo() {
		highLightElement(getDriver(), getDriver().findElement(By.name("category_id")));
		Select selecao = new Select(getDriver().findElement(By.name("category_id")));
		selecao.selectByVisibleText("[All Projects] Desafio jMeter");
		getDriver().findElement(By.name("summary")).sendKeys("Teste - automação de teste campo resumo");
		getDriver().findElement(By.name("description")).sendKeys("Teste campo Descrição");
		getDriver().findElement(By.id("ufile[]")).sendKeys(
				"/home/amanda/eclipse-workspace/projeto-automacao-de-testes-MantisBT/automacaoTesteMantisBT-selenium/mantis.png");
		getDriver().findElement(By.cssSelector("input[value='Submit Report']")).click();
		Assert.assertTrue(getDriver().findElement(By.xpath("//table[@class='width100']")).isDisplayed());
		Assert.assertTrue(getDriver().findElement(By.id("buglist")).isDisplayed());
		highLightElement(getDriver(), getDriver().findElement(By.xpath("//td[5]/a")));
		Assert.assertTrue(getDriver().findElement(By.xpath("//td[5]/a")).isDisplayed());
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

package com.Amazon.Test_assignment;

import java.awt.print.Printable;
import java.io.File;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Nm.Base.BaseClass;
import com.Nm.Pom.Locators;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

//********Extend Report**********//
public class Amazon_Testing extends BaseClass {

	@BeforeTest
	public void startReport() {

		htmlReporter = new ExtentHtmlReporter(".//Report//sample.html");

		// initialize ExtentReports and attach the HtmlReporter
		report = new ExtentReports();

		// htmlReporter.setAppendExisting(false);
		report.attachReporter(htmlReporter);
		report.setSystemInfo("Host name", "localhost");
		report.setSystemInfo("Environemnt", "QA");
		report.setSystemInfo("user", "Automation Team");

		// configuration items to change the look and feel
		// add content, manage tests etc

		htmlReporter.config().setDocumentTitle("Extent Report ");
		htmlReporter.config().setReportName("Test Report");

		// htmlReporter.config().setTheme(Theme.STANDARD);

	}

	/*
	 * Step1 : Open https://www.amazon.in/. Step2 : Click on the hamburger menu in
	 * the top left corner. Step3 : Scroll own and then Click on the TV, Appliances
	 * and Electronics link under Shop by Department section. Step4 :Then click on
	 * Televisions under Tv, Audio & Cameras sub section. Step5 :Scroll down and
	 * filter the results by Brand ‘Samsung’. Step6 : Sort the Samsung results with
	 * price High to Low. Step7 :Click on the second highest priced item (whatever
	 * that maybe at the time of automating).
	 * 
	 * Step8 : Switch the Window
	 * 
	 * Step9 :Assert that “About this item” section is present and log this section
	 * text to console/report.
	 * 
	 */

//*******************************************************M3 Non Rx & OTC flow**********************************************************************//
	@Test(enabled = true)
	public void AmazonTest() throws Throwable {

		logger = report.createTest("AmazonTest");
		logger.log(Status.PASS, "*************AmazonTest********************");

		Locators m = new Locators();

//Step1 : Open https://www.amazon.in/.	

		driver.navigate().to(amazonurl);

		driver.manage().window().maximize();

		logger.log(Status.PASS, " Url launced Successfully");
		System.out.println("Url launced Successfully");

//Step2 : Click on the hamburger menu in the top left corner.	

		m.getHamburger().click();

		logger.log(Status.PASS, " Hamburger menu clicked successsfully");
		System.out.println("Hamburger menu clicked successsfully");

//Step3 : Scroll own and then Click on the TV, Appliances and Electronics link under Shop by Department section.

		Thread.sleep(1000);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement TvElement = m.getTvandapplicances();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", TvElement);

		m.getTvandapplicances().click();

		logger.log(Status.PASS, " Scrolled down and clicked the menu Tv & appliances successsfully");
		System.out.println("Scrolled down and clicked the menu Tv & appliances successsfully");

//Step4 :Then click on Televisions under Tv, Audio & Cameras sub section.

		driver.findElement(By.xpath("//a[contains(text(),'Televisions')]")).click();
		
		logger.log(Status.PASS, " clicked the menu Televisions successsfully");
		System.out.println("clicked the menu Televisions successsfully");

//Step 5 : Scroll down and filter the results by Brand ‘Samsung’.

		Thread.sleep(1000);

		WebElement samsung = m.getBrands();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", samsung);

		Thread.sleep(500);

		driver.findElement(By.xpath("//span[text()='Samsung']")).click();
		
		logger.log(Status.PASS, " Scrolled down to the brands and samsung brand was successsfully");
		System.out.println("Scrolled down to the brands and samsung brand was successsfully");

//Step6 : Sort the Samsung results with price High to Low.

		m.getFeatured().click();
		
		m.getprice_highlow().click();
		
		logger.log(Status.PASS, " Successfully selected the sort option high to low");
		System.out.println("Successfully selected the sort option high to low");

		Thread.sleep(2000);

//	Step7 :Click on the second highest priced item (whatever that maybe at the time of automating).

		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement Samasung_product = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("(//div[@class='a-section aok-relative s-image-square-aspect'])[2]")));

		Samasung_product.click();
		
		logger.log(Status.PASS, " Successfully selected the second highest priced item");
		System.out.println("Successfully selected the second highest priced item");
		
		/*
		 * WebElement product = driver.findElement(By.
		 * xpath("//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-4'])[2]"
		 * ));
		 * 
		 * product.click();
		 */

//	Step8 :	Switch the Window

		String parentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
				
				logger.log(Status.PASS, " Successfully switched to the new window");
				System.out.println("Successfully switched to the new window");

				Thread.sleep(1000);

				
				
				WebElement aboutthis_item = driver.findElement(By.xpath(" //h1[text()=' About this item ']"));

				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", aboutthis_item);

				Thread.sleep(1000);

//Step9 :Assert that “About this item” section is present and log this section text to console/report.		  

				System.out.println("Sucessfully printed" +    aboutthis_item.getText());
				
				

				driver.close(); // closing child window
				driver.switchTo().window(parentWindow); // cntrl to parent window
			}
		}

		System.out.println("Amazon assignment has bee completed");
	}

	@AfterMethod()
	public void screenShot(ITestResult result) throws Throwable {

		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
			logger.fail(result.getThrowable());
			try {

				TakesScreenshot screenshot = (TakesScreenshot) driver;
				File src = screenshot.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(src, new File("./Report/" + result.getName() + ".png"));
				System.out.println("Successfully captured a screenshot");
				// logger.log(Status.FAIL, result.getThrowable());
				logger.log(Status.FAIL,
						"Snapshot below: " + logger.addScreenCaptureFromPath(result.getName() + ".png"));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));

		} else {

			// onFinish(context);
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
			logger.skip(result.getThrowable());
			report.removeTest(logger);
		}
		Thread.sleep(3000);
		if (result.getStatus() == ITestResult.FAILURE) {
			Thread.sleep(3000);
			// BaseClass.mail_report();
		}

	}

	@AfterTest
	private void quitbrowser() {
		report.flush();

		// driver.quit();
	}

}

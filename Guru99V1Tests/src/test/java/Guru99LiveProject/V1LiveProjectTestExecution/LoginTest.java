package Guru99LiveProject.V1LiveProjectTestExecution;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class LoginTest {
	
	private static ExtentReports report;
	private WebDriver webDriver;
    private JavascriptExecutor js;
    
    @BeforeClass
    public static void init(){
    	report = new ExtentReports();
        String fileName = "MyReport" + ".html";
        String filePath = System.getProperty("user.dir") + File.separatorChar + fileName;
        report.attachReporter(new ExtentHtmlReporter(filePath));
    }
    
    @Before
    public void setup(){
    	webDriver = new ChromeDriver();
    	webDriver.manage().window().maximize();
    }
    
    @After
    public void tearDown(){
    	webDriver.quit();
    }
    
    @AfterClass
    public static void destroy(){
    	report.flush();
    }
    
    @Test
    public void Guru99V1Login(){
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Alex\\workspace\\V1LiveProjectTestExecution\\chromerdriver.exe");
    	
    	ExtentTest loginTest = report.createTest("Guru99 Live Project V1 Login Test");
    	loginTest.log(Status.INFO, "This is a test for the Login test case outlined in the Guru99 Test Cases Document. This will test that both fields display an error when empty and will also test to ensure that the system does not allow you to login with incorrect details.");
    	String url = "http://demo.guru99.com/V1/";
    	
    	loginTest.log(Status.INFO, "Navigating to webpage: "+ url + ".");
    	webDriver.navigate().to(url);
    	
    	LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
    	loginTest.log(Status.INFO, "Selecting each field individually and entering no text to test if error messages become visible.");
    	loginTest.log(Status.DEBUG, "Calling the clickUser and clickPass methods from the LoginPage class. This selects each field, but enters no text. This should prompt the error messages to show.");
    	loginPage.clickUser();
    	loginPage.clickPass();
    	
    	if(loginPage.checkUserError()){
    		loginTest.log(Status.PASS, "Error shown when user field is blank.");
    	} else {
    		loginTest.log(Status.FAIL, "Error not showing when user field is blank.");
    	}
    	
    	if(loginPage.checkPassError()){
    		loginTest.log(Status.PASS, "Error shown when password field is blank.");
    	} else {
    		loginTest.log(Status.FAIL, "Error not showing when password field is blank.");
    	}
    	
    	loginTest.log(Status.INFO, "Entering an invalid username and password, then clicking login in order to test that the system does not allow you to login with invalid credentials.");
    	loginTest.log(Status.DEBUG, "Entering the invalid username \"user123\" and the invalid password \"pass123\" through the use of the enterUser and enterPass methods found within the LoginPage class.");  	
    	String invalidUser = "user123";
    	String invalidPass = "pass123";
    	
    	loginPage.enterUser(invalidUser);
    	loginPage.enterPass(invalidPass);
    	loginPage.clickLogin();
    	
    	String currentUrl = webDriver.getCurrentUrl();
    	
    	if(!currentUrl.equals(url)){
    		loginTest.log(Status.FAIL, "Login with invalid credentials did not return you to the login page as was intended.");
    	} else {
    		loginTest.log(Status.PASS, "Login failed and user was returned to the login page.");
    	}
    	
    	loginTest.log(Status.INFO, "Entering valid login credentials to access the system as a Manager.");
    	loginTest.log(Status.DEBUG, "Entering valid credentials provided by Guru99 using the enterUser and enterPass methods in the LoginPage class");
    	String validUser = "mngr113644";
    	String validPass = "mytAzAr";
    	
    	loginPage.enterUser(validUser);
    	loginPage.enterPass(validPass);
    	loginPage.clickLogin();
    	currentUrl = webDriver.getCurrentUrl();
    	
    	if(currentUrl.equals(url)){
    		loginTest.log(Status.FAIL, "Login with valid credentials was unsuccessful");
    	} else {
    		loginTest.log(Status.PASS, "Login Successful. Test Complete");
    	}
    }
	
}

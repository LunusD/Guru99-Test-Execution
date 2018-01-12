package Guru99LiveProject.V1LiveProjectTestExecution;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	@FindBy(name = "uid")
	WebElement usernameField;
	
	@FindBy(name = "password")
	WebElement passwordField;
	
	@FindBy(name = "btnLogin")
	WebElement loginButton;
	
	@FindBy(id = "message23")
	WebElement userError;
	
	@FindBy(id = "message18")
	WebElement passError;
	
	public void clickLogin(){
		loginButton.click();
	}
	
	public void clickUser(){
		usernameField.click();
	}
	
	public void clickPass(){
		passwordField.click();
	}
	
	public void enterUser(String username){
		usernameField.sendKeys(username);
	}
	
	public void enterPass(String password){
		passwordField.sendKeys(password);
	}
	
	public Boolean checkUserError(){
		if(userError.getAttribute("style") == "visibility: visible;"){
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean checkPassError(){
		if(passError.getAttribute("style") == "visibility: visible;"){
			return true;
		} else {
			return false;
		}
	}
	
}

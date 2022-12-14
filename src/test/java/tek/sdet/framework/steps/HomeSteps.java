package tek.sdet.framework.steps;

import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tek.sdet.framework.pages.POMFactory;
import tek.sdet.framework.utilities.CommonUtility;


public class HomeSteps extends CommonUtility{
	// all your step definitions classes will extends 
	// CommonUtility class
	// we need to create object of POMFactory class
	// PomFactory instance should be on top of the class 
	POMFactory factory = new POMFactory();
	
	@Given("User is on retail website")
	public void userIsOnRetailWebsite() {
	String expectedTitle = "React App";
	String actualTitle = getTitle();// wrote this one in CommonUtility
	Assert.assertEquals(expectedTitle, actualTitle);
	logger.info(actualTitle +" is equal to "+ expectedTitle);
	}
	
	@Then("User verify retail page logo is present")
	public void userVerifyRetailPageLogoIsPresent() {
	Assert.assertTrue(isElementDisplayed(factory.homePage().logo));
	logger.info("logo is present");
		
	}
	
	@When("User change the category to {string}")
	public void userChangeTheCategoryTo(String value) {
		selectByVisibleText(factory.homePage().allDepartments,value);
		logger.info(value + " was selected from the drop down");
	    
	}
	@When("User search for an item {string}")
	public void userSearchForAnItem(String value) {
		sendText(factory.homePage().searchInputField,value);
		logger.info("user entered "+value);
	  
	}
	@When("User click on Search icon")
	public void userClickOnSearchIcon() {
		click(factory.homePage().searchButton);
		logger.info("user clicked on search button");
	}
	@Then("Item should be present")
	public void itemShouldBePresent() {
		Assert.assertTrue(isElementDisplayed(factory.homePage().playStationItem));
		logger.info("item is present");
	    
	}

	@When("User click on All section")
	public void userClickOnAllSection() {
	click(factory.homePage().allElement);
	logger.info("user clicked on All element");
	}

	@Then("User verifies {string} is present")
	public void user_verifies_is_present(String value) {
	String expectedValue = value;
	String actualValue = getElementText(factory.homePage().shopByDepartment);
	Assert.assertEquals(expectedValue, actualValue);
	logger.info(expectedValue + " is present");
	}
	
	
	@And("User verifies cart icon is present")
	public void userVerifiesCartIconIsPresent() {
		slowDown();
		HighlightElement(factory.homePage().cart);
		slowDown();
		Assert.assertTrue(isElementDisplayed(factory.homePage().cart));
		logger.info("cart icon is present");
		scrollPageDownWithJS();
		slowDown();
	}
	
	@And("User click on Create New Account button")
    public void userClickOnCreateNewAccountButton() {
        click(factory.signInPage().newAccountButton);
        logger.info("user clicked on Create New Account button");
    }
    @And("User fill the signUp information with below data")
    public void userFillTheSignUpInformationWithBelowData(DataTable data) {
        List<Map<String, String>> signUpData = data.asMaps(String.class,String.class);
        sendText(factory.signInPage().nameInput,signUpData.get(0).get("name"));
        sendText(factory.signInPage().emailInput,signUpData.get(0).get("email"));
        sendText(factory.signInPage().passwordInput,signUpData.get(0).get("password"));
        sendText(factory.signInPage().confirmPasswordInput,signUpData.get(0).get("confirmPassword"));
        logger.info("user entered required information into sign up form");
    }
    @And("User click on SignUp button")
    public void userClickOnSignUpButton() {
        click(factory.signInPage().signUpButton);
        logger.info("user clicked on Sign up Button");
    }
    @Then("User should be logged into account page")
    public void userShouldBeLoggedIntoAccountPage() {
        Assert.assertTrue(isElementDisplayed(factory.signInPage().profileImage));
        Assert.assertTrue(isElementDisplayed(factory.homePage().account));
        logger.info("Account was created");
    }
}



package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.HomePage;

public class CreateRecordSteps {
	
	TestContext testContext;
	HomePage homePage;
	
	public CreateRecordSteps(TestContext context) {
		testContext = context;
		homePage = testContext.getPageObjectManager().getHomePage();
	}
	
	@Given("^I am on homepage$")
	public void i_am_on_homepage() throws Throwable {
		homePage.validateHomePage();
	}

	@When("^I click on Add a new computer button$")
	public void i_click_on_Add_a_new_computer_button() throws Throwable {
		homePage.addNewComputerButton();
	}

	@Then("^I should be on computer detail page$")
	public void i_should_be_on_computer_detail_page() throws Throwable {
		homePage.validateNewRecordPage();
	}

	@When("^I enter new computer detail$")
	public void i_enter_new_computer_detail() throws Throwable {
		homePage.enterComputerDetail();
	}

	@When("^I click on create this computer button$")
	public void i_click_on_create_this_computer_button() throws Throwable {
		homePage.addNewRecord();
	}
	
	@Then("^I should get \"([^\"]*)\" message$")
	public void i_should_get_message(String alertType) throws Throwable {
		homePage.validateAlertMessage(alertType);
	}
	
	@When("^I enter new computer name in search field$")
	public void i_enter_new_computer_name_in_search_field() throws Throwable {
		homePage.searchRecord();
	}

	@Then("^I should get details in table$")
	public void i_should_get_details_in_table() throws Throwable {
		homePage.validateSearchRecord();
	}

	@When("^I open new record$")
	public void i_open_new_record() throws Throwable {
		homePage.openNewRecord();
	}

	@Then("^I should be able to update record$")
	public void i_should_be_able_to_update_record() throws Throwable {
		i_enter_new_computer_detail();
		i_click_on_create_this_computer_button();
	}
	
	@When("^I delete new record$")
	public void i_delete_new_record() throws Throwable {
		homePage.deleteRecord();
	}


	@When("^I enter deleted computer name in search field$")
	public void i_enter_deleted_computer_name_in_search_field() throws Throwable {
		i_enter_new_computer_name_in_search_field();
	}

	@Then("^I should get no record to display$")
	public void i_should_get_no_record_to_display() throws Throwable {
		homePage.validateSearchOfDeleteRecord();
	}
}

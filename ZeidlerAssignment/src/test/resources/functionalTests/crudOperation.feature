#Author: praveen9319@gmail.com
#Summary : Zeidler group Automation assignment
Feature: User should able to perform CRUD operation i.e
	1. Add new record
	2. Search record
	3. Update record
	4. Delete record
	

 @regression
	Scenario Outline: Add new record
		Given I am on homepage
		When I click on Add a new computer button
		Then I should be on computer detail page
		When I enter new computer detail
		And I click on create this computer button
		Then I should get "<alert>" message
		Examples: 
      	|alert 			|
	  		|new record	|
		
	@regression
	Scenario: Search newly added record
		Given I am on homepage
		When I enter new computer name in search field
		Then I should get details in table

		
	@regression
	Scenario Outline: Update newly added record
		Given I am on homepage
		When I enter new computer name in search field
		And I open new record
		Then I should be able to update record
		And I should get "<alert>" message
		When I enter new computer name in search field
		Then I should get details in table
		Examples: 
      	|alert 					|
	  		|update record	|
		
	@regression
	Scenario Outline: Delete newly added record
		Given I am on homepage
		When I enter new computer name in search field
		And I open new record
		And I delete new record
		Then I should get "<alert>" message
		When I enter deleted computer name in search field
		Then I should get no record to display	
		Examples: 
      	|alert 					|
	  		|delete record	|
		
		
		



 		
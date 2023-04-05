
Feature: Login verification with saucedemo 


Scenario: 1 Successful Login
    Given I am on the Sauce Demo Login Page
 		When I fill the account information for account User into the Username field and the Password field
			| username | password |
			|standard_user|secret_sauce|
 		And I click the Login button
 		Then I am redirected to the Sauce Demo Main Page
 		And I verify the App Logo exists
 
 Scenario: 2 Failed Login
 		Given I am on the Sauce Demo Login Page
 		When I fill the account information for account User into the Username field and the Password field
			| username | password |
			|locked_out_user|secret_sauce|
 		And I click the Login button
 		Then I verify the Error Message contains the text

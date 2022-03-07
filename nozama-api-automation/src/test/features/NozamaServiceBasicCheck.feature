#Author: balamurugan.th@gmail.com
#Keywords Summary : Nozama, API Service Automation
Feature: Nozama Service Basic Check

  Scenario: Checking the API Gateway is Up
    Given Nozama API Gateway URL "/api/status"
    When Calling the Nozama API Gateway
    Then I verify the Nozama API Gateway is up

  Scenario: Checking the User service status is up via API Gateway
    Given Nozama User Service with API URL "/user/status"
    When Calling the Nozama User Service Via API Gateway
    Then I verify the Nozama User Service Status is up

  Scenario: Verify Signup User service adds user via API Gateway
    Given Nozama User Signup Service with API URL "/user/signup"
    When Calling the Nozama User Signup Service Via API Gateway Using "userservice\userservice-signup.json"
    Then I verify the Signup Nozama User Service is created

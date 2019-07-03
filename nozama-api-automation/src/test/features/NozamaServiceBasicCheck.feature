#Author: balamurugan.th@gmail.com
#Keywords Summary : Nozama, API Service Automation
Feature: Nozama Service Basic Check

  Scenario: Checking the API Gateway is Up
    Given Nozama API Gateway URL "http://localhost:8080/api/test"
    When Calling the Nozama API Gateway
    Then I verify the Nozama API Gateway is up

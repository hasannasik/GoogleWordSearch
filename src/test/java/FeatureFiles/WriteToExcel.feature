Feature: Searching for IBTECH in Google and printing the results in excel

  @Regression
  Scenario:
    Given Search for IBTECH on Google
    And   First page results are listed and The results are exported to excel
    And   Second page results are found and exported to excel
    And   The results on the last page are listed and exported to excel


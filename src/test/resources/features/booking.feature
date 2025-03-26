Feature: Search on Booking.com

  @smoke
  Scenario: Search by city
    Given User is looking for hotels in 'United States' city
    When User does search
    Then Hotel 'Hilton San Francisco Union Square' should be on the search results page
    Then Hotel 'Hilton San Francisco Union Square' should has rate is '7.6'

  @smoke
  Scenario: DataTable
    Given User provide information:
      | name | email | phone |
      | tania | tania@mail.ru    | 2233433  |
      | kirill | lil@mail.ru  | 3344455  |
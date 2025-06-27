Feature: Version API Tests

Background:
  * url baseUrl
  * def expectedFields = ['version', 'environment', 'commit_hash', 'commit_time', 'branch', 'build_time', 'application_name']

  Scenario: Get version information
    Given path '/api/v1/version'
    When method GET
    Then status 200
    And match response == { version: '#string', environment: '#string', commit_hash: '#string', commit_time: '#string', branch: '#string', build_time: '#string', application_name: '#string' }

Scenario: Version should contain environment information
  Given path '/api/v1/version'
  When method GET
  Then status 200
  And match response.environment == '#string'
  And match response.environment != ''

Scenario: Health check endpoint
  Given path '/api/v1/health'
  When method GET
  Then status 200
  And match response == 'OK'

Scenario: Version response should have correct structure
  Given path '/api/v1/version'
  When method GET
  Then status 200
  And match response.version == '#string'
  And match response.application_name == '#string'
  And response.commit_hash.length > 0
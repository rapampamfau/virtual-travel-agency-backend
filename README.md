FRONTEND FOR THIS BACKEND: https://github.com/rapampamfau/virtual-travel-agency-frontend

Requirements before running - Java 11, Gradle, MySQL.

For the application to work properly, a user must be created in the MySQL database.
The database username and password should be the same as in the application.properties file
in item #DATABASE MYSQL CONFIG.

Then we need to set the environment variables for our system properly so that it can
use API keys:

- SkyscannerApiKey = a05f1f1db0msha331470e5a81a12p134765jsn68e497afa04b

- OpenWeatherApiKey = 5732fb42e3e41e16981b2c741713c068

In the application.properties file section, you can set the #ADMINISTRATION and #EMAIL CONFIGURATION fields
with your login credentials, it is best to do this by setting environment variables.
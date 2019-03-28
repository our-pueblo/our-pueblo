# Our Pueblo

Our Pueblo is an application designed to facilitate the large-scale volunteer translation of the pages on the City of San Antonio website. Employees are treated as administrators, and granted higher-level privileges than the common user or volunteer. This application utilizes an API called JSOUP for web scraping as well as the Google Cloud Translation API to provide a baseline translation for the user’s reference.
## Getting Started

Clone the project from Github

Open example.properties and create an application.properties with the same parameters changing placeholder values to match ones for your system 

Run the site locally

Create a user with the username of admin (CASE SENSITIVE), it will automatically be designated the super admin

While logged in as admin go to /seed and run the seeder when prompted (Only do this once, unless you drop the database)
### Prerequisites

Properly set up Tomcat configuration

Google Cloud API key

## Built With

* [Spring-Boot](https://spring.io/projects/spring-boot)
* [Thymeleaf](https://www.thymeleaf.org/)
* [JSOUP](https://jsoup.org/)
* [Google Translate API](https://cloud.google.com/translate/docs/)
* [MySQL](https://www.mysql.com/)
* [Maven](https://maven.apache.org/)

## Authors

* **Michael Carranza** - [Linkedin](https://www.linkedin.com/in/michaelcarranza/)

* **Austin Garza** - [Linkedin](https://www.linkedin.com/in/austin-garza/)

* **Ryan Glazer** - [Personal Site](https://ryan-glazer.com)


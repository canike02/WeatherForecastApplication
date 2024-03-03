This is a simple Spring Boot application to fetch Weather Forecast details using zipCode as input, for the RestAPI to fetch data.
The data that is fetched is cached for a 30 minutes before invalidating. (The API used can be accessed for free for only 1000 hits/day).

To try out the application, you can clone the repository and start running in any local IDE supporting JDK 17+ and is able to run a Spring Boot application. 
The application runs on default port 8080 and you may use http://localhost:8080/weather/swagger-ui/index.html, to try out the sole Rest API available.

To build it locally, you can generate the executable using  mave commands - mvn clean install.

### Welcome to URL Shortener!

#### Problem Statement
This is a url shortener service where you can get short url like http://shorturl.com/db63e1 for the given long url like https://codesubmit.io/library/react
Once you receive the short url, you can use it to decode and fetch the corresponding long url.

#### Considerations
1. There are 2 HTTP REST API end-points - /url/encode (to encode long url) and /url/decode (to decode short url).
2. There is only 1 user accessing at a time.
3. The short url length will be 26 characters.
4. There is not limit on number or urls encoded.
5. There is no constraint on the user who is using the service.
6. All the data will be kept in in-memory and there is not persistent storage.
7. The URLs will remain in the memory as long the server will run. There is no expiry to the data.

#### Design
1. There is 1 controller,URLShortenerController, which has REST API endpoints.
2. REST API /url/encode follows POST HTTP method. It accepts a URLShortenerRequest which contains the long url and can be easily extended to contain user information and expected expiry date.
The input and output are JSON formats.
3. REST API /url/decode follows GET HTTP method. It take url as a RequestParam which is used as input to fetch data from in-memory database.
The output is a JSON format.
4. Base62 is used to create hashcode for short url. The length of hashcode will be 6 digits and will contain characters from a-z, A-Z, 0-9 which gives the capability to create more than 56.8 billion (62 pow 6) unique urls.
5. Code is organized in 3 layers - Controller, Service and DAO.
6. All the external models exposed through REST API end-points goes in Contract folder.
7. Internal models which are used to carry out internal operations goes in Model folder.
8. All the validations happen through Validator folder.
9. Other common stuff like ErrorCodes and Constants goes in Common folder.

#### CodeFlow
Application instantiates through Application.java and the URLShortenerController takes the input requests.
These requests are passed on to the service layer which validate the requests and if required contact with DAO layer to fetch data from in-memory DB.
If there is any exception/error, they are accordingly passed on to the client in Error node of URLShortenerResponse.

#### Instructions
1. Maven version - 3.6.3
2. Java version - 1.8 or above
3. Framework - spring-boot 2.3.4
3. Compile and build using **mvn clean install** on java-url-shortener-pkollz folder.
4. Run using **mvn spring-boot:run** on java-url-shortener-pkollz folder. The application will start on http://localhost:8080/. This url will return output "URL Shortener is active".
5. Execute test cases using **mvn test** on java-url-shortener-pkollz folder.
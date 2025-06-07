package tests.api;

        import com.fasterxml.jackson.databind.ObjectMapper;
        import io.restassured.response.Response;
        import models.User;
        import org.testng.Assert;
        import org.testng.annotations.DataProvider;
        import org.testng.annotations.Test;
        import static io.restassured.RestAssured.given;
        import utils.RequestBuilder;
        import utils.ResponseValidator;

        public class CreateUserAccountAPITests {

            @DataProvider(name = "validUserData")
            public Object[][] provideValidUserData() {
                return new Object[][] {
                    {"testuser1", "password123", "test1@example.com", "John", "Doe", "+12345678901", "123 Main St", "Cairo", "Egypt", "12345"},
                    {"testuser2", "password456", "test2@example.com", "Jane", "Smith", "+12345678902", "456 Oak St", "Alexandria", "Egypt", "12346"},
                    {"testuser3", "password789", "test3@example.com", "Robert", "Brown", "+12345678903", "789 Pine St", "Giza", "Egypt", "12347"}
                };
            }

            @Test(dataProvider = "validUserData")
            public void testCreateUser(String username, String password, String email, String firstName,
                                     String lastName, String phoneNumber, String address, String city,
                                     String state, String zipCode) {
                // 1. Create user object with valid values
                User newUser = new User(username, password, email, firstName, lastName,
                                      phoneNumber, address, city, state, zipCode);

                // 2. Validate user data using User model validation methods
                Assert.assertTrue(newUser.isValid(), "User data should be valid");
                Assert.assertTrue(newUser.isValidEmail(), "Email format should be valid");
                Assert.assertTrue(newUser.isValidPhoneNumber(), "Phone number format should be valid");

                // 3. Convert User object to JSON using ObjectMapper
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonBody = "";
                try {
                    jsonBody = objectMapper.writeValueAsString(newUser);
                } catch (Exception e) {
                    e.printStackTrace();
                    Assert.fail("Error occurred while converting User object to JSON");
                }

                // 4. Send POST request to create the user
                String endpoint = "/users/create";
                Response response = given()
                        .spec(RequestBuilder.getRequestSpecification())
                        .body(jsonBody)
                        .when()
                        .post(endpoint)
                        .then()
                        .extract().response();

                // 5. Verify response status code is 201 (Created)
                ResponseValidator.validateStatusCode(response, 201);

                // 6. Read response and convert it to User object for validation
                User createdUser = null;
                try {
                    createdUser = objectMapper.readValue(response.getBody().asString(), User.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    Assert.fail("Error occurred while converting response JSON to User object");
                }

                // 7. Verify returned user data matches the input
                Assert.assertEquals(createdUser.getEmail(), newUser.getEmail(), "Email does not match");
                Assert.assertEquals(createdUser.getUsername(), newUser.getUsername(), "Username does not match");
                Assert.assertEquals(createdUser.getFirstName(), newUser.getFirstName(), "First name does not match");
                Assert.assertEquals(createdUser.getLastName(), newUser.getLastName(), "Last name does not match");
                Assert.assertEquals(createdUser.getPhoneNumber(), newUser.getPhoneNumber(), "Phone number does not match");
                Assert.assertEquals(createdUser.getAddress(), newUser.getAddress(), "Address does not match");
                Assert.assertEquals(createdUser.getCity(), newUser.getCity(), "City does not match");
                Assert.assertEquals(createdUser.getState(), newUser.getState(), "State does not match");
                Assert.assertEquals(createdUser.getZipCode(), newUser.getZipCode(), "Zip code does not match");
            }
        }
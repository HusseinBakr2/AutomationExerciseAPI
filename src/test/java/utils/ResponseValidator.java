package utils;

    import io.restassured.path.json.JsonPath;
    import io.restassured.response.Response;
    import static org.hamcrest.MatcherAssert.assertThat;
    import static org.hamcrest.Matchers.*;

    public class ResponseValidator {
        public static void validateStatusCode(Response response, int expectedStatusCode) {
            assertThat(response.getStatusCode(), is(expectedStatusCode));
        }

        public static void validateResponseTime(Response response, long maxResponseTimeMs) {
            long responseTime = response.getTime();
            assertThat("Response time", responseTime, lessThan(maxResponseTimeMs));
        }

        public static void validateProductDetails(String responseBody, int productIndex) {
            JsonPath jsonPath = new JsonPath(responseBody);

            assertThat(jsonPath.getInt("responseCode"), is(200));
            assertThat(jsonPath.getInt("products[" + productIndex + "].id"), notNullValue());
            assertThat(jsonPath.getString("products[" + productIndex + "].name"), notNullValue());
            assertThat(jsonPath.getString("products[" + productIndex + "].price"), startsWith("Rs. "));
            assertThat(jsonPath.getString("products[" + productIndex + "].brand"), notNullValue());
            assertThat(jsonPath.getString("products[" + productIndex + "].category.category"), notNullValue());
            assertThat(jsonPath.getString("products[" + productIndex + "].category.usertype.usertype"), notNullValue());
        }

        public static void validateErrorResponse(Response response, int expectedErrorCode) {
            assertThat(response.getStatusCode(), is(expectedErrorCode));
        }
    }
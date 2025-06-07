package tests.api;

import org.testng.annotations.Test;
import tests.BaseTest;


import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AllBrandsListAPITests extends BaseTest {

    //============================Valid test cases========================================
    //here we Test to check if the status code of the response is 200
    @Test
    public void testAllBrandsListStatusCodeReturns200() {
        given()
                    .spec(requestSpec)
         .when()
                    .get("/brandsList")
         .then()
                    .log().all()
                    .assertThat().statusCode(200);
    }

    //here we test to check if the response body contains the expected keys and values
    @Test
    public void testAllBrandsListResponseBody() {
        given()
                .spec(requestSpec)
         .when()
                    .get("/brandsList")
         .then()
                    .log().all()
                    .assertThat().body("responseCode", is(equalTo(200)))
                    .assertThat().body("brands[0]",hasKey("id"))
                    .assertThat().body("brands[0]",hasKey("brand"))
                    .assertThat().body("brands",not(empty()));

    }
    //here we test to check if the response body contains the expected value
    @Test
    public void testAllBrandsListResponseBodyContainsExpectedValue() {

        given()
            .spec(requestSpec)
            .log().all()
         .when()
                 .get("/brandsList")
         .then()
                .log().all()
                .assertThat()
                .body("brands.[0]", hasItems("Polo"));
    }

    //here we test to check if the response time is less than 500ms
    @Test
    public void testAllBrandsListResponseTimeLess500ms() {
        // Perform warm-up requests before the actual test
        for (int i = 1; i <= 4; i++) {
            long warmupTime = given()
                    .spec(requestSpec)
              .when()
                    .get("/brandsList")
              .then()
                    .extract().time();

            System.out.println("Warm-up Request #" + i + " Response Time: " + warmupTime + " ms");
        }


        long responseTime = given()
                .spec(requestSpec)
             .when()
                .get("/brandsList")
             .then()
                .extract().time();

        System.out.println("Measured Response Time: " + responseTime + " ms");
        assertThat("Response time after warm-up", responseTime, lessThan(500L));
    }


    //================================Invalid test cases========================================

    //here we test to check if the status code of the response is 404
    @Test
    public void testAllBrandsListInvalidEndpoint() {
        given()
                .spec(requestSpec)
         .when()
                .get("/brandsList22")
         .then()
                .log().all()
                .assertThat().statusCode(404);
    }
    //here we tes invalid Request
    @Test
    public void testAllBrandsListInvalidRequest() {
        given()
                .spec(requestSpec)
         .when()
                .post("/brandsList")
         .then()
                .log().all();

    }

}




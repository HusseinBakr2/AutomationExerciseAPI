package tests.api;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import tests.BaseTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AllProductsListAPITests extends BaseTest {
    //============================Valid test cases========================================
    //here we Test to check if the status code of the response is 200
     @Test
    public void testAllProductsListStatusCodeReturns200() {
         given()
                     .spec(requestSpec)
          .when()
                     .get("/productsList")
          .then()
                     .log().all()
                     .assertThat().statusCode(200);
     }

    //here we test to check if the response body contains the expected keys and values
    @Test
    public void testAllProductsListResponseBody() {
        String responseBody = given()
                .spec(requestSpec)
            .when()
                .get("/productsList")
            .then()
                .log().all()
                .extract().response().body().htmlPath().getString("body");


        JsonPath jsonPath = new JsonPath(responseBody);

        assertThat(jsonPath.getInt("responseCode"), is(200));
        assertThat(jsonPath.getInt("products[0].id"), is(1));
        assertThat(jsonPath.getString("products[0].name"), is("Blue Top"));
        assertThat(jsonPath.getString("products[0].price"), is("Rs. 500"));
        assertThat(jsonPath.getString("products[0].brand"), is("Polo"));
        assertThat(jsonPath.getString("products[0].category.usertype.usertype"), is("Women"));
        assertThat(jsonPath.getString("products[0].category.category"), is("Tops"));

    }

    //here we test the response time is less than 500ms
    @Test
    public void testAllProductsListResponseTimeLess500ms() {
        // Perform warm-up requests before the actual test
        for (int i = 1; i <= 6; i++) {
            long warmupTime = given()
                    .spec(requestSpec)
                .when()
                    .get("/productsList")
                .then()
                    .extract().time();

            System.out.println("Warm-up Request #" + i + " Response Time: " + warmupTime + " ms");
        }


        long responseTime = given()
                .spec(requestSpec)
            .when()
                .get("/productsList")
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
                .get("/productsList22")
            .then()
                .log().all()
                .assertThat().statusCode(404);
    }
    //here we tes invalid Request
    @Test
    public void testAllBrandsListInvalidRequest() {
      // String responseBody1=
          given()
                .spec(requestSpec)
            .when()
                .delete("/productsList")
            .then()
                .assertThat().statusCode(405);
//                .extract().response().body().htmlPath().getString("body");
//
//        JsonPath jsonPath = new JsonPath(responseBody1);
//        assertThat(jsonPath.getInt("responseCode"), is(405));

    }


}

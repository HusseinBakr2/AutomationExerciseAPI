package utils;

    import io.restassured.builder.RequestSpecBuilder;
    import io.restassured.specification.RequestSpecification;
    import config.ConfigManager;

    public class RequestBuilder {
        public static RequestSpecification getRequestSpecification() {
            return new RequestSpecBuilder()
                    .setBaseUri(ConfigManager.getInstance().getProperty("https://automationexercise.com"))
                    .addHeader("Content-Type", "application/json")
                    .build();
        }
    }
package example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIObject {
    @Test
        public void postObject(){
            String object = "{\"name\": \"testDiego\", \"data\": { \"Generation\": \"4th\", \"Price\": \"519.99\", \"Capacity\": \"256 GB\" }}";
            Response postResponse = RestAssured
                    .given()
                    .log().all()
                    .header("Content-Type", "application/json")
                    .header("User-Agent", "Chrome/58.0.3029.110")
                    .body(object)
                    .post("https://api.restful-api.dev/objects");
            postResponse.prettyPrint();            
            postResponse.then().statusCode(200);
            String objectId = postResponse.jsonPath().getString("id");
            Assertions.assertNotNull(objectId, "Object ID should not be null");
            String endpoint = String.format("https://api.restful-api.dev/objects/%s", objectId);    
            Response getResponse = RestAssured
                    .given()
                    .log().all()
                    .get(endpoint);
            getResponse.prettyPrint();
            getResponse.then().statusCode(200);
            String actualName = getResponse.jsonPath().getString("name");
            Assertions.assertEquals("testDiego", actualName, "Expected name to be 'testDiego' but was '" + actualName + "'");
        }
}

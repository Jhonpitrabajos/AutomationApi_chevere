package example;

import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SimpleApiTest {
    
    @Test
    public void testApiKeyWorks() {
        Response response = RestAssured
                .given()
                .log().all()
                .header("x-api-key", "reqres-free-v1")
                .get("https://reqres.in/api/users?page=1");
        
        response.prettyPrint();
        response.then().statusCode(200);
        
        System.out.println("✅ API Key works!");
    }
    
    @Test
    public void testApiKeyWorksUpperCase() {
        Response response = RestAssured
                .given()
                .log().all()
                .header("X-API-Key", "reqres-free-v1")
                .get("https://reqres.in/api/users?page=1");
        
        response.prettyPrint();
        response.then().statusCode(200);
        
        System.out.println("✅ API Key works with uppercase!");
    }
}

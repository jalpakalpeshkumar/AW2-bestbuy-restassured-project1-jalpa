package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StoresAssertionTest {


    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

//1. Verify the if the total is equal to 1561
    @Test
    public void test1(){
        response.body("total",equalTo(1568));
    }
//2. verify the if the stores of limit is equal to 10
    @Test
    public void test2(){
        response.body("limit",equalTo(10));
    }

    //3. Check the single ‘Name’ in the Array list (Inver Grove Heights)
    @Test
    public  void test3(){
        response.body("data.name",hasItem("Inver Grove Heights"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
    @Test
    public void test004(){
        response.body("data.name", hasItems("Roseville", "Burnsville", "Maplewood"));
    }

    //5. Verify the storied=7 inside storeservices of the third store of second services
    @Test
    public void test5(){
        response.body("data[2].services[1].storeservices.storeId",equalTo(7));
    }

    //6. Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
    @Test
    public void test6(){
        response.body("data[2].services[1].storvices",hasKey("createsAt"));
    }

    //7. Verify the state = MN of forth store
    @Test
    public void test7(){
        response.body("data[3].state",equalTo("MN"));
    }

    //8. Verify the store name = Rochester of 9th store
    @Test
    public void test8(){
        response.body("data[8].name",equalTo("Rochester"));
    }

    //9. Verify the storeId = 11 for the 6th store
    @Test
    public void test9(){
        response.body("data[5].services[0].storeId",equalTo(null));
    }

    //10. Verify the serviceId = 4 for the 7th store of forth service
    @Test
    public void test10(){
        response.body("data[6].services[0].storeId",equalTo(11));
    }
}

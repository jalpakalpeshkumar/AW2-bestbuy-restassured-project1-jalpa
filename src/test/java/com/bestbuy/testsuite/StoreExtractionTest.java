package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class StoreExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = (ValidatableResponse) given()
                .when()
                .get("/stores")
                .then().statusCode(200);

    }

    @Test
    public void extractStoreDetails() {


        // 1. Extract the limit
        int limit = response.extract().path("limit");
        System.out.println("Limit: " + limit);

        // 2. Extract the total
        int total = response.extract().path("total");
        System.out.println("Total: " + total);

        // 3. Extract the name of the 5th store
        String fifthStoreName = response.extract().path("stores[4].name");
        System.out.println("Name of 5th Store: " + fifthStoreName);

        // 4. Extract the names of all the stores
        List<String> storeNames = response.extract().path("stores.name");
        System.out.println("Store Names: " + storeNames);

        // 5. Extract the storeId of all the stores
        List<Integer> storeIds = response.extract().path("stores.storeId");
        System.out.println("Store IDs: " + storeIds);

        // 6. Print the size of the data list
        int dataSize = response.extract().path("stores.size()");
        System.out.println("Size of Store List: " + dataSize);

        // 7. Get all the value of the store where store name = St Cloud
        List<Object> stCloudStore = response.extract().path("stores.find { it.name == 'St Cloud' }");
        System.out.println("Details of Store 'St Cloud': " + stCloudStore);

        // 8. Get the address of the store where store name = Rochester
        String rochesterAddress = response.extract().path("stores.find { it.name == 'Rochester' }.address");
        System.out.println("Address of Store 'Rochester': " + rochesterAddress);

        // 9. Get all the services of the 8th store
        List<Object> eighthStoreServices = response.extract().path("stores[7].storeServices");
        System.out.println("Services of 8th Store: " + eighthStoreServices);

        // 10. Get store services of the store where service name = Windows Store
        List<Object> windowsStoreServices = response.extract().path("stores.storeServices.findAll { it.name == 'Windows Store' }");
        System.out.println("Services of Store 'Windows Store': " + windowsStoreServices);

        // 11. Get all the storeId of all the stores
        List<Integer> allStoreIds = response.extract().path("stores.storeId");
        System.out.println("All Store IDs: " + allStoreIds);

        // 12. Get id of all the stores
        List<Integer> storeIdsAll = response.extract().path("stores.storeId");
        System.out.println("All Store IDs: " + storeIdsAll);

        // 13. Find the store names where state = ND
        List<String> ndStoreNames = response.extract().path("stores.findAll { it.state == 'ND' }.name");
        System.out.println("Store Names in ND: " + ndStoreNames);

        // 14. Find the Total number of services for the store where store name = Rochester
        int servicesForRochester = response.extract().path("stores.find { it.name == 'Rochester' }.storeServices.size()");
        System.out.println("Total Services for Rochester: " + servicesForRochester);

        // 15. Find the createdAt for all services whose name = 'Windows Store'
        List<String> windowsStoreCreatedAt = response.extract().path("stores.storeServices.findAll { it.name == 'Windows Store' }.createdAt");
        System.out.println("CreatedAt for 'Windows Store' Services: " + windowsStoreCreatedAt);

        // 16. Find the name of all services where store name = 'Fargo'
        List<String> fargoServices = response.extract().path("stores.find { it.name == 'Fargo' }.storeServices.name");
        System.out.println("Services of Fargo Store: " + fargoServices);

        // 17. Find the zip of all the stores
        List<String> storeZips = response.extract().path("stores.zip");
        System.out.println("Store Zips: " + storeZips);

        // 18. Find the zip of store name = Roseville
        String rosevilleZip = response.extract().path("stores.find { it.name == 'Roseville' }.zip");
        System.out.println("Zip of Roseville Store: " + rosevilleZip);

        // 19. Find the store services details of the service name = Magnolia Home Theater
        List<Object> magnoliaServices = response.extract().path("stores.storeServices.findAll { it.name == 'Magnolia Home Theater' }");
        System.out.println("Services of Magnolia Home Theater: " + magnoliaServices);

        // 20. Find the lat of all the stores
        List<Double> storeLats = response.extract().path("stores.lat");
        System.out.println("Latitudes of all Stores: " + storeLats);
    }
}





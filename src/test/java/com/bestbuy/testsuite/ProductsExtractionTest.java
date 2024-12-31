package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);

    }
    @Test
    public void extractProductDetails() {

        // 21. Extract the limit
        int limit = response.extract().path("limit");
        System.out.println("Limit: " + limit);

        // 22. Extract the total
        int total = response.extract().path("total");
        System.out.println("Total: " + total);

        // 23. Extract the name of the 5th product
        String fifthProductName = response.extract().path("products[4].name");
        System.out.println("Name of 5th Product: " + fifthProductName);

        // 24. Extract the names of all the products
        List<String> productNames = response.extract().path("products.name");
        System.out.println("Product Names: " + productNames);

        // 25. Extract the productId of all the products
        List<Integer> productIds = response.extract().path("products.productId");
        System.out.println("Product IDs: " + productIds);

        // 26. Print the size of the data list
        int dataSize = response.extract().path("products.size()");
        System.out.println("Size of Product List: " + dataSize);

        // 27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
        List<Object> energizerMaxBatteries = response.extract().path("products.find { it.name == 'Energizer - MAX Batteries AA (4-Pack)' }");
        System.out.println("Details of 'Energizer - MAX Batteries AA (4-Pack)': " + energizerMaxBatteries);

        // 28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
        String energizerModel = response.extract().path("products.find { it.name == 'Energizer - N Cell E90 Batteries (2-Pack)' }.model");
        System.out.println("Model of 'Energizer - N Cell E90 Batteries (2-Pack)': " + energizerModel);

        // 29. Get all the categories of the 8th product
        List<String> eighthProductCategories = response.extract().path("products[7].categories");
        System.out.println("Categories of 8th Product: " + eighthProductCategories);

        // 30. Get categories of the product where product id = 150115
        List<String> categoriesOfProduct = response.extract().path("products.find { it.productId == 150115 }.categories");
        System.out.println("Categories of Product with ID 150115: " + categoriesOfProduct);

        // 31. Get all the descriptions of all the products
        List<String> productDescriptions = response.extract().path("products.description");
        System.out.println("Product Descriptions: " + productDescriptions);

        // 32. Get id of all the categories of all the products
        List<Integer> categoryIds = response.extract().path("products.categories.id");
        System.out.println("Category IDs for all Products: " + categoryIds);

        // 33. Find the product names where type = HardGood
        List<String> hardGoodProductNames = response.extract().path("products.findAll { it.type == 'HardGood' }.name");
        System.out.println("Product Names of type 'HardGood': " + hardGoodProductNames);

        // 34. Find the total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
        int categoryCountForDuracell = response.extract().path("products.find { it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)' }.categories.size()");
        System.out.println("Number of Categories for 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)': " + categoryCountForDuracell);

        // 35. Find the createdAt for all products whose price < 5.49
        List<String> createdAtForProducts = response.extract().path("products.findAll { it.price < 5.49 }.createdAt");
        System.out.println("CreatedAt for products with price < 5.49: " + createdAtForProducts);

        // 36. Find the name of all categories where product name = 'Energizer - MAX Batteries AA (4-Pack)'
        List<String> categoriesForEnergizer = response.extract().path("products.find { it.name == 'Energizer - MAX Batteries AA (4-Pack)' }.categories.name");
        System.out.println("Categories for 'Energizer - MAX Batteries AA (4-Pack)': " + categoriesForEnergizer);

        // 37. Find the manufacturer of all the products
        List<String> productManufacturers = response.extract().path("products.manufacturer");
        System.out.println("Manufacturers of Products: " + productManufacturers);

        // 38. Find the image of products whose manufacturer is 'Energizer'
        List<String> energizerProductImages = response.extract().path("products.findAll { it.manufacturer == 'Energizer' }.image");
        System.out.println("Images of Energizer Products: " + energizerProductImages);

        // 39. Find the createdAt for all categories of products whose price > 5.99
        List<String> createdAtForExpensiveProducts = response.extract().path("products.findAll { it.price > 5.99 }.createdAt");
        System.out.println("CreatedAt for products with price > 5.99: " + createdAtForExpensiveProducts);

        // 40. Find the URI of all the products
        List<String> productUris = response.extract().path("products.uri");
        System.out.println("URIs of Products: " + productUris);
    }
}

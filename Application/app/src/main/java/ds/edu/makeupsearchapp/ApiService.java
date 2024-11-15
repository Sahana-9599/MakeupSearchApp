/**
 * Author : Sahana Baggaon Gajendra
 * email : sahanabaggaongajendra@gmail.com
 * 
 */



package ds.edu.makeupsearchapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Fetches makeup products based on brand and product type.
 * Sends a GET request to /makeupSearch/api/products.
 */
public interface ApiService {
    @GET("/api/products")
    Call<List<Product>> getProducts(
            @Query("brand") String brand,
            @Query("product_type") String productType
    );
}

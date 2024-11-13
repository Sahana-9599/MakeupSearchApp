/**
 * Author : Sahana Baggaon Gajendra
 * email : sahanabaggaongajendra@gmail.com
 */




package ds.edu.makeupsearchapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This file defines the main activity for the Android application.
 * It serves as the entry point and handles user interactions,
 * such as input for brand and product type, triggering an API call
 * to fetch products, and displaying the results in a RecyclerView.
 */

public class MainActivity extends AppCompatActivity {

    // UI components for user input, button, product list, and loading indicator
    private EditText etBrand, etProductType;
    private Button btnFetch;
    private RecyclerView rvProducts;
    private ProgressBar progressBar;
    private ProductAdapter productAdapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize UI elements
        etBrand = findViewById(R.id.etBrand);
        etProductType = findViewById(R.id.etProductType);
        btnFetch = findViewById(R.id.btnFetch);
        rvProducts = findViewById(R.id.rvProducts);
        progressBar = findViewById(R.id.progressBar);

        rvProducts.setLayoutManager(new LinearLayoutManager(this));

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchProducts();
            }
        });
    }

    /**
     * Function to fetch products from the web service based on user input.
     * If valid inputs (brand or product type) are provided, it makes an HTTP request to the server,
     * processes the response, and updates the UI accordingly.
     */
    private void fetchProducts() {
        String brand = etBrand.getText().toString().trim();
        String productType = etProductType.getText().toString().trim();

        // Showing error if both inputs are empty
        if (brand.isEmpty() && productType.isEmpty()) {
            Toast.makeText(this, "Please enter at least a brand or product type", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bug-free-space-umbrella-wrr6r7ww765jcgw6r-8080.app.github.dev")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        // Call the API with parameters
        Call<List<Product>> call = apiService.getProducts(brand, productType);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    // Populate the RecyclerView with products
                    List<Product> products = response.body();
                    productAdapter = new ProductAdapter(products);
                    rvProducts.setAdapter(productAdapter);
                } else {
                    // Clear the RecyclerView and show a meaningful error message
                    rvProducts.setAdapter(null); // Clear the list
                    Toast.makeText(MainActivity.this, "No products found. Please try a different search.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            // Handle API call failure (network issues)
            public void onFailure(Call<List<Product>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Failed to fetch data: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

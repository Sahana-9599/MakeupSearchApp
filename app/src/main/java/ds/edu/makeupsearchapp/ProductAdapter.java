/**
 * Author : Sahana Baggaon Gajendra
 * email : sahanabaggaongajendra@gmail.com
 */


package ds.edu.makeupsearchapp;

/**
 * This class defines the adapter for the RecyclerView used to display a list of makeup products.
 * It binds product data (name, price, image) to the UI components in each item of the RecyclerView
 * and handles click events to open the product link in a web browser.
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> products;

    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    /**
     * Inflates the item layout and creates a ViewHolder for each item.
     *
     * @param parent   The parent ViewGroup into which the new view will be added.
     * @param viewType The type of view (not used here as all items are the same).
     * @return A new instance of ProductViewHolder.
     */

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

/**
 * Binds the data for a product to the corresponding UI components in the ViewHolder.
 */
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText("$" + product.getPrice());

        Picasso.get().load(product.getImage_link()).into(holder.ivProduct);

        holder.itemView.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(product.getProduct_link()));
            v.getContext().startActivity(browserIntent);
        });
    }

/**
 * Returns the total number of products in the list.
 */
    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice;
        ImageView ivProduct;

        /**
         * ViewHolder class to hold references to the UI components for each product item.
         */
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvProductPrice);
            ivProduct = itemView.findViewById(R.id.ivProductImage);
        }
    }
}

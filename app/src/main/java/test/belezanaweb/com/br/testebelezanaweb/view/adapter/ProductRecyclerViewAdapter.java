package test.belezanaweb.com.br.testebelezanaweb.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import test.belezanaweb.com.br.testebelezanaweb.R;
import test.belezanaweb.com.br.testebelezanaweb.model.Product;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {

    private List<Product> products;
    private OnItemClickListener listener;

    public ProductRecyclerViewAdapter(List<Product> products) {
        this.products = products;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_products_item, parent, false);
        return new ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Product model = products.get(position);

        if (model.getImageObject() != null) {
            Glide.with(holder.ivImage.getContext())
                    .load(model.getImageObject().getLarge())
                    .into(holder.ivImage);
        }

        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());

        holder.tvDescription.setText(model.getName());
        holder.tvPrice.setText(format.format(model.getPriceSpecification().getPrice()));
        holder.tvCode.setText(holder.tvCode.getContext().getString(R.string.product_code, model.getSku()));

        if (model.getPriceSpecification().getOriginalPrice() > model.getPriceSpecification().getPrice()){
            holder.tvPriceOriginal.setVisibility(View.VISIBLE);
            holder.tvPrice.setText(format.format(model.getPriceSpecification().getOriginalPrice()));
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        OnItemClickListener mListener;
        ImageView ivImage;
        TextView tvDescription;
        TextView tvPrice;
        TextView tvPriceOriginal;
        TextView tvCode;
        Button btDetail;

        ViewHolder(View v, OnItemClickListener listener) {
            super(v);
            ivImage = v.findViewById(R.id.product_image);
            tvDescription = v.findViewById(R.id.product_description);
            tvCode = v.findViewById(R.id.product_code);
            tvPrice = v.findViewById(R.id.product_price);
            tvPriceOriginal = v.findViewById(R.id.product_price_original);
            btDetail = v.findViewById(R.id.product_detail);
            mListener = listener;

            btDetail.setOnClickListener(view -> mListener.onClick(view, getAdapterPosition()));
        }
    }
}

package test.belezanaweb.com.br.testebelezanaweb.view.ui.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.Locale;

import test.belezanaweb.com.br.testebelezanaweb.R;
import test.belezanaweb.com.br.testebelezanaweb.model.Product;
import test.belezanaweb.com.br.testebelezanaweb.view.ui.activity.MainActivity;

public class ProductDetailFragment extends Fragment {

    private ImageView ivImage;
    private TextView tvDescription;
    private TextView tvPriceOriginal;
    private TextView tvPriceDiscount;
    private TextView tvPriceInstallments;
    private TextView tvName;
    private TextView tvCode;
    private TextView tvFullDescription;
    private Button btBuy;
    private Button btCallMe;

    private final static String KEY = "product";

    public static ProductDetailFragment newInstance(Product product) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY, product);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity activity = ((MainActivity) getActivity());

        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setTitle(R.string.product_detail);
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        initView(getView());
        if (getArguments().getSerializable(KEY) == null) {
            Toast.makeText(getActivity().getApplicationContext(), R.string.product_detail_error, Toast.LENGTH_LONG).show();
        } else {
            final Product product = (Product) getArguments().getSerializable(KEY);
            setData(product);
        }
    }

    private void successAddCart() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_cart);
        builder.setMessage(R.string.dialog_success_add);
        builder.setPositiveButton(R.string.dialog_close, (dialogInterface, i) -> getActivity().onBackPressed());

        builder.show().setCanceledOnTouchOutside(false);
    }

    private void successSendContact() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_call_me);
        builder.setMessage(R.string.dialog_success_send_contact);
        builder.setPositiveButton(R.string.dialog_close, (dialogInterface, i) -> getActivity().onBackPressed());

        builder.show().setCanceledOnTouchOutside(false);
    }

    private void openCallMe() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_call_me);
        builder.setView(R.layout.dialog_call_me);
        builder.setPositiveButton(R.string.call_me, (dialogInterface, i) -> successSendContact());

        builder.show().setCanceledOnTouchOutside(false);
    }

    private void initView(View view) {
        ivImage = view.findViewById(R.id.product_image);
        tvDescription = view.findViewById(R.id.product_description);
        tvPriceOriginal = view.findViewById(R.id.product_price_original);
        tvPriceDiscount = view.findViewById(R.id.product_price_discount);
        tvPriceInstallments = view.findViewById(R.id.product_price_parcel);
        tvName = view.findViewById(R.id.product_name);
        tvCode = view.findViewById(R.id.product_code);
        tvFullDescription = view.findViewById(R.id.product_full_description);
        btBuy = view.findViewById(R.id.product_buy);
        btCallMe = view.findViewById(R.id.product_call_me);

        btBuy.setOnClickListener(view1 -> successAddCart());
        btCallMe.setOnClickListener(view12 -> openCallMe());
    }

    private void setData(Product product) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());

        Glide.with(getActivity().getApplicationContext())
                .load(product.getImageObject().getLarge())
                .into(ivImage);

        tvDescription.setText(product.getName());
        tvPriceOriginal.setText(format.format(product.getPriceSpecification().getOriginalPrice()));
        tvPriceOriginal.setPaintFlags(tvPriceOriginal.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tvPriceDiscount.setText(format.format(product.getPriceSpecification().getPrice()));
        Integer num = product.getPriceSpecification().getInstallments().getNumberOfPayments();
        String monthly = format.format(product.getPriceSpecification().getInstallments().getMonthlyPayment());
        tvPriceInstallments.setText(getString(R.string.product_installments, num, monthly));
        tvCode.setText(getString(R.string.product_code, product.getSku()));
        if (product.getBrand() != null && product.getBrand().getLine() != null) {
            tvFullDescription.setText(product.getBrand().getLine().getDescription());
            tvName.setText(product.getBrand().getLine().getName());
        }

        if (product.getInventory().getQuantity() > 0) {
            btBuy.setVisibility(View.VISIBLE);
            btCallMe.setVisibility(View.GONE);
        }
    }
}

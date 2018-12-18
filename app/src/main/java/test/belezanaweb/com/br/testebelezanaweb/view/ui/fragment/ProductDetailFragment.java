package test.belezanaweb.com.br.testebelezanaweb.view.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
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
    private TextView tvPriceRaw;
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
            activity.getSupportActionBar().setTitle("Detalhe do produto");
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        initView(getView());
        if (getArguments().getSerializable(KEY) == null) {
            Toast.makeText(getActivity().getApplicationContext(), "erro", Toast.LENGTH_LONG).show();
        } else {
            final Product product = (Product) getArguments().getSerializable(KEY);
            setData(product);
        }
    }

    void initView(View view) {
        ivImage = view.findViewById(R.id.product_image);
        tvDescription = view.findViewById(R.id.product_description);
        tvPriceRaw = view.findViewById(R.id.product_price_raw);
        tvPriceDiscount = view.findViewById(R.id.product_price_discount);
        tvPriceInstallments = view.findViewById(R.id.product_price_parcel);
        tvName = view.findViewById(R.id.product_name);
        tvCode = view.findViewById(R.id.product_code);
        tvFullDescription = view.findViewById(R.id.product_full_description);
        btBuy = view.findViewById(R.id.product_buy);
        btCallMe = view.findViewById(R.id.product_call_me);
    }

    void setData(Product product) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());

        Glide.with(getActivity().getApplicationContext())
                .load(product.getImageObject().getMedium())
                .into(ivImage);

        tvDescription.setText(product.getName());
        tvPriceRaw.setText(format.format(product.getPriceSpecification().getOriginalPrice()));
        tvPriceDiscount.setText(format.format(product.getPriceSpecification().getPrice()));
        String installment = product.getPriceSpecification().getInstallments().getNumberOfPayments() + "x ";
        installment = installment.concat(format.format(product.getPriceSpecification().getInstallments().getMonthlyPayment()));
        tvPriceInstallments.setText(installment);
        //tvName.setText();
        tvCode.setText("Cod: " + product.getSku());
        //tvFullDescription = view.findViewById(R.id.product_full_description);
        //btBuy = view.findViewById(R.id.product_buy);
        //btCallMe = view.findViewById(R.id.product_call_me);


    }
}

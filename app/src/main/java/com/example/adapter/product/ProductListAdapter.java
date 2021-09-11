package com.example.adapter.product;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.lunchbox.R;
import com.example.model.Price;
import com.example.model.Product;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.List;

/**
 * @Authot: Albert Akimov
 * @Date: 04.09.2021
 * @Description:
 */
public class ProductListAdapter extends ArrayAdapter<Product> {

    private final Context mContext;
    private final int mResource;
    private int lastPosition;

    private static class ProductView {
        TextView id;
        TextView nameProduct;
        TextView description;
        TextView price;
        ImageView image;
    }

    public ProductListAdapter(@NonNull Context context, int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        setupImageLoader();

        String nameProduct = getItem(position).getName();
        String description = getItem(position).getDescription();
        Long price = getItem(position).getPrice();
        String image = getItem(position).getImage();
        byte[] decodedString  = Base64.decode(getItem(position).getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        Product product = new Product(1L, nameProduct, description, price, image);

        final View result;

        ProductView productView;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            productView= new ProductView();
            productView.nameProduct = (TextView) convertView.findViewById(R.id.nameProduct);
            productView.description = (TextView) convertView.findViewById(R.id.description);
            productView.price = (TextView) convertView.findViewById(R.id.price);
            productView.image = (ImageView) convertView.findViewById(R.id.image);

            result = convertView;

            convertView.setTag(productView);
        }
        else{
            productView = (ProductView) convertView.getTag();
            result = convertView;
        }


        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);

        result.startAnimation(animation);

        lastPosition = position;

        ImageLoader imageLoader = ImageLoader.getInstance();

        int defaultImage = mContext.getResources().getIdentifier("@drawable/ic_input_add", null, mContext.getOpPackageName());

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .showImageOnLoading(defaultImage).build();

        //imageLoader.displayImage(image, productView.image, options);

        productView.image.setImageBitmap(decodedByte);

        productView.nameProduct.setText(product.getName());
        productView.description.setText(product.getDescription());
        productView.price.setText(product.getPrice().toString());

        return convertView;

    }

    private void setupImageLoader() {
        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mContext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP
    }
}

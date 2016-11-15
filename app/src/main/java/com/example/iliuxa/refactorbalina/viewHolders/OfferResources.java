package com.example.iliuxa.refactorbalina.viewHolders;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iliuxa.refactorbalina.R;
import com.example.iliuxa.refactorbalina.entity.Offer;


public class OfferResources extends BaseViewHolder<Offer> {
    private TextView mOfferName;
    private TextView mOfferPrice;
    private TextView mOfferDescription;
    public final static int LAYOUT_ID = R.layout.simple_offer_list_item;


    public OfferResources(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(LAYOUT_ID, parent, false));
        mOfferName = (TextView)itemView.findViewById(R.id.offerNameText);
        mOfferPrice = (TextView)itemView.findViewById(R.id.priceText);
        mOfferDescription = (TextView)itemView.findViewById(R.id.descriptionText);
        image = (ImageView) itemView.findViewById(R.id.offerImage);
    }

    @Override
    public void bind(Offer data) {
        mOfferName.setText(data.getName());
        mOfferPrice.setText(data.getPrice());
        mOfferDescription.setText(data.getDescription());
        super.loadImage(data.getPicture());
    }
}



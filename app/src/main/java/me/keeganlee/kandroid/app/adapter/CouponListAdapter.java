package me.keeganlee.kandroid.app.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.keeganlee.kandroid.R;
import me.keeganlee.kandroid.app.util.CouponPriceUtil;
import me.keeganlee.kandroid.model.CouponBO;

/**
 * Created by Tian on 2016/5/3.
 */
public class CouponListAdapter extends KBaseAdapter<CouponBO> {
    public CouponListAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_list_coupon, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        CouponBO coupon = itemList.get(i);
        holder.titleText.setText(coupon.getName());
        holder.infoText.setText(coupon.getIntroduce());
        SpannableString priceString = null;
        switch (coupon.getModelType()) {
            case CouponBO.TYPE_CASH:
                priceString = CouponPriceUtil.getCashPrice(context, coupon.getFaceValue(), coupon.getEstimateAmount());
                break;
            case CouponBO.TYPE_DEBIT:
                priceString = CouponPriceUtil.getVoucherPrice(context, coupon.getDebitAmount(), coupon.getMiniAmount());
                break;
            case CouponBO.TYPE_DISCOUNT:
                priceString = CouponPriceUtil.getDiscountPrice(context, coupon.getDiscount(), coupon.getMiniAmount());
                break;
            default:
        }
        holder.priceText.setText(priceString);
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.text_item_title)
        TextView titleText;
        @Bind(R.id.text_item_info)
        TextView infoText;
        @Bind(R.id.text_item_price)
        TextView priceText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}

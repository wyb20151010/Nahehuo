package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.BankCard;
import java.util.List;

/**
 * Created by wyb on 2016/3/2.
 */
public class PerWalBankCardAdapter extends BaseAdapter {

    private List<BankCard> mBankCards;

    private Context mContext;


    public PerWalBankCardAdapter(List<BankCard> bankCards, Context context) {
        mBankCards = bankCards;
        mContext = context;
    }


    @Override public int getCount() {
        return mBankCards.size();
    }


    @Override public Object getItem(int position) {
        return mBankCards.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BankCard item = mBankCards.get(position);
        ViewHolder viewHolder;
        View v;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_per_wallet_card, null);
            viewHolder.tv_bank = (TextView) v.findViewById(R.id.tv_bank);
            viewHolder.tv_name = (TextView) v.findViewById(R.id.tv_name);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.tv_bank.setText(item.getCard_name());
        viewHolder.tv_name.setText(item.getName());
        return v;
    }


    static class ViewHolder {
        TextView tv_name;
        TextView tv_bank;
    }
}

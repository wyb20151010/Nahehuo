package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.DictCityData;
import java.util.List;

/**
 * 行业选择
 * 
 * @author LT
 * 
 */
public class SelectAdapter<T> extends BaseAdapter {

	/**
	 * 行业
	 */
	private List<T> dictIndustries;

	private LayoutInflater inflater;
	private Context mContext;
	private int selectPosition = 0;
	private int selectPositionChild = 0;

	public void setCheckItem(int position) {
		selectPosition = position;
		notifyDataSetChanged();
	}

	public void setCheckChildItem(int position) {
		selectPositionChild = position;
		notifyDataSetChanged();
	}
	public int getSelectPosition() {
		return selectPosition;
	}

	public void setSelectPosition(int selectPosition) {
		this.selectPosition = selectPosition;
	}

	public void setSelectPositionChild(int selectPosition) {
		this.selectPositionChild = selectPosition;
	}

	public SelectAdapter(Context context, List<T> dictIndustries) {
		this.dictIndustries = dictIndustries;
		this.mContext = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return dictIndustries.size() == 0 ? 0 : dictIndustries.size();
	}

	@Override
	public T getItem(int position) {
		return dictIndustries.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;

		if (convertView != null) {
			viewHolder = (ViewHolder) convertView.getTag();
		}else {
			convertView = inflater.inflate(R.layout.item_list_drop_down, null);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		}

		/*
		 * 地点
		 */
		if (dictIndustries.get(0) instanceof DictCityData) {
			viewHolder.mText.setText(((DictCityData) dictIndustries.get(position)).getName());

			if (((DictCityData) dictIndustries.get(position)).getLevel() .equals
					("1")) {
				if (selectPosition == position) {
					viewHolder.mText.setTextColor(mContext.getResources()
														  .getColor(
																  R.color.drop_down_selected));
					viewHolder.mText.setCompoundDrawablesWithIntrinsicBounds(null,
							null, null, null);

				} else {
					viewHolder.mText.setTextColor(mContext.getResources()
														 .getColor(
																 R.color.drop_down_unselected));
					viewHolder.mText.setCompoundDrawablesWithIntrinsicBounds(null,
							null, null, null);
				}
			} else {
				if (selectPositionChild == position) {
					viewHolder.mText.setTextColor(mContext.getResources()
														  .getColor(
																  R.color.drop_down_selected));
					viewHolder.mText.setCompoundDrawablesWithIntrinsicBounds(
							null, null, mContext.getResources()
												.getDrawable(
														R.drawable.drop_down_checked),
							null);
				} else {
					viewHolder.mText.setTextColor(mContext.getResources()
														  .getColor(
																  R.color.drop_down_unselected));
					viewHolder.mText.setCompoundDrawablesWithIntrinsicBounds(null,
							null, null, null);
				}
			}
		}

		return convertView;
	}

	static class ViewHolder {
		TextView mText;


		ViewHolder(View view) {
			mText = (TextView) view.findViewById(R.id.text);
		}

	}
}

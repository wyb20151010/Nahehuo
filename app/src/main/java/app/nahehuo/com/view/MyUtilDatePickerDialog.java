package app.nahehuo.com.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

public class MyUtilDatePickerDialog extends DatePickerDialog {

	@SuppressWarnings("deprecation")
	public MyUtilDatePickerDialog(Context context, int theme, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
		super(context, theme, callBack, year, monthOfYear, dayOfMonth);
		// this.setTitle("设置生日：");
		this.setButton("确认", this);
		this.setButton2("取消", (OnClickListener) null);
	}

	@Override
	protected void onStop() {

	}

	@Override
	public void onDateChanged(DatePicker view, int year, int month, int day) {
		this.setTitle("Set Date");
	}

}

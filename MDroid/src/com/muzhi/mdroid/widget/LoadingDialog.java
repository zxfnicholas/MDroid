package com.muzhi.mdroid.widget;
import com.muzhi.mdroid.R;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 自定义加载框
 * @author jazzy
 * @date 2014/10/30
 *
 */
public class LoadingDialog {
	/**
	 * 得到自定义的progressDialog
	 * @param context
	 * @param msg
	 * @return
	 */
	public static Dialog createLoadingDialog(Context context, String msg) {

		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.mdroid_load_dialog, null);// 得到加载view
		TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
		
		if(msg!=null && !"".equals(msg)){
			tipTextView.setText(msg);
			tipTextView.setVisibility(View.VISIBLE);
		}
		

		Dialog loadingDialog = new Dialog(context, R.style.dialogStyle);// 创建自定义样式dialog

		loadingDialog.setCancelable(true);// 不可以用“返回键”取消
		loadingDialog.setCanceledOnTouchOutside(false);//点击其他区域不能取消
		loadingDialog.setContentView(v, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
		return loadingDialog;

	}
}

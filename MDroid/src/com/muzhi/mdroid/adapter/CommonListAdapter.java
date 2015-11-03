package com.muzhi.mdroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import com.muzhi.mdroid.tools.ViewHolder;


public abstract class CommonListAdapter<T> extends BaseAdapter
{
	protected Context mContext;
	protected List<T> mList;
	protected LayoutInflater mInflater;
	protected int mLayoutId;

	protected OnItemCallBackClickListener onItemClick = null;
	
	public CommonListAdapter(Context context)
	{
		this.mContext = context;
		/*mInflater = LayoutInflater.from(context);
		this.mList = datas;
		this.layoutId = layoutId;*/
	}

	@Override
	public int getCount()
	{
		if (mList != null)
			return mList.size();
		else
			return 0;
	}

	@Override
	public T getItem(int position)
	{
		return mList.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	
	public List<T> getList() {
		return mList;
	}
	

	public void setList(List<T> list) {
		this.mList = list;
		notifyDataSetChanged();
	}
	public void setList(T[] list) {
		ArrayList<T> arrayList = new ArrayList<T>(list.length);
		for (T t : list) {
			arrayList.add(t);
		}
		setList(arrayList);
	}
	
	public void addToFirst(List<T> list) {
		
		ArrayList<T> arrayList = new ArrayList<T>(list.size());
		for (T t : list) {
			arrayList.add(t);
		}
		setList(arrayList);
	}

	public void addToLast(List<T> list) {		
		 if (list != null && list.size() > 0) {	            
			 this.mList.addAll(list);
	     }
		 this.notifyDataSetChanged();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = ViewHolder.get(mContext, convertView, parent,mLayoutId, position);
		getCommonView(holder, getItem(position));
		return holder.getConvertView();
	}

	public abstract void getCommonView(ViewHolder holder, T t);

	
	/**
	 * 回调
	 */
	public void OnItemClickListener(OnItemCallBackClickListener mClickListener) {
		this.onItemClick = mClickListener;
	}
	
	/**
	 * 按钮事件接口
	 */
	public interface OnItemCallBackClickListener {
		public void onItemClick(int position,int flag);
	}
}

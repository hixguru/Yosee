package kr.yosee.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import kr.yosee.R;
import kr.yosee.adapter.model.RecyclerDataModel;
import kr.yosee.adapter.view.BaseViewHolder;
import kr.yosee.adapter.view.ModelAdapterView;
import kr.yosee.adapter.view.holder.MainRecipeViewHolder;
import kr.yosee.adapter.view.holder.MaterialViewHolder;
import kr.yosee.view.listeners.OnRecyclerItemClickListener;

/**
 * Created by hwanik on 2017. 1. 26..
 */

public class RecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>>
    implements RecyclerDataModel<T>, ModelAdapterView {

    public static final int MAIN_VIEW = 0;
    public static final int MATERIAL_VIEW = 1;

    private final Context context;
    private final int viewHolderType;
    private List<T> items;

    public OnRecyclerItemClickListener onRecyclerItemClickListener;

    public RecyclerAdapter(Context context, int viewHolderType) {
        this.context = context;
        this.viewHolderType = viewHolderType;
        this.items = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    @Override
    public BaseViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewHolderType) {
            case MAIN_VIEW:
                view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.home_recipe_item, parent, false);
                return (BaseViewHolder<T>) new MainRecipeViewHolder(this, view);
            case MATERIAL_VIEW:
                view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recipe_material_item, parent, false);
                return (BaseViewHolder<T>) new MaterialViewHolder(this, view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<T> holder, int position) {
        holder.onBindView(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public T getItem(int position) {
        return items.get(position);
    }

    public void setOnRecyclerItemClickListener(
        OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void add(T item) {
        items.add(item);
    }
}

package kr.yosee.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import kr.yosee.R;
import kr.yosee.adapter.model.RecipeDataModel;
import kr.yosee.adapter.view.RecipeAdapterView;
import kr.yosee.model.Recipe;

/**
 * Created by hwanik on 2017. 1. 26..
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
    implements RecipeDataModel, RecipeAdapterView {

    private final Context context;
    private List<Recipe> items;

    public RecyclerAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_recipe_image) ImageView recipeImage;
        @BindView(R.id.tv_recipe_title) TextView recipeTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe recipe = items.get(position);

        Glide.with(context)
            .load(recipe.getImgUrl())
            .into(holder.recipeImage);
        holder.recipeTitle.setText(recipe.getTitle());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void add(Recipe recipe) {
        items.add(recipe);
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }
}

package kr.yosee.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
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
import kr.yosee.view.listeners.OnRecyclerItemClickListener;

/**
 * Created by hwanik on 2017. 1. 26..
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
    implements RecipeDataModel, RecipeAdapterView {

    private final Context context;
    private List<Recipe> recipes;

    public OnRecyclerItemClickListener onRecyclerItemClickListener;

    public RecyclerAdapter(Context context) {
        this.context = context;
        this.recipes = new ArrayList<>();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.layout_recipe_container) CardView itemView;
        @BindView(R.id.iv_recipe_image) ImageView recipeImage;
        @BindView(R.id.tv_recipe_title) TextView recipeTitle;
        @BindView(R.id.tv_recipe_description) TextView recipeDescription;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.fragment_home_recipe_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);

        Glide.with(context).load(recipe.getImgUrl()).into(holder.recipeImage);
        holder.recipeTitle.setText(recipe.getTitle());
        holder.recipeDescription.setText(recipe.getDescription());

        holder.itemView.setOnClickListener(view -> {
            if (onRecyclerItemClickListener != null) {
                onRecyclerItemClickListener.onItemClick(RecyclerAdapter.this, position);
            }
        });
    }

    public Recipe getItem(int position) {
        return recipes.get(position);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    @Override
    public void add(Recipe recipe) {
        recipes.add(recipe);
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    public void setOnRecyclerItemClickListener(
        OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }
}

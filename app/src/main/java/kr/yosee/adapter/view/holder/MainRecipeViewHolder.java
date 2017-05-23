package kr.yosee.adapter.view.holder;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import kr.yosee.R;
import kr.yosee.adapter.RecyclerAdapter;
import kr.yosee.adapter.view.BaseViewHolder;
import kr.yosee.model.Recipe;

/**
 * Created by hwanik on 2017. 2. 4..
 */

public class MainRecipeViewHolder extends BaseViewHolder<Recipe> {
    private final RecyclerAdapter adapter;
    @BindView(R.id.layout_recipe_container) CardView itemView;
    @BindView(R.id.iv_recipe_image) ImageView recipeImage;
    @BindView(R.id.tv_recipe_title) TextView recipeTitle;
    @BindView(R.id.tv_recipe_description) TextView recipeDescription;

    public MainRecipeViewHolder(RecyclerAdapter adapter, View view) {
        super(view);
        this.adapter = adapter;
        ButterKnife.bind(this, view);
    }

    @Override
    public void onBindView(Recipe recipe, int position) {
        if (recipeImage != null) {
            Glide.with(adapter.getContext()).load(recipe.mainStep.mainImage).into(recipeImage);
        }

        recipeTitle.setText(recipe.mainStep.mainTitle);
        recipeDescription.setText(recipe.mainStep.mainDescription);

        itemView.setOnClickListener(view -> {
            if (adapter.onRecyclerItemClickListener != null) {
                adapter.onRecyclerItemClickListener.onItemClick(adapter, position);
            }
        });
    }
}

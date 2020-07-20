package kr.yosee.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_detail_recipe.indicator
import kotlinx.android.synthetic.main.activity_detail_recipe.view_pager
import kr.yosee.R
import kr.yosee.adapter.DetailPagerAdapter
import kr.yosee.model.Recipe

class DetailRecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_recipe)

        initToolbar()

        val adapter: DetailPagerAdapter = DetailPagerAdapter(supportFragmentManager, this)

        view_pager.adapter = adapter
        view_pager.offscreenPageLimit = 3
        indicator.setViewPager(view_pager)
        adapter.registerDataSetObserver(indicator.dataSetObserver)

        val recipe: Recipe = intent.getParcelableExtra("recipe")

        adapter.addItem(DetailMain(recipe.mainStep.image,
                                   recipe.mainStep.title,
                                   recipe.mainStep.description))

        adapter.addItem(
            DetailMaterials(recipe.mainStep.image, recipe.mainStep.title, recipe.additionalInfo,
                            recipe.materials))

        adapter.addItem(DetailStep(recipe.mainStep.image,
                                   recipe.mainStep.title,
                                   recipe.mainStep.description))
    }

    private fun initToolbar() {
        val toolBar: Toolbar = findViewById(R.id.tool_bar) as Toolbar
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}

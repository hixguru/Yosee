package kr.yosee.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail_materials.iv_detail_mat_image
import kotlinx.android.synthetic.main.fragment_detail_materials.tv_cooking_time
import kotlinx.android.synthetic.main.fragment_detail_materials.tv_detail_mat_title
import kotlinx.android.synthetic.main.fragment_detail_materials.tv_mats
import kotlinx.android.synthetic.main.fragment_detail_materials.tv_serving
import kotlinx.android.synthetic.main.fragment_detail_materials.tv_tip
import kr.yosee.R
import kr.yosee.model.AdditionalInfo
import kr.yosee.model.Material

class DetailMaterials(val image: String, val title: String, val additionalInfo: AdditionalInfo, val mats: List<Material>) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_detail_materials, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(image).into(iv_detail_mat_image)
        tv_detail_mat_title.text = title
        tv_serving.text = additionalInfo.serving
        tv_cooking_time.text = additionalInfo.cookingTime
        tv_tip.text = additionalInfo.tip

        val mat: StringBuffer = StringBuffer()

        for ((index, material) in mats.withIndex()) {
            mat.append(material.matName).append(" ")
                .append(material.matAmount)
                .append(material.matUnit)

            if (index != mats.size - 1) mat.append(", ")
        }

        tv_mats.text = mat
    }
}

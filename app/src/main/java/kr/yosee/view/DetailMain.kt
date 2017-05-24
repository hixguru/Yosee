package kr.yosee.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail_main.iv_detail_main_image
import kotlinx.android.synthetic.main.fragment_detail_main.tv_detail_main_description
import kotlinx.android.synthetic.main.fragment_detail_main.tv_detail_main_title
import kr.yosee.R

class DetailMain(val image: String, val title: String, val description: String) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_detail_main, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(image).into(iv_detail_main_image)
        tv_detail_main_title.text = title
        tv_detail_main_description.text = description
    }
}

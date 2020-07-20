package kr.yosee.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail_step.iv_step_image
import kotlinx.android.synthetic.main.fragment_detail_step.tv_step_description
import kotlinx.android.synthetic.main.fragment_detail_step.tv_step_title
import kr.yosee.R

class DetailStep(val image: String, val title: String, val description: String) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_detail_step, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(image).into(iv_step_image)
        tv_step_title.text = title
        tv_step_description.text = description
    }
}

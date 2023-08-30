package com.hncr.ibms.adapter

import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.chad.library.adapter.base.util.MultiTypeDelegate
import com.hncr.ibms.R
import com.hncr.ibms.http.data.FirstTypeBean
import com.hncr.ibms.http.data.SecondTypeBean
import com.hncr.ibms.http.data.ThreeTypeBean
import com.hncr.ibms.http.data.UserItemBean

/**
 * Created by LG
 * on 2023/8/2  18:37
 * Description：
 */
class MyExpandRecyclerAdapter(data : MutableList<MultiItemEntity>?) : BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder>(data){

    //布局类型：一级列表
    private val ITEM_TYPE_FIRST = 0
    //布局类型：二级列表
    private val ITEM_TYPE_SECOND = 1
    //布局类型：三级列表
    private val ITEM_TYPE_THREE = 2


    init {
        addItemType(ITEM_TYPE_FIRST, R.layout.layout_expand_first)
        addItemType(ITEM_TYPE_SECOND,R.layout.layout_expand_second)
        addItemType(ITEM_TYPE_THREE,R.layout.layout_expand_three)
    }

    override fun convert(helper: BaseViewHolder?, item: MultiItemEntity?) {
        when(item?.itemType){
            0 -> {
                val firstTypeBean : FirstTypeBean = item as FirstTypeBean
                helper!!.setText(R.id.tv_first_name,firstTypeBean.label)
                helper.itemView.setOnClickListener{
                    val pos : Int = helper.adapterPosition
                    if (firstTypeBean.isExpanded){
                        collapse(pos)
                    }else{
                        expand(pos)
                    }
                }

            }
            1 -> {
                val secondTypeBean : SecondTypeBean = item as SecondTypeBean
                helper!!.setText(R.id.tv_second_name,secondTypeBean.label)
                helper.itemView.setOnClickListener {
                    val pos: Int = helper.adapterPosition

                    if (pos >= 0) {
                        if (secondTypeBean.isExpanded) {
                            collapse(pos)
                        } else {
                            expand(pos)
                        }
                    }
                }
            }
            2->{
                val threeTypeBean : ThreeTypeBean = item as ThreeTypeBean

                if (threeTypeBean.roomId.isNullOrEmpty()){
                    helper!!.setText(R.id.tv_three_name,threeTypeBean.label)
                }else{
                    helper!!.setText(R.id.tv_three_name,threeTypeBean.label + " (办公室：" + threeTypeBean.roomId + ")")
                }


            }
        }
    }


}


package com.hncr.ibms.http.data

import com.chad.library.adapter.base.entity.AbstractExpandableItem
import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * Created by LG
 * on 2023/8/2  19:39
 * Description：
 */
data class SecondTypeBean(val id : Int, val label : String, val roomId : String?) : AbstractExpandableItem<ThreeTypeBean>(),MultiItemEntity{
    override fun getLevel(): Int {
        return 1
    }

    override fun getItemType(): Int {
        return 1
    }

}

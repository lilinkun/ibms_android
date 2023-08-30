package com.hncr.ibms.http.data

import com.chad.library.adapter.base.entity.AbstractExpandableItem
import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * Created by LG
 * on 2023/8/2  19:37
 * Description：
 */
data class FirstTypeBean(val id : Int, val label : String, val roomId : String?) : AbstractExpandableItem<SecondTypeBean>(),MultiItemEntity{
    override fun getLevel(): Int {
        return 0
    }

    override fun getItemType(): Int {
        return 0
    }

}

package com.hncr.ibms.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hncr.ibms.R
import com.hncr.ibms.http.data.UserInfoBean

/**
 * Created by LG
 * on 2023/8/3  19:15
 * Description：
 */
class SearchAdapter(layoutResId: Int, data : List<UserInfoBean>) : BaseQuickAdapter<UserInfoBean,BaseViewHolder>(layoutResId,data) {


    override fun convert(helper: BaseViewHolder?, item: UserInfoBean?) {
        helper!!.setText(R.id.tv_name,item?.userName)


        if (item?.roomId.isNullOrEmpty()){
            helper!!.setText(R.id.tv_dept,"(" + item?.deptUpName + "-" + item?.deptName + ")")
        }else{
            helper!!.setText(R.id.tv_dept,"(" + item?.deptUpName + "-" + item?.deptName + " | 办公室：" + item?.roomId + ")")
        }

    }


}
package com.hncr.ibms.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.hncr.ibms.R
import com.hncr.ibms.http.data.FloorItem
import com.hncr.ibms.tools.EVENTBUS_FLOOR_ID
import com.jeremyliao.liveeventbus.LiveEventBus

/**
 * Created by LG
 * on 2023/8/2  11:39
 * Description：
 */
class GridAdapter(private val context: Context, private val floorList: List<FloorItem>) :BaseAdapter() {

    override fun getCount(): Int = floorList.size

    override fun getItem(position: Int): Any = floorList.get(position)


    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val holder : ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_grid_item,null)
            holder = ViewHolder()
            holder.myGridTitle = view.findViewById(R.id.tv_grid_title)
            holder.myGridContent = view.findViewById(R.id.tv_grid_content)
            view.tag = holder
        }else{
            holder = view?.tag as ViewHolder
        }

        val myItem = floorList.get(position)

        holder.myGridTitle.text = myItem.floorName
        holder.myGridContent.text = myItem.deptName

        view?.setOnClickListener(View.OnClickListener {
            LiveEventBus.get(EVENTBUS_FLOOR_ID,String::class.java).post(myItem.floorName)
        })


        return view!!
    }

    inner class ViewHolder{
        lateinit var myGridTitle : TextView
        lateinit var myGridContent : TextView
    }

}
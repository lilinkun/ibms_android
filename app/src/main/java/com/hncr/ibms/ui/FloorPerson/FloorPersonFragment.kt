package com.hncr.ibms.ui.FloorPerson

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.adapters.AdapterViewBindingAdapter.OnItemSelected
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.hncr.ibms.R
import com.hncr.ibms.adapter.MyExpandRecyclerAdapter
import com.hncr.ibms.adapter.SearchAdapter
import com.hncr.ibms.base.BaseFragment
import com.hncr.ibms.databinding.FragmentFloorPersonBinding
import com.hncr.ibms.http.data.*
import com.hncr.ibms.tools.EVENTBUS_FLOOR_DATA
import com.hncr.ibms.tools.EVENTBUS_FLOOR_USER
import com.hncr.ibms.tools.EVENTBUS_SEARCH_USER
import com.hncr.ibms.tools.hideKeyboard
import com.hncr.ibms.ui.floornavigation.FloorNagationViewModel
import com.jeremyliao.liveeventbus.LiveEventBus
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by LG
 * on 2023/8/2  15:24
 * Description：
 */
class FloorPersonFragment : BaseFragment<FragmentFloorPersonBinding>() {

    private val args: FloorPersonFragmentArgs by navArgs()
    private val mFloorViewModel by viewModel<FloorNagationViewModel>()

    lateinit var adapter : MyExpandRecyclerAdapter

    lateinit var searchAdapter : SearchAdapter

    private var firstList : MutableList<FirstTypeBean>? = ArrayList()
    private var secondList : MutableList<SecondTypeBean>? = ArrayList()
    private var thirdList : MutableList<ThreeTypeBean>? = ArrayList()

    private var allList : MutableList<MultiItemEntity>? = ArrayList()

    val TAG = "FloorPersonFragment"

    var floor : String? = null

    override fun getLayoutResId(): Int = R.layout.fragment_floor_person

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(args.type == 2){
            mFloorViewModel.searchUser(args.pageTitle)
        }else{
            floor = args.pageTitle.substring(0,args.pageTitle.indexOf("F"))
            mFloorViewModel.getUserList(floor!!)
        }

        LiveEventBus.get(EVENTBUS_FLOOR_USER,Boolean::class.java).observe(this){
            if (it){
                setRecycler(mFloorViewModel.treeInfo)
            }
        }

        LiveEventBus.get(EVENTBUS_SEARCH_USER, Boolean::class.java).observe(this){
            if (it){
                setSearchRecycle(mFloorViewModel.userList);
            }
        }

    }

    override fun initView() {

        if (args.type == 2){
            binding.tvFloorName.text = "搜索"
        }else {
            binding.tvFloorName.text = args.pageTitle
        }

        binding.llBackDetail.setOnClickListener(View.OnClickListener {

            findNavController().navigateUp()

            LiveEventBus.get(EVENTBUS_FLOOR_DATA,Boolean::class.java).post(true)
        })

    }

    override fun observeViewModels() {
    }

    private fun setSearchRecycle(lists : List<UserInfoBean>){


        binding.rvUser.layoutManager = LinearLayoutManager(context)
        searchAdapter = SearchAdapter(R.layout.layout_search_user,lists)
        binding.rvUser.adapter = searchAdapter
    }

    private fun setRecycler(lists : List<UserItemBean>){

        for (a : Int in 0 until lists!!.size){
            if (lists.get(a).id == 0){

                val firstBean = lists.get(a)

                val firstTypeBean = FirstTypeBean(firstBean.id,firstBean.label,firstBean.roomId)
                if (lists.get(a).children?.size != 0){


                    for (i : Int in 0 until lists.get(a).children.size){
                        val secondBean = lists.get(a).children.get(i)
                        val secondTypeBean = SecondTypeBean(secondBean.id,secondBean.label,secondBean.roomId)

                        if (secondBean.children?.size != 0) {
                            for (j : Int in 0 until secondBean.children?.size) {
                                val thirdBean = secondBean.children.get(j)

                                val threeTypeBean = ThreeTypeBean(thirdBean.id, thirdBean.label, thirdBean.roomId)
                                secondTypeBean.addSubItem(threeTypeBean)
                            }
                        }


                        firstTypeBean.addSubItem(secondTypeBean)
                    }

                }

                allList?.add(firstTypeBean)
            }
            /*if (lists.get(a).id == 1){
                val secondTypeBean = SecondTypeBean(lists.get(a).id,lists.get(a).label,lists.get(a).roomId)
                secondList?.add(secondTypeBean)
                firstList?.size?.let { firstList?.get(it-1)?.addSubItem(secondTypeBean) }
            }
            if (lists.get(a).id == 2){
                val threeTypeBean = ThreeTypeBean(lists.get(a).id,lists.get(a).label,lists.get(a).roomId)
                thirdList?.add(threeTypeBean)
                secondList?.size?.let { secondList?.get(it)?.addSubItem(threeTypeBean) }

                firstList?.size?.let { firstList?.get(it-1)?.getSubItem(secondList!!.size-1)!!.addSubItem(threeTypeBean) }
            }*/
        }


        binding.rvUser.layoutManager = LinearLayoutManager(context)

        adapter = MyExpandRecyclerAdapter(allList)
        binding.rvUser.adapter = adapter

        adapter.expand(0)


    }
}
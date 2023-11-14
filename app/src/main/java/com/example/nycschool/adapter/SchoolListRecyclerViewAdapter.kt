package com.example.nycschool.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.network.SchoolData
import com.example.nycschool.databinding.LayoutSchoolRecyclerViewItemBinding
import com.example.nycschool.listener.SchoolListItemClickListener

class SchoolListRecyclerViewAdapter(val onClickListener: SchoolListItemClickListener) :
    RecyclerView.Adapter<SchoolListRecyclerViewAdapter.SchoolListViewHolder>() {

    private val data: MutableList<SchoolData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolListViewHolder {
        return SchoolListViewHolder(
            LayoutSchoolRecyclerViewItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SchoolListViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    fun setData(newData: List<SchoolData>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    inner class SchoolListViewHolder(private val binding: LayoutSchoolRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SchoolData) {
            with(binding) {
                tvSchoolName.text = item.schoolName
                tvCity.text = item.city
                root.setOnClickListener {
                    onClickListener.onItemClickListener(item)
                }
            }
        }
    }
}
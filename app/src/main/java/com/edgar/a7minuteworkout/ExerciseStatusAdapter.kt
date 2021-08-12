package com.edgar.a7minuteworkout

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.edgar.a7minuteworkout.databinding.ItemExerciseStatusBinding


class ExerciseStatusAdapter(val items: ArrayList<ExerciseModel>, val context: Context): RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = ItemExerciseStatusBinding.bind(holder.itemView)

        val model: ExerciseModel = items[position]
        holder.bind(model)

        if(model.getIsSelected()){
            binding.tvItem.background = ContextCompat.getDrawable(context, R.drawable.item_circular_thin_color_accent_border)
            binding.tvItem.setTextColor(Color.parseColor("#212121"))
        } else if(model.getIsComplete()){
            binding.tvItem.background = ContextCompat.getDrawable(context, R.drawable.item_circular_color_accent_background)
            binding.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
        } else {
            binding.tvItem.background = ContextCompat.getDrawable(context, R.drawable.item_circular_color_gray_background)
            binding.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
        }
    }

    class ViewHolder(private val binding: ItemExerciseStatusBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(model: ExerciseModel) {
            binding.tvItem.text = model.getId().toString()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
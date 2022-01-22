package com.example.nuntium.slide

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import android.R

import androidx.annotation.NonNull

import android.view.LayoutInflater

import android.view.ViewGroup

import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.delay


class SliderAdapter(
    sliderItems: List<SliderItems>,
    viewPager2: ViewPager2,
) :
    RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {
    private val sliderItems: List<SliderItems> = sliderItems
    private val viewPager2: ViewPager2 = viewPager2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                com.example.nuntium.R.layout.slide_item_container, parent, false
            ))
    }


    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.setImage(sliderItems[position])
    }

    override fun getItemCount(): Int {
        return sliderItems.size
    }

    inner class SliderViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val imageView: RoundedImageView =
            itemView.findViewById(com.example.nuntium.R.id.imageSlide)

        fun setImage(sliderItems: SliderItems) {
            sliderItems.getImage()?.let { imageView.setImageResource(it) }
        }

    }

}
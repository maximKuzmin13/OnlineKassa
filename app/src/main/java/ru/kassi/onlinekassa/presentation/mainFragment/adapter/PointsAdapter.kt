package ru.kassi.onlinekassa.presentation.mainFragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.databinding.ItemPointBinding
import ru.kassi.onlinekassa.domain.api.points.Point

class PointsAdapter(val listener: (Int)-> Unit): RecyclerView.Adapter<PointsAdapter.PointsViewHolder>() {

    var data: List<Point> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPointBinding.inflate(inflater, parent, false)

        return PointsViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PointsViewHolder, position: Int) {
        val point = data[position]
        with(holder.binding){
            titleValue.text = point.name
            adressValue.text = point.adress
            kassaValue.text = point.registerName
            val backGround = if (point.haveExpiredKassa) R.drawable.item_border_red else R.drawable.item_border_default
            pointBorder.setBackgroundResource(backGround)
            root.setOnClickListener {
                listener.invoke(point.num)
            }
        }
    }

    class PointsViewHolder(val binding: ItemPointBinding): RecyclerView.ViewHolder(binding.root)
}
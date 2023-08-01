package ru.kassi.onlinekassa.presentation.mainFragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kassi.onlinekassa.data.Point
import ru.kassi.onlinekassa.databinding.ItemPointBinding

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
            kassaValue.text = point.kassa
            root.setOnClickListener {
                listener.invoke(point.id)
            }
        }
    }

    class PointsViewHolder(val binding: ItemPointBinding): RecyclerView.ViewHolder(binding.root)
}
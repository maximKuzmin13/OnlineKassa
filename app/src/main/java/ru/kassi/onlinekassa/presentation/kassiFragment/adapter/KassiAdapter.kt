package ru.kassi.onlinekassa.presentation.kassiFragment.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.recyclerview.widget.RecyclerView
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.data.Kassa
import ru.kassi.onlinekassa.databinding.ItemKassaBinding
import ru.kassi.onlinekassa.databinding.ItemKassaDoubleBinding
import ru.kassi.onlinekassa.databinding.ItemKassaSingleBinding
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class KassiAdapter(val listener: () -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data: List<Kassa> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val bindingSingle = ItemKassaBinding.inflate(inflater, parent, false)
        return KassiViewHolder(bindingSingle)
    }

    override fun getItemViewType(position: Int): Int {
        return if (data.size % 2 == 0) 1 else 2
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val point = data[position]
        with(holder as KassiViewHolder){
            with(binding) {
                titleValue.text = point.title
                adressValue.text = point.adress
                kassaValue.text = point.kassa
                try {
                    SimpleDateFormat("dd.MM.yyyy").parse(point.term)
                    term.text = binding.root.resources.getString(R.string.term, point.term)
                } catch (e: Exception) {
                    term.text = point.term
                }
                setColor(this, point.term)
                root.setOnClickListener { listener.invoke() }
            }
        }

    }

    fun setColor(binding: ItemKassaBinding, date: String) {
        var background = R.drawable.item_border_default
        var textColor = R.color.borderColor
        try {
            val date = SimpleDateFormat("dd.MM.yyyy").parse(date)
            val format = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            val currentDate = SimpleDateFormat("dd.MM.yyyy").parse(LocalDateTime.now().format(format))
            val diff: Long = currentDate.time - date.time
            val seconds = diff / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            val days = (hours / 24) * -1
            when {
                days >= 90 -> {
                    background = R.drawable.item_border_green
                    textColor = R.color.positive
                }
                days > 45 -> {
                    background = R.drawable.item_border_yellow
                    textColor = R.color.warning
                }
                days < 45 -> {
                    background = R.drawable.item_border_red
                    textColor = R.color.destructive
                }
            }
        } catch (e: Exception) {
            background = R.drawable.item_border_default
            textColor = R.color.borderColor
        }

        with(binding){
            itemBorder.setBackgroundResource(background)
            term.setTextColor(this.root.resources.getColor(textColor))
        }
    }

    class KassiViewHolder(val binding: ItemKassaBinding): RecyclerView.ViewHolder(binding.root)
//    class KassiSingleViewHolder(val binding: ItemKassaSingleBinding): RecyclerView.ViewHolder(binding.root)
//    class KassiDoubleViewHolder(val binding: ItemKassaDoubleBinding): RecyclerView.ViewHolder(binding.root)
}
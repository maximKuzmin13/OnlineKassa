package ru.kassi.onlinekassa.presentation.kassiFragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.databinding.ItemKassaBinding
import ru.kassi.onlinekassa.domain.api.kassa.Kassa
import ru.kassi.onlinekassa.domain.api.kassa.KassaData
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class KassiAdapter(val listener: () -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data: List<KassaData> = emptyList()
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
                titleValue.text = point.name
                adressValue.text = point.address
                val date = point.term?.substringBefore("T")
                val formattedDate = date?.replace("-", ".")
                val rightDate = try {
                    val localdate = LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("yyyy.MM.dd"))
                    localdate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
                } catch (e: Exception) {
                    formattedDate
                }
                term.text = binding.root.resources.getString(R.string.term, rightDate)
                serviceName.text = point.service
                setColor(this, rightDate?.replace("-", ".").toString())
                root.setOnClickListener { listener.invoke() }
            }
        }

    }

    fun setColor(binding: ItemKassaBinding, date: String) {
        var background = R.drawable.item_border_default
        var textColor = R.color.borderColor
        try {
            val dateD = SimpleDateFormat("yyyy.MM.dd").parse(date)
            val format = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            val currentDate = SimpleDateFormat("dd.MM.yyyy").parse(LocalDateTime.now().format(format))
            val diff: Long = currentDate.time - dateD.time
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
            serviceName.setTextColor(this.root.resources.getColor(textColor))
        }
    }

    class KassiViewHolder(val binding: ItemKassaBinding): RecyclerView.ViewHolder(binding.root)
}
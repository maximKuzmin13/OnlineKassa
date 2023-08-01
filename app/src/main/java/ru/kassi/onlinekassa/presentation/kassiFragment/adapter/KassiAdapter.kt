package ru.kassi.onlinekassa.presentation.kassiFragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kassi.onlinekassa.data.Kassa
import ru.kassi.onlinekassa.databinding.ItemKassaBinding
import ru.kassi.onlinekassa.databinding.ItemKassaDoubleBinding
import ru.kassi.onlinekassa.databinding.ItemKassaSingleBinding

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
                term.text = point.term
                root.setOnClickListener { listener.invoke() }
            }
        }

    }

    class KassiViewHolder(val binding: ItemKassaBinding): RecyclerView.ViewHolder(binding.root)
//    class KassiSingleViewHolder(val binding: ItemKassaSingleBinding): RecyclerView.ViewHolder(binding.root)
//    class KassiDoubleViewHolder(val binding: ItemKassaDoubleBinding): RecyclerView.ViewHolder(binding.root)
}
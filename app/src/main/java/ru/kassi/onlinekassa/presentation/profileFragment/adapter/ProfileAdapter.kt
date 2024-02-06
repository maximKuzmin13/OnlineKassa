package ru.kassi.onlinekassa.presentation.profileFragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kassi.onlinekassa.data.Profile
import ru.kassi.onlinekassa.databinding.ItemProfileBinding

class ProfileAdapter: RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    var data: List<Profile?> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProfileBinding.inflate(inflater, parent, false)

        return ProfileViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val point = data[position]
        with(holder.binding){
            title.text = point?.text?.first
            text.text = point?.text?.second
        }
    }

    class ProfileViewHolder(val binding: ItemProfileBinding): RecyclerView.ViewHolder(binding.root)
}
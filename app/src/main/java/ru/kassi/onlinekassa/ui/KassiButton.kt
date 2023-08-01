package ru.kassi.onlinekassa.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.databinding.UiKassiButtonBinding
import ru.kassi.onlinekassa.databinding.UiKassiEditTextBinding

class KassiButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    protected val binding = UiKassiButtonBinding.inflate(LayoutInflater.from(context), this, true)

    fun setText(text: Int) {
        binding.button.text = context.getText(text)
    }
    fun setText(text: String) {
        binding.button.text = text
    }

    fun enable() {
        binding.button.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_button_enabled))
        binding.button.setTextColor(resources.getColor(R.color.white))
    }

    fun disable() {
        binding.button.isEnabled = false
    }

    fun onClick(listener: () -> Unit) {
        binding.button.setOnClickListener {
            listener.invoke()
        }
    }
}
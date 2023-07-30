package ru.kassi.onlinekassa.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
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

    fun disable() {
        binding.button.isEnabled = false
    }

    fun onClick(listener: () -> Unit) {
        binding.button.setOnClickListener {
            listener.invoke()
        }
    }
}
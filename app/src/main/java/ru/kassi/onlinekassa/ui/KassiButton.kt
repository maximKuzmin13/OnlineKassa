package ru.kassi.onlinekassa.ui

import android.content.Context
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.coroutines.delay
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.databinding.UiKassiButtonBinding
import ru.kassi.onlinekassa.databinding.UiKassiEditTextBinding
import java.util.logging.Handler

open class KassiButton @JvmOverloads constructor(
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

    fun setState(isEnabled: Boolean) {
        Looper.myLooper()?.let {
            android.os.Handler(it).postDelayed({
                binding.button.isEnabled = isEnabled
            }, 50)
        }
        if (isEnabled) {
            binding.button.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_button_enabled))
            binding.button.setTextColor(resources.getColor(R.color.white))
        }
    }

    fun onClick(listener: () -> Unit) {
        binding.button.setOnClickListener {
            listener.invoke()
        }
    }
}
package ru.kassi.onlinekassa.ui

import android.content.Context
import android.content.res.Resources
import android.text.InputFilter
import android.text.InputType
import android.text.Layout.Alignment
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AutoCompleteTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import com.google.android.material.textfield.TextInputLayout
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.databinding.UiKassiEditTextBinding
import java.lang.reflect.Array.getInt

open class KassiEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val EDIT_TEXT_PADDING = 20
        private const val EDIT_TEXT_TRANSLATION_Z = 8F
    }

    val Float.px: Float
        get() = this * Resources.getSystem().displayMetrics.density

    protected val binding = UiKassiEditTextBinding.inflate(LayoutInflater.from(context), this, true)

    val editText: AutoCompleteTextView
        get() = binding.kassiEditTextInput

    protected val inputLayout: TextInputLayout
        get() = binding.kassiEditTextInputLayout

    init {
        editText.setOnFocusChangeListener { view, isFocused ->
            setShadow(isFocused)
            setAlingnment(isFocused)
        }
        editText.updatePadding(bottom = EDIT_TEXT_PADDING)
    }

    private fun setShadow(isFocused: Boolean) {
        with(inputLayout) {
            if (isFocused) {
                outlineAmbientShadowColor = context.getColor(R.color.shadowColor)
                outlineSpotShadowColor = context.getColor(R.color.shadowColor)
                translationZ = EDIT_TEXT_TRANSLATION_Z.px
            } else {
                outlineAmbientShadowColor = context.getColor(R.color.textPrimary)
                outlineSpotShadowColor = context.getColor(R.color.textPrimary)
                translationZ = EDIT_TEXT_TRANSLATION_Z.px
            }
        }
    }

    fun setMaxLength(length: Int) {
        editText.filters += InputFilter.LengthFilter(length)
    }

    private fun setAlingnment(isFocused: Boolean) {
        with(editText){
            if (text.isEmpty()) {
                textAlignment = if (isFocused ){
                    View.TEXT_ALIGNMENT_VIEW_START
                } else {
                    View.TEXT_ALIGNMENT_CENTER
                }
            }
        }
    }

    fun setText(text: String?) {
        editText.setText(text)
        editText.setSelection(editText.text?.length ?: 0)
    }

    fun setInputType(type: Int){
        editText.inputType = type
    }

    fun setImeOptions(options: Int) {
        editText.imeOptions = options
    }

    fun setHint(text: Int?) {
        editText.hint = context.getString(text ?: 0)
    }

}
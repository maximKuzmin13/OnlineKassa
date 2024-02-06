package ru.kassi.onlinekassa.presentation.pdfFragment

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import dagger.hilt.android.AndroidEntryPoint
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.databinding.FragmentPdfBinding
import ru.kassi.onlinekassa.extentions.args
import ru.kassi.onlinekassa.extentions.withArgs
import ru.kassi.onlinekassa.presentation.base.BaseFragment
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.viewBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@AndroidEntryPoint
class PdfFragment: BaseFragment<EmptyNavArgs, PdfState, PdfIntent, PdfViewModel>() {

    private val binding by viewBinding(FragmentPdfBinding::bind)

    override val viewModel: PdfViewModel by viewModels()

    companion object {
        private const val PDF_ARGS = "pdf_args"
        fun newInstance(navArgs: PdfNavArgs) = PdfFragment().withArgs {
            putParcelable(
                PDF_ARGS, navArgs
            )
        }
    }

    private val navArgs: PdfNavArgs by args(PDF_ARGS)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pdf, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dispatchIntent(PdfIntent.Num(navArgs.num))
        binding.toolbarInclude.toolbar.title = "Счёт"
        binding.toolbarInclude.toolbar.setNavigationIcon(R.drawable.ic_back)
        viewModel.chooseModel(navArgs)
        binding.toolbarInclude.toolbar.setNavigationOnClickListener {
            dispatchIntent(PdfIntent.Back)
        }
        binding.mail.setText(R.string.mail)
        binding.mail.setState(true)
    }

    fun composeEmail(mail: String, subject: String?) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:$mail?subject=$subject")
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        startActivity(intent)
    }


    private fun getQrCodeBitmap(content: String): Bitmap {
        val size = 512
        val hints = hashMapOf<EncodeHintType, String>().also { it[EncodeHintType.CHARACTER_SET] = "UTF-8" }
        val bits = QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, size, size, hints)
        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
            for (x in 0 until size) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        }
    }
    override fun renderState(viewState: PdfState) {
        super.renderState(viewState)
        with(viewState){
            val content = getCodeData(viewState)
            binding.toolbarInclude.rightIcon.setOnClickListener {
                val pickIntent = Intent(Intent.ACTION_PICK)
                pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")

                startActivityForResult(pickIntent, 111)
            }
            val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            binding.info.text = "Оплата за сервис ${navArgs.model} от $date на сумму ${viewState.typeSum?.dropLast(2)} руб."
            binding.mail.onClick {
                composeEmail(mail.orEmpty(),"Оплата сервису ${navArgs.model}")
            }
            if (!viewState.isLoading) {
                binding.qr.setImageBitmap(getQrCodeBitmap(content))
                binding.loader.isVisible = false
            } else {
                binding.loader.isVisible = true
            }
        }
    }

    fun getCodeData(viewState: PdfState): String {
        val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        return StringBuilder().apply {
            append("ST00012")
            append("|")
            append("Name=${viewState.name}")
            append("|")
            append("PersonalAcc=${viewState.personalAcc}")
            append("|")
            append("BankName=${viewState.bankName}")
            append("|")
            append("BIC=${viewState.bic}")
            append("|")
            append("CorrespAcc=${viewState.corrAcc}")
            append("|")
            append("Sum=${viewState.typeSum}")
            append("|")
            append("Purpose=Оплата по счету от ${date}. Сумма ${viewState.typeSum?.dropLast(2)}-00. Без налога (НДС).")
            append("|")
            append("PayeeINN=${viewState.inn}")
        }.toString()
    }

    private fun dispatchIntent(intent: PdfIntent) {
        viewModel.handleIntent(intent)
    }
}
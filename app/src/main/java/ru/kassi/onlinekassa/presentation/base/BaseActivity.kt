package ru.kassi.onlinekassa.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.extentions.setStatusBarColor

abstract class BaseActivity: AppCompatActivity() {

    abstract val view: Int

    private var doOnStartJob: Job? = null

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        print(throwable)
    }

    open suspend fun doOnStart() = Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        this.setStatusBarColor(R.color.white)
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()
        doOnStartJob = lifecycleScope.launch(errorHandler) {
            doOnStart()
        }
    }

    override fun onStop() {
        doOnStartJob?.cancel()
        super.onStop()
    }
}
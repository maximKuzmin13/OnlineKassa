package ru.kassi.onlinekassa.presentation.launchActivity

import android.content.Intent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.presentation.base.BaseActivity

@AndroidEntryPoint
class LaunchActivity: BaseActivity() {
    override val view: Int = R.layout.splash_screen

    private val viewModel: LaunchViewModel by viewModels()

    override suspend fun doOnStart() {
        super.doOnStart()
        viewModel.state.collect { state ->

            if (state.isInterrupted) {
                finish()
            }

            if (state.isLoading) {
                viewModel.handleIntent(LaunchIntent.FetchData)
            }

            if (state.nextDestination != null) {
                val nextActivity = Intent(this@LaunchActivity, state.nextDestination)
                startActivity(nextActivity)
                finish()
            }
        }
    }
}
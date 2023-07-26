package ru.kassi.onlinekassa.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.github.terrakok.cicerone.Back
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Forward
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.presentation.base.BaseNavigator
import ru.kassi.onlinekassa.presentation.singleActivity.SingleActivity

class MainActivityNavigator(
    private val singleActivity: SingleActivity
) : BaseNavigator(
    singleActivity.supportFragmentManager,
    singleActivity.supportFragmentManager.fragmentFactory,
    R.id.fragment_container
) {

    override fun applyCommand(command: Command) {
        when (command) {
            is Forward -> forward(command)
            is Replace -> replace(command)
            is Back -> back()
        }
    }

    private fun forward(command: Forward) {
        val fragmentScreen = command.screen as FragmentScreen
        val fragment = fragmentScreen.createFragment(ff)
        commitFragmentTransaction(fragment, fragmentScreen, fragmentScreen.screenKey)
    }

    private fun replace(command: Replace) {
        fm.popBackStack()
        val fragmentScreen = command.screen as FragmentScreen
        val fragment = fragmentScreen.createFragment(ff)
        commitFragmentTransaction(
            fragment,
            fragmentScreen,
            fragmentScreen.screenKey
        )
    }

    private fun commitFragmentTransaction(
        fragment: Fragment,
        fragmentScreen: FragmentScreen,
        backStackName: String
    ) {
        fm.commit {
            setReorderingAllowed(true)
            replace(containerId, fragment, fragmentScreen.screenKey)
            addToBackStack(backStackName)
        }
    }

    private fun back() {
        if (fm.backStackEntryCount > FIRST_INDEX_FRAGMENT_TO_BACKSTACK) {
            fm.popBackStack()
        } else {
            singleActivity.finish()
        }
    }

    companion object {
        private const val FIRST_INDEX_FRAGMENT_TO_BACKSTACK = 1
    }
}
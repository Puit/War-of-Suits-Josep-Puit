package com.nunnos.warofsuitsjoseppuit.presentation.feature.main.navigation

import android.content.Intent
import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import com.nunnos.warofsuitsjoseppuit.R
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.activity.MainActivity
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.activity.vm.MainViewModel
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.fragment.DashboardFragment
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.fragment.GameFragment
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.navigation.MainNavigation.Companion.DASHBOARD
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.navigation.MainNavigation.Companion.GAME
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.navigation.MainNavigation.Companion.OLD_GAMES
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.navigation.MainNavigation.Companion.POP_BACKSTACK
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.activity.OldGamesActivity


class MainNavigationManager() {
    companion object {
        fun goTo(
            activity: MainActivity,
            @MainNavigation navigation: Int?,
            viewModel: MainViewModel?
        ) {
            when (navigation) {
                DASHBOARD -> navigateToDashboard(activity)
                GAME -> navigateToGame(activity)
                OLD_GAMES -> navigateToOldGames(activity)
                POP_BACKSTACK -> popBackStack(activity)
                else -> throw IllegalStateException("ContactInfoNavigationManager error, navigation has not been implementad")
            }
        }

        private fun navigateToDashboard(activity: MainActivity) {
            overrideSlidingUpTransition(DashboardFragment.newInstance(), activity)
        }

        private fun navigateToGame(activity: MainActivity) {
            overrideSlidingUpTransition(GameFragment.newInstance(), activity)
        }

        private fun navigateToOldGames(activity: MainActivity) {
            val intent = Intent(activity.applicationContext, OldGamesActivity::class.java)
            activity.startActivity(intent)
        }

        private fun popBackStack(activity: MainActivity) {
            if (activity.supportFragmentManager.fragments.size >= 2) {
                val lastFragment =
                    activity.supportFragmentManager.fragments[activity.supportFragmentManager.fragments.size - 2]
                activity.supportFragmentManager.popBackStack()
                activity.supportFragmentManager.popBackStack()
                overrideSlidingUpTransition(lastFragment, activity)
            } else {
                activity.finish()
            }
        }

        private fun overrideSlidingUpTransition(fragment: Fragment, activity: MainActivity) {
            overrideSlidingTransition(fragment, activity, R.anim.slide_in_up, R.anim.slide_out_down)
        }

        private fun overrideSlidingTransition(
            fragment: Fragment,
            activity: MainActivity,
            @AnimatorRes @AnimRes enter: Int,
            @AnimatorRes @AnimRes exit: Int
        ) {
            val transaction = activity.supportFragmentManager.beginTransaction()
            val fragmentId = fragment.javaClass.simpleName

            transaction.setCustomAnimations(enter, exit, enter, exit)
                .add(fragment, fragmentId)
                .addToBackStack(fragmentId)
                .setTransition(TRANSIT_FRAGMENT_OPEN)
                .commit()
        }
    }
}
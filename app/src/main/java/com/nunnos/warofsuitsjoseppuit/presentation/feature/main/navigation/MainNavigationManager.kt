package com.nunnos.warofsuitsjoseppuit.presentation.feature.main.navigation

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
                else -> throw IllegalStateException("ContactInfoNavigationManager error, navigation has not been implementad")
            }
        }

        private fun navigateToDashboard(activity: MainActivity) {
            overrideSlidingUpTransition(DashboardFragment.newInstance(), activity)
        }

        private fun navigateToGame(activity: MainActivity) {
            overrideSlidingUpTransition(GameFragment.newInstance(), activity)
        }

        private fun overrideSlidingUpTransition(fragment: Fragment?, activity: MainActivity) {
            overrideSlidingTransition(fragment, activity, R.anim.slide_in_up, R.anim.slide_out_down)
        }

        private fun overrideSlidingTransition(
            fragment: Fragment?,
            activity: MainActivity,
            @AnimatorRes @AnimRes enter: Int,
            @AnimatorRes @AnimRes exit: Int
        ) {
            val transaction = activity.supportFragmentManager.beginTransaction()
            if (fragment != null) {
                transaction.setCustomAnimations(enter, exit, enter, exit)
                    //.replace(R.id.activity_fragment_container, fragment)
                    .add(fragment, "new Fragment")
                    .addToBackStack(null)
                    .setTransition(TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

        }
    }
}
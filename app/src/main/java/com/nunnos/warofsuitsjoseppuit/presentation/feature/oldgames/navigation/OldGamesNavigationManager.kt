package com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.navigation

import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.nunnos.warofsuitsjoseppuit.R
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.activity.OldGamesActivity
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.activity.vm.OldGamesViewModel
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.fragment.OldGameFragment
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.fragment.OldGamesDistributorFragment
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.navigation.OldGamesNavigation.Companion.DISTRIBUTOR
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.navigation.OldGamesNavigation.Companion.OLD_GAME
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.navigation.OldGamesNavigation.Companion.POP_BACKSTACK

class OldGamesNavigationManager {
    companion object {
        fun goTo(
            activity: OldGamesActivity,
            @OldGamesNavigation navigation: Int?,
            viewModel: OldGamesViewModel?
        ) {
            when (navigation) {
                DISTRIBUTOR -> navigateToDistributor(activity)
                OLD_GAME -> navigateToOldGame(activity, viewModel)
                POP_BACKSTACK -> popBackStack(activity)
                else -> throw IllegalStateException("ContactInfoNavigationManager error, navigation has not been implementad")
            }
        }

        private fun navigateToDistributor(activity: OldGamesActivity) {
            overrideSlidingUpTransition(OldGamesDistributorFragment.newInstance(), activity)
        }

        private fun navigateToOldGame(activity: OldGamesActivity, viewModel: OldGamesViewModel?) {
            overrideSlidingUpTransition(
                OldGameFragment.newInstance(viewModel!!.selectedGame),
                activity
            )
        }

        private fun popBackStack(activity: OldGamesActivity) {
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

        private fun overrideSlidingUpTransition(fragment: Fragment, activity: OldGamesActivity) {
            overrideSlidingTransition(fragment, activity, R.anim.slide_in_up, R.anim.slide_out_down)
        }

        private fun overrideSlidingTransition(
            fragment: Fragment,
            activity: OldGamesActivity,
            @AnimatorRes @AnimRes enter: Int,
            @AnimatorRes @AnimRes exit: Int
        ) {
            val transaction = activity.supportFragmentManager.beginTransaction()
            val fragmentId = fragment.javaClass.simpleName

            transaction.setCustomAnimations(enter, exit, enter, exit)
                .add(fragment, fragmentId)
                .addToBackStack(fragmentId)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }
    }
}
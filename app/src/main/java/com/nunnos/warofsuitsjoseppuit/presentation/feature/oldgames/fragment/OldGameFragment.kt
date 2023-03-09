package com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nunnos.warofsuitsjoseppuit.R
import com.nunnos.warofsuitsjoseppuit.databinding.FragmentOldGameBinding
import com.nunnos.warofsuitsjoseppuit.domain.OldGame
import com.nunnos.warofsuitsjoseppuit.presentation.components.recyclerviews.distributor.DistributorAdapter
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.activity.OldGamesActivity
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.activity.vm.OldGamesViewModel

class OldGameFragment(oldGame: OldGame) : Fragment() {

    lateinit var databinding: FragmentOldGameBinding
    lateinit var shareViewModel: OldGamesViewModel
    lateinit var oldGame: OldGame

    private lateinit var adapter: DistributorAdapter

    companion object {
        fun newInstance(oldGame: OldGame): OldGameFragment {
            val fragment = OldGameFragment(oldGame)
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    init {
        this.oldGame = oldGame
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shareViewModel = (requireActivity() as OldGamesActivity).viewModel
        databinding = DataBindingUtil.setContentView(
            requireActivity(),
            R.layout.fragment_old_game
        )
        return inflater.inflate(R.layout.fragment_old_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
    }

    private fun setView() {
        databinding.oldGameResult.text = oldGame.result
        databinding.oldGameDate.text = oldGame.date + " " + oldGame.time
        databinding.oldGameScore.setMyScore(calculateMyScore(oldGame))
        databinding.oldGameScore.setMyScore(calculateOpponentScore(oldGame))
        databinding.oldGameScore.setSuits(oldGame.suits)
    }

    private fun calculateMyScore(oldGame: OldGame): Int {
        //TODO
        return 0
    }

    private fun calculateOpponentScore(oldGame: OldGame): Int {
        //TODO
        return 2
    }
}
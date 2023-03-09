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
import com.nunnos.warofsuitsjoseppuit.presentation.components.recyclerviews.oldgamesadapter.OldGameRoundAdapter
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.activity.OldGamesActivity
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.activity.vm.OldGamesViewModel
import com.nunnos.warofsuitsjoseppuit.utils.GameHelper

class OldGameFragment(oldGame: OldGame, activity: OldGamesActivity) : Fragment() {

    lateinit var databinding: FragmentOldGameBinding
    lateinit var shareViewModel: OldGamesViewModel
    lateinit var oldGame: OldGame
    lateinit var activity: OldGamesActivity

    private lateinit var adapter: OldGameRoundAdapter

    companion object {
        fun newInstance(oldGame: OldGame, activity: OldGamesActivity): OldGameFragment {
            val fragment = OldGameFragment(oldGame, activity)
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    init {
        this.oldGame = oldGame
        this.activity = activity
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
        setMyScore()
        setOpponentScore()
        databinding.oldGameScore.setSuits(oldGame.suits)
        setUpRecyclerView()
    }

    private fun setMyScore() {
        databinding.oldGameScore.setMyScore(
            GameHelper.getMaxScore(
                GameHelper.getAllRounds(oldGame.game),
                oldGame.suits,
                true
            )
        )
    }

    private fun setOpponentScore() {
        databinding.oldGameScore.setOpponentScore(
            GameHelper.getMaxScore(
                GameHelper.getAllRounds(
                    oldGame.game
                ), oldGame.suits, false
            )
        )
    }

    private fun setUpRecyclerView() {
        adapter = OldGameRoundAdapter(
            GameHelper.getAllRounds(shareViewModel.selectedGame.game),
            shareViewModel.selectedGame.suits,
            activity
        )
        databinding.oldGameRecyclerView.adapter = adapter
        databinding.oldGameRecyclerView.setHasFixedSize(false)

    }

}
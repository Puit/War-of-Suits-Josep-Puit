package com.nunnos.warofsuitsjoseppuit.presentation.feature.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nunnos.warofsuitsjoseppuit.R
import com.nunnos.warofsuitsjoseppuit.databinding.FragmentGameBinding
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.activity.MainActivity
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.activity.vm.MainViewModel

class GameFragment() : Fragment() {
    lateinit var databinding: FragmentGameBinding
    lateinit var shareViewModel: MainViewModel

    companion object {
        fun newInstance(): GameFragment {
            val fragment: GameFragment = GameFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shareViewModel = (requireActivity() as MainActivity).viewModel
        databinding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_game)
        setListeners()
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    private fun setListeners() {
        databinding.gameOponentLot.setOnClickListener {
            if (shareViewModel.haveToOrganizeTheGame()) {
                shareViewModel.dealCards()
                shareViewModel.shuffleSuitsPriority()
                databinding.gameFeedbackText.visibility = View.GONE
            } else {
                shareViewModel.playOneRound()
                if (shareViewModel.isGameFinished()) {
                    val message: String
                    when (shareViewModel.checkIfIWonTheGame(
                        shareViewModel.myWonDeck, shareViewModel.opponentWonDeck
                    )) {
                        MainViewModel.GameResult.WIN -> message = getString(R.string.you_win)
                        MainViewModel.GameResult.LOSE -> message = getString(R.string.you_lose)
                        MainViewModel.GameResult.TIE -> message = getString(R.string.tie)
                    }
                    databinding.gameFeedbackText.visibility = View.VISIBLE
                    databinding.gameFeedbackText.text = message
                }
            }
        }
    }
}
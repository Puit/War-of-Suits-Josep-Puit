package com.nunnos.warofsuitsjoseppuit.presentation.feature.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nunnos.warofsuitsjoseppuit.R
import com.nunnos.warofsuitsjoseppuit.databinding.FragmentGameBinding
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.activity.MainActivity
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.activity.vm.MainViewModel
import com.nunnos.warofsuitsjoseppuit.utils.AlertsManager
import com.nunnos.warofsuitsjoseppuit.utils.AlertsManager.TwoButtonsAlertListener

class GameFragment : Fragment() {
    lateinit var databinding: FragmentGameBinding
    lateinit var shareViewModel: MainViewModel

    companion object {
        fun newInstance(): GameFragment {
            val fragment = GameFragment()
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
        databinding.gamePlayBtn.setOnClickListener {
            if (shareViewModel.haveToOrganizeTheGame()) {
                newGame()
            } else {
                playOneRound()
            }
        }
        databinding.gameResetBtn.setOnClickListener {
            AlertsManager.showTwoButtonsAlert(
                activity,
                object : TwoButtonsAlertListener {
                    override fun onLeftClick() {
                        newGame()
                    }

                    override fun onRightClick() {
                        //DO NOTHING
                    }
                },
                getString(R.string.restart_question),
                getString(R.string.yes),
                getString(R.string.no),
                false
            )
        }

    }

    private fun newGame() {
        shareViewModel.dealCards()
        shareViewModel.shuffleSuitsPriority()
        databinding.gameFeedbackText.visibility = INVISIBLE

        databinding.gameMyLotUnder.visibility = VISIBLE
        databinding.gameScoreBoard.visibility = VISIBLE
        databinding.gameOponentLotUnder.visibility = VISIBLE

        databinding.gameScoreBoard.setMyScore(0)
        databinding.gameScoreBoard.setOpponentScore(0)
        databinding.gameScoreBoard.setSuits(shareViewModel.suitPriority)
        databinding.gamePlayBtn.text = getString(R.string.play)
        databinding.gameResetBtn.visibility = GONE
    }

    private fun playOneRound() {
        shareViewModel.playOneRound()
        databinding.gameScoreBoard.setMyScore(shareViewModel.myWonDeck.size)
        databinding.gameScoreBoard.setOpponentScore(shareViewModel.opponentWonDeck.size)
        if (shareViewModel.isGameFinished()) {
            gameEnded()
        } else {
            databinding.gameResetBtn.visibility = VISIBLE
        }
    }

    private fun gameEnded() {
        val message: String
        when (shareViewModel.checkIfIWonTheGame(
            shareViewModel.myWonDeck, shareViewModel.opponentWonDeck
        )) {
            MainViewModel.GameResult.WIN -> message = getString(R.string.you_win)
            MainViewModel.GameResult.LOSE -> message = getString(R.string.you_lose)
            MainViewModel.GameResult.TIE -> message = getString(R.string.tie)
        }
        databinding.gameFeedbackText.visibility = VISIBLE
        databinding.gameFeedbackText.text = message
        databinding.gameMyLotUnder.visibility = GONE
        databinding.gameOponentLotUnder.visibility = GONE
        databinding.gamePlayBtn.text = getString(R.string.start)
        databinding.gameResetBtn.visibility = GONE
    }
}
package com.nunnos.warofsuitsjoseppuit.presentation.feature.main.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nunnos.warofsuitsjoseppuit.R
import com.nunnos.warofsuitsjoseppuit.data.Card
import com.nunnos.warofsuitsjoseppuit.databinding.FragmentGameBinding
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.activity.MainActivity
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.activity.vm.MainViewModel
import com.nunnos.warofsuitsjoseppuit.utils.AlertsManager
import com.nunnos.warofsuitsjoseppuit.utils.AlertsManager.TwoButtonsAlertListener
import kotlinx.coroutines.*


class GameFragment() : Fragment() {
    private val TAG = this.javaClass.simpleName
    private val ANIMATION_DURATION = 500L
    private val ANIMATION_WAITING_DURATION = 1000L
    private lateinit var databinding: FragmentGameBinding
    private lateinit var shareViewModel: MainViewModel
    private var enableToClick = true
    private var job = Job()
    private val mainScope =
        CoroutineScope(Dispatchers.Main + job) // Para lanzar animacione debe usarse el main
    private var jobIndex = 0

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
        shareViewModel =
            ViewModelProvider(requireActivity() as MainActivity)[MainViewModel::class.java]
        databinding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_game)
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        initObservers()

    }

    private fun initObservers() {
        shareViewModel.opponentWonDeck.observe(viewLifecycleOwner) {
            databinding.gameScoreBoard.setOpponentScore(it.size)
            vanishLastCardIfNeeded()
            if (it.size != 0)
                playOpponentWinAnimation()
        }
        shareViewModel.myWonDeck.observe(viewLifecycleOwner) {
            databinding.gameScoreBoard.setMyScore(it.size)
            vanishLastCardIfNeeded()
            if (it.size != 0)
                playIWinAnimation()
        }
    }

    private fun vanishLastCardIfNeeded() {
        if (shareViewModel.myDeck.size == 0) {
            databinding.gameMyLotUnder.visibility = INVISIBLE
            databinding.gameScoreBoard.visibility = INVISIBLE
        } else {
            databinding.gameMyLotUnder.visibility = VISIBLE
            databinding.gameScoreBoard.visibility = VISIBLE
        }
    }

    private fun setListeners() {
        databinding.gamePlayBtn.setOnClickListener {
            if (enableToClick) {
                jobIndex++
                if (shareViewModel.haveToOrganizeTheGame()) {
                    newGame()
                } else {
                    playOneRound()
                }
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
        jobIndex++
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
        databinding.gameResetBtn.visibility = INVISIBLE

        databinding.gameOponentProfits.visibility = INVISIBLE
        databinding.gameMyProfits.visibility = INVISIBLE
        databinding.gameScoreBoard.setRoundTitle(getRound())

        databinding.gameOponentLot.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.back
            )
        )
        databinding.gameMyLot.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.back
            )
        )
    }

    private fun getRound(): String {
        return getString(R.string.round) + " " + shareViewModel.round
    }

    private fun playOneRound() {
        shareViewModel.playOneRound()
        if (shareViewModel.isGameFinished()) {
            gameEnded()
        } else {
            databinding.gameResetBtn.visibility = VISIBLE
        }
        databinding.gameOponentProfits.visibility = VISIBLE
        databinding.gameMyProfits.visibility = VISIBLE
        databinding.gameScoreBoard.setRoundTitle(getRound())
    }

    private fun playIWinAnimation() {
        val opponentPreviouseY = databinding.gameOponentLot.y
        val myCardPreviouseX = databinding.gameMyLot.x

        databinding.gameOponentLot.setImageDrawable(getDrawableOfCard(shareViewModel.opponentDeck[0]))
        databinding.gameMyLot.setImageDrawable(getDrawableOfCard(shareViewModel.myDeck[0]))

        val fromOpponentToMyWinLotAnim = ObjectAnimator.ofFloat(
            databinding.gameOponentLot,
            "translationY",
            databinding.gameMyProfits.y - databinding.gameMyProfits.marginBottom
        ).apply {
            duration = ANIMATION_DURATION
        }
        val fromMyLotToMyWinLotAnim = ObjectAnimator.ofFloat(
            databinding.gameMyLot,
            "translationX",
            -(databinding.gameMyLot.x - databinding.gameMyProfits.x)
        ).apply {
            duration = ANIMATION_DURATION
        }

        fromOpponentToMyWinLotAnim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                databinding.gameOponentLot.y = opponentPreviouseY
                if (context != null) {
                    databinding.gameOponentLot.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.back
                        )
                    )
                }
            }
        })
        fromMyLotToMyWinLotAnim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                databinding.gameMyLot.x = myCardPreviouseX
                if (context != null) {
                    databinding.gameMyLot.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.back
                        )
                    )
                }
                enableButton(true)
            }
        })
        mainScope.launch {
            val oldJobIndex = jobIndex
            delay(ANIMATION_WAITING_DURATION)
            try {
                if (oldJobIndex == jobIndex) {
                    fromOpponentToMyWinLotAnim.start()
                    fromMyLotToMyWinLotAnim.start()
                    enableButton(false)
                }
            } catch (e: Exception) {
                Log.e(TAG, "playIWinAnimation: Something went wrong")
            }

        }
    }

    private fun playOpponentWinAnimation() {
        val opponentPreviouseX = databinding.gameOponentLot.x
        val myCardPreviouseY = databinding.gameMyLot.y

        databinding.gameOponentLot.setImageDrawable(getDrawableOfCard(shareViewModel.opponentDeck[0]))
        databinding.gameMyLot.setImageDrawable(getDrawableOfCard(shareViewModel.myDeck[0]))

        val fromMetToOpponentWinLotAnim = ObjectAnimator.ofFloat(
            databinding.gameOponentLot,
            "translationX",
            (databinding.gameMyLot.x - databinding.gameMyProfits.x)
        ).apply {
            duration = ANIMATION_DURATION
        }
        val fromOpponentToOpponentWinLotAnim = ObjectAnimator.ofFloat(
            databinding.gameMyLot,
            "translationY",
            -(databinding.gameMyProfits.y - databinding.gameOponentLot.y)
        ).apply {
            duration = ANIMATION_DURATION
        }

        fromMetToOpponentWinLotAnim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                databinding.gameOponentLot.x = opponentPreviouseX
                if (context != null) {
                    databinding.gameOponentLot.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.back
                        )
                    )
                }
                enableButton(true)
            }
        })
        fromOpponentToOpponentWinLotAnim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                databinding.gameMyLot.y = myCardPreviouseY
                if (context != null) {
                    databinding.gameMyLot.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.back
                        )
                    )
                }
                enableButton(true)
            }
        })
        mainScope.launch {
            val oldJobIndex = jobIndex
            delay(ANIMATION_WAITING_DURATION)
            try {
                if (oldJobIndex == jobIndex) {
                    fromMetToOpponentWinLotAnim.start()
                    fromOpponentToOpponentWinLotAnim.start()
                    enableButton(false)
                }
            } catch (e: Exception) {
                Log.e(TAG, "playIWinAnimation: Something went wrong")
            }
        }
    }

    private fun gameEnded() {
        val message: String
        when (shareViewModel.checkIfIWonTheGame(
            shareViewModel.myWonDeck.value!!, shareViewModel.opponentWonDeck.value!!
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
        databinding.gameResetBtn.visibility = INVISIBLE

        shareViewModel.addGame()
    }

    private fun getDrawableOfCard(card: Card): Drawable? {
        val type: String
        val number: String
        when (card.type) {
            Card.Type.CLUBS -> type = "club_"
            Card.Type.DIAMONTS -> type = "diamond_"
            Card.Type.HEARTS -> type = "hearts_"
            Card.Type.SPADES -> type = "spades_"
        }
        when (card.number) {
            Card.Number.TWO -> number = "2"
            Card.Number.THREE -> number = "3"
            Card.Number.FOUR -> number = "4"
            Card.Number.FIVE -> number = "5"
            Card.Number.SIX -> number = "6"
            Card.Number.SEVEN -> number = "7"
            Card.Number.EIGHT -> number = "8"
            Card.Number.NINE -> number = "9"
            Card.Number.TEN -> number = "10"
            Card.Number.J -> number = "j"
            Card.Number.Q -> number = "q"
            Card.Number.K -> number = "k"
            Card.Number.ACE -> number = "ace"
        }
        return requireActivity().getDrawable(
            this.resources.getIdentifier(type + number, "drawable", requireActivity().packageName)
        )
    }

    private fun enableButton(bool: Boolean) {
        enableToClick = bool
        databinding.gamePlayBtn.isEnabled = bool
    }

    override fun onDestroy() {
        newGame()
        shareViewModel.clearData()
        super.onDestroy()
    }
}
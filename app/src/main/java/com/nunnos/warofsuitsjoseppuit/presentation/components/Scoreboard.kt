package com.nunnos.warofsuitsjoseppuit.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.nunnos.warofsuitsjoseppuit.R
import com.nunnos.warofsuitsjoseppuit.data.Card
import com.nunnos.warofsuitsjoseppuit.databinding.ComponentScoreboardBinding

class Scoreboard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?
) :
    ConstraintLayout(context, attrs) {

    private lateinit var dataBinding: ComponentScoreboardBinding

    init {
        bindView()
        parseAttributes(attrs)
    }

    private fun bindView() {
        dataBinding = ComponentScoreboardBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) {
            return
        }
        val attributes =
            context.obtainStyledAttributes(attrs, R.styleable.Scoreboard, 0, 0)
        val leftTitle = attributes.getText(R.styleable.Scoreboard_SC_left_title)
        val roundTitle = attributes.getText(R.styleable.Scoreboard_SC_left_title)
        val rightTitle = attributes.getText(R.styleable.Scoreboard_SC_right_title)
        val leftScore = attributes.getText(R.styleable.Scoreboard_SC_left_score)
        val rightScore = attributes.getText(R.styleable.Scoreboard_SC_right_score)
        val suit1 = attributes.getInt(R.styleable.Scoreboard_SC_suit1_drawable, -1)
        val suit2 = attributes.getInt(R.styleable.Scoreboard_SC_suit2_drawable, -1)
        val suit3 = attributes.getInt(R.styleable.Scoreboard_SC_suit3_drawable, -1)
        val suit4 = attributes.getInt(R.styleable.Scoreboard_SC_suit4_drawable, -1)

        if (roundTitle != null) dataBinding.scoreboardRoundTitle.text = roundTitle
        if (leftTitle != null) dataBinding.scoreboardOpponentTitle.text = leftTitle
        if (rightTitle != null) dataBinding.scoreboardMyTitle.text = rightTitle
        if (leftScore != null) dataBinding.scoreboardOpponentScore.text = leftScore
        if (rightScore != null) dataBinding.scoreboardMyScore.text = rightScore

        if (suit1 != -1 && suit2 != -1 && suit3 != -1 && suit4 != -1) {
            setSuits(
                Card.Type.values().get(suit1),
                Card.Type.values().get(suit2),
                Card.Type.values().get(suit3),
                Card.Type.values().get(suit4)
            )

        }
    }

    fun setSuits(suit1: Card.Type, suit2: Card.Type, suit3: Card.Type, suit4: Card.Type) {
        dataBinding.scoreboardSuits1.setImageResource(getResourceFromCardType(suit1))
        dataBinding.scoreboardSuits2.setImageResource(getResourceFromCardType(suit2))
        dataBinding.scoreboardSuits3.setImageResource(getResourceFromCardType(suit3))
        dataBinding.scoreboardSuits4.setImageResource(getResourceFromCardType(suit4))
    }

    fun setSuits(suits: ArrayList<Card.Type>) {
        if (suits != null && suits.size == 4) {
            setSuits(suits[0], suits[1], suits[2], suits[3])
        }
    }

    private fun getResourceFromCardType(type: Card.Type): Int {
        when (type) {
            Card.Type.CLUBS -> return R.drawable.clubs
            Card.Type.DIAMONTS -> return R.drawable.diamonds
            Card.Type.HEARTS -> return R.drawable.hearts
            Card.Type.SPADES -> return R.drawable.spades
        }
    }

    fun setOpponentScore(score: Int) {
        dataBinding.scoreboardOpponentScore.text = score.toString()
    }

    fun setMyScore(score: Int) {
        dataBinding.scoreboardMyScore.text = score.toString()
    }

    fun setRoundTitle(text: String) {
        dataBinding.scoreboardRoundTitle.text = text
    }
}
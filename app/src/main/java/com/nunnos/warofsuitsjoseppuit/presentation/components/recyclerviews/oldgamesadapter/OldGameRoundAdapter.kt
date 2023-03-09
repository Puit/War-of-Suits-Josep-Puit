package com.nunnos.warofsuitsjoseppuit.presentation.components.recyclerviews.oldgamesadapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.nunnos.warofsuitsjoseppuit.R
import com.nunnos.warofsuitsjoseppuit.data.Card
import com.nunnos.warofsuitsjoseppuit.domain.Round
import com.nunnos.warofsuitsjoseppuit.utils.GameHelper

class OldGameRoundAdapter(
    items: List<Round>,
    suitPriority: ArrayList<Card.Type>,
    activity: AppCompatActivity
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items = listOf<Round>()
    var suitPriority = ArrayList<Card.Type>()
    var activity: AppCompatActivity
    lateinit var context: Context

    init {
        this.items = items
        this.activity = activity
        this.suitPriority = suitPriority
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    /**
     * BASE FUNCTIONS
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context //Context del recyclerview
        val layoutInflater = LayoutInflater.from(context)
        val view: View =
            layoutInflater.inflate(R.layout.component_old_game_round, parent, false)
        val viewHolder = OldGameAdapterViewHolder(view)
        viewHolder.setIsRecyclable(false)
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
      /*  var myDeck = getMyDeck()
        (holder as DistributorAdapterViewHolder).bind(
            items[position],
            calculateMyScore(myDeck, opponentDeck, suitPriority, position),
            calculateOpponentScore(myDeck, opponentDeck, suitPriority, position),
            activity
        )*/
        (holder as OldGameAdapterViewHolder).bind(
            items[position],
            0,
            0,
            activity
        )
    }

    private fun calculateMyScore(
        myDeck: List<Round>,
        opponentDeck: List<Round>,
        suitPriority: ArrayList<Card.Type>,
        position: Int,

        ): Int {
        var myScore = 0
        for (index in 0..position) {
            if (GameHelper.checkIfIWonTheRound(
                    myDeck[index].myCard,
                    opponentDeck[index].oppponentCard,
                    suitPriority
                )
            ) {
                myScore += 2
            }
        }
        return myScore
    }

    private fun calculateOpponentScore(
        myDeck: List<Round>,
        opponentDeck: List<Round>,
        suitPriority: ArrayList<Card.Type>,
        position: Int,
    ): Int {
        var myScore = 0
        for (index in 0..position) {
            if (!GameHelper.checkIfIWonTheRound(
                    myDeck[index].myCard,
                    opponentDeck[index].oppponentCard,
                    suitPriority
                )
            ) {
                myScore += 2
            }
        }
        return myScore
    }

    override fun getItemCount(): Int {
        return this.items.size
    }

    /**
     * CLASS VIEWHOLDER
     */
    class OldGameAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private lateinit var round: Round
        private lateinit var myCard: ImageView
        private lateinit var opponentCard: ImageView
        private lateinit var myScore: TextView
        private lateinit var opponentScore: TextView
        private lateinit var activity: AppCompatActivity
        private var isWon = false

        init {
            initView(itemView)
            itemView.setOnClickListener(this)
        }

        private fun initView(itemView: View) {
            myCard = itemView.findViewById(R.id.old_game_round_opponent_card_image)
            opponentCard = itemView.findViewById(R.id.old_game_round_my_card_image)
            myScore = itemView.findViewById(R.id.old_game_round_my_score)
            opponentScore = itemView.findViewById(R.id.old_game_round_opponent_score)
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(round: Round, myScore: Int, opponentScore: Int, activity: AppCompatActivity) {
            this.round = round
            isWon = round.isWon
            this.myScore.text = myScore.toString()
            this.opponentScore.text = opponentScore.toString()
            this.activity = activity
            setImages(round)
        }

        private fun setImages(round: Round) {
            myCard.setImageDrawable(getDrawableOfCard(round.myCard))
            opponentCard.setImageDrawable(getDrawableOfCard(round.oppponentCard))
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
            return activity.getDrawable(
                activity.resources.getIdentifier(type + number, "drawable", activity.packageName)
            )
        }

        override fun onClick(v: View) {
            //TODO
        }
    }
}

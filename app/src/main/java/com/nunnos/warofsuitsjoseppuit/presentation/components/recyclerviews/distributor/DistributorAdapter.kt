package com.nunnos.warofsuitsjoseppuit.presentation.components.recyclerviews.distributor

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nunnos.warofsuitsjoseppuit.R
import com.nunnos.warofsuitsjoseppuit.data.Card
import com.nunnos.warofsuitsjoseppuit.domain.OldGame

class DistributorAdapter(items: List<OldGame>, listener: CustomItemClick) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items = listOf<OldGame>()
    lateinit var context: Context
    var listener: CustomItemClick? = null

    init {
        this.items = items
        this.listener = listener
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
            layoutInflater.inflate(R.layout.component_old_game_distributor, parent, false)
        val viewHolder = DistributorAdapterViewHolder(view)
        viewHolder.setListener(listener)
        viewHolder.setIsRecyclable(false)
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DistributorAdapterViewHolder).bind(items[position])
    }

    override fun getItemCount(): Int {
        return this.items.size
    }

    interface CustomItemClick {
        fun onItemClick(game: OldGame)
    }

    /**
     * CLASS VIEWHOLDER
     */
    class DistributorAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private lateinit var game: OldGame
        private lateinit var suit1: ImageView
        private lateinit var suit2: ImageView
        private lateinit var suit3: ImageView
        private lateinit var suit4: ImageView
        private lateinit var date: TextView
        private lateinit var time: TextView
        private lateinit var won: TextView
        private var listener: CustomItemClick? = null

        init {
            initView(itemView)
            itemView.setOnClickListener(this)
        }

        private fun initView(itemView: View) {
            suit1 = itemView.findViewById(R.id.old_game_distributor_suit_1)
            suit2 = itemView.findViewById(R.id.old_game_distributor_suit_2)
            suit3 = itemView.findViewById(R.id.old_game_distributor_suit_3)
            suit4 = itemView.findViewById(R.id.old_game_distributor_suit_4)
            won = itemView.findViewById(R.id.old_game_distributor_who_won)
            date = itemView.findViewById(R.id.old_game_distributor_date)
            time = itemView.findViewById(R.id.old_game_distributor_time)
        }

        fun setListener(listener: CustomItemClick?) {
            this.listener = listener
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(game: OldGame) {
            this.game = game
            setImages(game)
            won.text = game.result
            date.text = game.date
            time.text = game.time
        }

        private fun setImages(game: OldGame) {
            if (game.suits.size == 4) {
                suit1.setImageResource(getDrawable(game.suits[0]))
                suit2.setImageResource(getDrawable(game.suits[1]))
                suit3.setImageResource(getDrawable(game.suits[2]))
                suit4.setImageResource(getDrawable(game.suits[3]))
            }
        }

        private fun getDrawable(type: Card.Type): Int {
            when (type) {
                Card.Type.SPADES -> return R.drawable.spades
                Card.Type.HEARTS -> return R.drawable.hearts
                Card.Type.CLUBS -> return R.drawable.clubs
                Card.Type.DIAMONTS -> return R.drawable.diamonds
            }
        }

        override fun onClick(v: View) {
            listener?.onItemClick(game)
        }
    }
}

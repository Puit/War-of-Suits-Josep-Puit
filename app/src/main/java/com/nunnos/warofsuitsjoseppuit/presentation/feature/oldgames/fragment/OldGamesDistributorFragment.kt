package com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nunnos.warofsuitsjoseppuit.R
import com.nunnos.warofsuitsjoseppuit.data.Card
import com.nunnos.warofsuitsjoseppuit.databinding.FragmentOldGamesDistributorBinding
import com.nunnos.warofsuitsjoseppuit.domain.OldGame
import com.nunnos.warofsuitsjoseppuit.presentation.components.recyclerviews.distributor.DistributorAdapter
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.activity.OldGamesActivity
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.activity.vm.OldGamesViewModel

class OldGamesDistributorFragment : Fragment(), DistributorAdapter.CustomItemClick {
    lateinit var databinding: FragmentOldGamesDistributorBinding
    lateinit var shareViewModel: OldGamesViewModel

    private lateinit var adapter: DistributorAdapter

    companion object {
        fun newInstance(): OldGamesDistributorFragment {
            val fragment = OldGamesDistributorFragment()
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
        shareViewModel = (requireActivity() as OldGamesActivity).viewModel
        databinding = DataBindingUtil.setContentView(
            requireActivity(),
            R.layout.fragment_old_games_distributor
        )
        return inflater.inflate(R.layout.fragment_old_games_distributor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        //TODO borrar de aqui y poner en observables

        //Mock----------------
        val suits = ArrayList<Card.Type>()
        suits.add(Card.Type.SPADES)
        suits.add(Card.Type.DIAMONTS)
        suits.add(Card.Type.HEARTS)
        suits.add(Card.Type.CLUBS)
        val oldGame1 = OldGame("You win", suits, "20/02/2023", "10:20", "")
        val oldGame2 = OldGame("You lose", suits, "05/05/2025", "15:25", "")
        val oldGameList = listOf<OldGame>(oldGame1, oldGame2)
        //--------------------
        adapter = DistributorAdapter(oldGameList, this)
        databinding.oldGamesRecyclerView.adapter = adapter
        databinding.oldGamesRecyclerView.setHasFixedSize(false)
    }

    private fun initObservers() {
        //TODO("Not yet implemented")
    }

    override fun onItemClick(game: OldGame) {
        shareViewModel.selectedGame = game
        shareViewModel.navigateToOldGame()
    }
}
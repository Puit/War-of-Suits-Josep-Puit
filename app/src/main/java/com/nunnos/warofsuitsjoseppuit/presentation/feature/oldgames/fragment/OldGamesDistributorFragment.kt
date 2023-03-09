package com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nunnos.warofsuitsjoseppuit.R
import com.nunnos.warofsuitsjoseppuit.data.oldgame.OldGameEntity
import com.nunnos.warofsuitsjoseppuit.databinding.FragmentOldGamesDistributorBinding
import com.nunnos.warofsuitsjoseppuit.domain.OldGame
import com.nunnos.warofsuitsjoseppuit.domain.mapper.OldGameMapper
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
    }

    private fun initObservers() {
        shareViewModel.readAllData.observe(activity as OldGamesActivity) { gameList ->
            oldGamesReceived(gameList)
        }
    }

    private fun oldGamesReceived(gameList: List<OldGameEntity>) {
        adapter = DistributorAdapter(OldGameMapper.mapEntityList(gameList), this)
        databinding.oldGamesRecyclerView.adapter = adapter
        databinding.oldGamesRecyclerView.setHasFixedSize(false)
    }

    override fun onItemClick(game: OldGame) {
        shareViewModel.selectedGame = game
        shareViewModel.navigateToOldGame()
    }
}
package com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nunnos.warofsuitsjoseppuit.R
import com.nunnos.warofsuitsjoseppuit.databinding.FragmentOldGamesDistributorBinding
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.activity.OldGamesActivity
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.activity.vm.OldGamesViewModel

class OldGamesDistributorFragment : Fragment() {
    lateinit var databinding: FragmentOldGamesDistributorBinding
    lateinit var shareViewModel: OldGamesViewModel

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
}
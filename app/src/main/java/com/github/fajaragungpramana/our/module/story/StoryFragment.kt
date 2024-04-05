package com.github.fajaragungpramana.our.module.story

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.fajaragungpramana.our.common.app.AppFragment
import com.github.fajaragungpramana.our.common.contract.AppState
import com.github.fajaragungpramana.our.core.data.remote.story.request.GetStoryRequest
import com.github.fajaragungpramana.our.databinding.FragmentStoryBinding
import com.github.fajaragungpramana.our.module.adapter.LoadStateAdapter
import com.github.fajaragungpramana.our.module.adapter.StoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StoryFragment : AppFragment<FragmentStoryBinding>(), AppState {

    private val viewModel: StoryViewModel by viewModels()

    private lateinit var storyAdapter: StoryAdapter

    override fun onViewBinding(container: ViewGroup?): FragmentStoryBinding =
        FragmentStoryBinding.inflate(layoutInflater)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        viewModel.setEvent(StoryEvent.GetListStory(GetStoryRequest(page = 1, perPage = 15)))

        initAttribute()
        initView()
    }

    override fun onStateObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest {

                when (it) {
                    is StoryState.OnStoryData -> storyAdapter.submitData(it.pagingData)
                }

            }
        }
    }

    private fun initAttribute() {
        storyAdapter = StoryAdapter()
    }

    private fun initView() {
        viewBinding.apply {
            rvStory.layoutManager = LinearLayoutManager(requireActivity())
            rvStory.adapter = storyAdapter.withLoadStateFooter(LoadStateAdapter())
        }
    }

}
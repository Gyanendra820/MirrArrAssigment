package com.example.fragment

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.target.Target
import com.example.mirrarrassigment.R
import com.example.mirrarrassigment.base.BaseFragment
import com.example.mirrarrassigment.databinding.FragmentHomeBinding
import com.example.mirrarrassigment.di.ApiModule
import com.example.mirrarrassigment.response.NasaResponse
import com.example.mirrarrassigment.utils.CommonHelper.hide
import com.example.mirrarrassigment.utils.CommonHelper.show
import com.example.mirrarrassigment.utils.NetworkHelper.isNetworkAvailable
import com.example.mirrarrassigment.utils.Resource
import com.example.mirrarrassigment.viewmodel.NasaViewModel
import dagger.hilt.android.AndroidEntryPoint

/*
created by:-Gyanendra Singh
Created on:-10/09/2023 12:37 PM
* */

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private var player: ExoPlayer? = null
    lateinit var binding: FragmentHomeBinding
    private val authViewModel: NasaViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        listener()
    }

    private fun listener() {
        //for down refresh functionality
        binding.swipe.isRefreshing = false
        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = true
            binding.parentCl.hide()
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                observer()
                binding.parentCl.show()
                binding.swipe.isRefreshing = false
            }, 3000)

        }
    }

//APi call
    private fun observer() {
        if (requireContext().isNetworkAvailable()) {
            authViewModel.getImageOfNasa(
                ApiModule.apiKey()
            ).observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Success -> {
                        binding.progressBar.hide()
                        afterSuccess(it.data)
                        binding.progressBarImage.show()
                        binding.parentCv.show()
                    }
                    is Resource.Error -> {
                        binding.progressBar.hide()
                        toastMsg(getString(R.string.error_msg))
                    }
                    is Resource.Loading -> {
                        binding.progressBar.show()
                    }
                }

            }
        } else {
            toastMsg(getString(R.string.network_error))
        }

    }

    private fun afterSuccess(data: NasaResponse?) {

        //Set the condition for image and video
        if (data?.media_type == getString(R.string.image)) {
            binding.progressBarImage.show()
            glideFunctionality(data.url)
        } else if (data?.media_type == getString(R.string.video)) {
            binding.playerView.show()
            binding.nasaImage.hide()
            binding.progressBarImage.hide()
            videPlayerFunctionality()
        }

        //Setting the data for image
        binding.titleTv.text = data?.title
        binding.dateTv.text = "(Posted On:-${data?.date})"
        binding.descriptionTv.text = data?.explanation
    }

    //This function is for loading the image if we get media type image
    private fun glideFunctionality(url: String?) {
// Set a listener to hide the ProgressBar when the image is loaded
        Glide.with(this).load(url)
            .listener(object : com.bumptech.glide.request.RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    // Handle load failure if needed
                    binding.progressBarImage.hide()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    // Image loaded successfully, hide the ProgressBar
                    binding.progressBarImage.hide()
                    return false
                }
            }).into(binding.nasaImage)
    }

    //This function is for playing the video if we get media type video
    private fun videPlayerFunctionality() {
        val uri =
            Uri.parse("https://static.videezy.com/system/resources/previews/000/002/231/original/5226496.mp4")
        val videoPlayer = binding.playerView
        player = ExoPlayer.Builder(requireContext()).build()
        videoPlayer.player = player
        val mediaItem: MediaItem = MediaItem.fromUri(uri)
        player!!.setMediaItem(mediaItem)
        player!!.prepare()
        player!!.playWhenReady = true

    }

    override fun onStop() {
        super.onStop()
        player!!.playWhenReady = false
        player!!.release()
        player = null
    }
}


package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


class Source : Fragment() {
    lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled", "NewApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_privacy, container, false)

        retainInstance=true


        webView = view.findViewById(R.id.web)


        var swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipe)

        webView.apply {

            loadUrl("https://trainings.internshala.com/android-training/")
            settings.javaScriptEnabled = true
            webView.settings.setSupportZoom(true)
            webView.settings.forceDark

        }
        // this will load the url of the website


        // this will enable the javascript settings
        swipe.setOnRefreshListener {
            webView.reload()
            swipe.isRefreshing = false
        }

        return view

    }







}
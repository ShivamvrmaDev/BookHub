package com.example.myapplication.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.R


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
webView.webViewClient= WebViewClient()
        webView.apply {

            loadUrl("https://trainings.internshala.com/android-training/")
            settings.javaScriptEnabled = true
            webView.settings.setSupportZoom(true)
            webView.settings.forceDark

        }

        swipe.setOnRefreshListener {
            webView.reload()
            swipe.isRefreshing = false
        }

        return view

    }

}
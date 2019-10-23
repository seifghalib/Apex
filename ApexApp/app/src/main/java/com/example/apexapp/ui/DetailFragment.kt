package com.example.apexapp.ui


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

import com.example.apexapp.R

private const val WEB_URL = "webViewUrl"

class DetailFragment : Fragment() {

    private var mWebViewUrl: String? = null
    private var mWebView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mWebViewUrl = it.getString(WEB_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mWebView = view.findViewById(R.id.webview)

        mWebView?.apply {
            requestFocus()
            webViewClient = WebViewClient()
            loadUrl(mWebViewUrl)
            settings.javaScriptEnabled = true
        }
    }

    fun onButtonPressed(): Boolean {
        mWebView?.let {
            if (it.canGoBack()) {
                it.goBack()
            } else return@let false
        }
        return false
    }

    companion object {
        @JvmStatic
        fun newInstance(url: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(WEB_URL, url)
                }
            }
    }
}

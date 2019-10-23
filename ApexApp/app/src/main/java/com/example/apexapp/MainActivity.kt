package com.example.apexapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apexapp.apidata.Article
import com.example.apexapp.dagger.AppComponent
import com.example.apexapp.ui.ArticleAdapter
import com.example.apexapp.ui.DetailFragment
import com.example.apexapp.viewmodels.MainActivityViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ArticleAdapter.OnItemClickListener {

    private lateinit var mFragmentManager: FragmentManager
    private lateinit var mRecyclerView: RecyclerView
    private var appComponent: AppComponent? = null
    private var mDetailFragment: DetailFragment? = null

    @Inject
    lateinit var mFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.run {
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        appComponent = (application as NewsApp).appComponent
        appComponent!!.inject(this)

        mRecyclerView = findViewById(R.id.recycler_view)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        val mActivityViewModel =
            ViewModelProviders.of(this, mFactory).get(MainActivityViewModel::class.java)

        mActivityViewModel.listMutableLiveData?.observe(
            this,
            Observer { list -> prepareAdapter(list) })
    }

    private fun prepareAdapter(list: List<Article>?) {
        list?.run {
            val adapter = ArticleAdapter(this@MainActivity, this)
            adapter.setOnItemClickListener(this@MainActivity)
            mRecyclerView.adapter = adapter
        }
    }

    override fun onItemClick(url: String) {

        mDetailFragment = DetailFragment.newInstance(url)
        mFragmentManager = supportFragmentManager
        val fragmentTransaction = mFragmentManager.beginTransaction()

        fragmentTransaction.setCustomAnimations(
            R.anim.enter_from_right, R.anim.exit_to_right,
            R.anim.enter_from_right, R.anim.exit_to_right
        )

        fragmentTransaction
            .addToBackStack("WEBVIEW")
            .replace(R.id.container, mDetailFragment!!)
            .commit()
    }

    override fun onBackPressed() {

        val fragment = mFragmentManager.findFragmentById(R.id.container)
        if (fragment != null && mDetailFragment != null) {
            if (!mDetailFragment!!.onButtonPressed()) super.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}




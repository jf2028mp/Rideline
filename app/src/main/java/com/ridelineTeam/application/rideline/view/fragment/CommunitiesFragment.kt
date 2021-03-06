package com.ridelineTeam.application.rideline.view.fragment


import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import com.ridelineTeam.application.rideline.R
import com.ridelineTeam.application.rideline.adapter.CommunityAdapter
import com.ridelineTeam.application.rideline.model.Community
import com.ridelineTeam.application.rideline.util.files.COMMUNITIES
import com.ridelineTeam.application.rideline.util.files.NAME
import com.ridelineTeam.application.rideline.util.helpers.FragmentHelper
import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class CommunitiesFragment : Fragment() {
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: CommunityAdapter.CommunityAdapterRecycler
    private lateinit var fab: FloatingActionButton
    private lateinit var arrayCommunitiesIds: ArrayList<String>
    private lateinit var arrayCommunities: ArrayList<Community>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView:View=inflater.inflate(R.layout.fragment_communities, container, false)
        database = FirebaseDatabase.getInstance()
        databaseReference = database.reference.child(COMMUNITIES)
        recycler = rootView.findViewById(R.id.communitiesRecycler)
        arrayCommunitiesIds = ArrayList()
        arrayCommunities= ArrayList()
        fab = rootView.findViewById(R.id.fabCreateCommunity)

        fab.setOnClickListener({
            FragmentHelper.changeFragment(CreateCommunityFragment(), this.fragmentManager!!)
        })
        return rootView
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar!!.title=getString(R.string.communityList)
    }
    override fun onStart() {
        super.onStart()
        loadCommunities()
    }
    private fun loadCommunities(){
        val query:Query =databaseReference.orderByChild(NAME)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler.layoutManager = linearLayoutManager
        adapter = CommunityAdapter.CommunityAdapterRecycler(context, databaseReference, activity,query)
        recycler.adapter = adapter


    }

}// Required empty public constructor

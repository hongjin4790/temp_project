package com.example.gdsc_project.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_project.R
import com.example.gdsc_project.adapter.FieldAdapter
import com.example.gdsc_project.adapter.policy
import com.example.gdsc_project.databinding.FragmentFieldBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.item.view.*

class FieldFragment : Fragment(){
    private var _binding: FragmentFieldBinding? = null
    var firestore: FirebaseFirestore? = null
    private lateinit var recyclerView: RecyclerView
    private val binding get() = _binding!!
    private val args by navArgs<FieldFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFieldBinding.inflate(inflater, container, false)
        return binding!!.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firestore = FirebaseFirestore.getInstance()
        recyclerView = binding.recyclerview
        val po: ArrayList<policy> = arrayListOf()

        val result = args.fieldType // 내가 선택한 지원분야 변수 받아오는 것
        val location = args.location // 내가 선택했던 지역
        val age = args.age  // 내가 썼던 나이


        firestore?.collection("po")?.whereEqualTo("지원분야", result)?.whereEqualTo("지역",location)?.get()
            ?.addOnCompleteListener {
                if (it.isSuccessful) {
                    for (snapshot in it.result!!.documents) {
                        val item = snapshot.toObject(policy::class.java)
                        po.add(item!!)
                    }
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }



        recyclerView.adapter = FieldAdapter(po)
        recyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

    }

}
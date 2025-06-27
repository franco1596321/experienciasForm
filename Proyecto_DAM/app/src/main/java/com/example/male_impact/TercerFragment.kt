package com.example.male_impact

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TercerFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tercer, container, false)
        val btnVerMasBarber1 = view.findViewById<Button>(R.id.btnVerMasBarber1)
        btnVerMasBarber1.setOnClickListener {
            val intent = Intent(requireContext(), ver_mas_barber::class.java)
            startActivity(intent)
        }
        val btnVerMasBarber2 = view.findViewById<Button>(R.id.btnVerMasBarber2)
        btnVerMasBarber2.setOnClickListener {
            val intent = Intent(requireContext(), ver_mas_barber2::class.java)
            startActivity(intent)
        }
        val btnVerMasBarber3 = view.findViewById<Button>(R.id.btnVerMasBarber3)
        btnVerMasBarber3.setOnClickListener {
            val intent = Intent(requireContext(), ver_mas_barber3::class.java)
            startActivity(intent)
        }
        return view
    }
    companion object {


        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TercerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
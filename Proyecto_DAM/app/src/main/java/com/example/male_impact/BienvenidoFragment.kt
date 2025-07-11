package com.example.male_impact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class BienvenidoFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        firebaseAuth = FirebaseAuth.getInstance()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bienvenido, container, false)
        val fadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        view.findViewById<ImageView>(R.id.img_logo)?.startAnimation(fadeIn)
        view.findViewById<ImageView>(R.id.img_icon_barber)?.startAnimation(fadeIn)
        return view
    }
    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BienvenidoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

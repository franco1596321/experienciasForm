package com.example.male_impact

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TiendasFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_tiendas, container, false)

        val btnVerMasTienda = view.findViewById<Button>(R.id.btnVerMasTienda)
        btnVerMasTienda.setOnClickListener {
            val intent = Intent(requireContext(), ver_mas_tienda1::class.java)
            startActivity(intent)
        }

        val btnVerMasTienda2 = view.findViewById<Button>(R.id.btnVerMasTienda2)
        btnVerMasTienda2.setOnClickListener {
            val intent = Intent(requireContext(), ver_mas_tienda2::class.java)
            startActivity(intent)
        }

        val btnVerMasTienda3 = view.findViewById<Button>(R.id.btnVerMasTienda3)
        btnVerMasTienda3.setOnClickListener {
            val intent = Intent(requireContext(), ver_mas_tienda3::class.java)
            startActivity(intent)
        }

        val btnVerMasTienda4 = view.findViewById<Button>(R.id.btnVerMasTienda4)
        btnVerMasTienda4.setOnClickListener {
            val intent = Intent(requireContext(), ver_mas_tienda4::class.java)
            startActivity(intent)
        }

        val btnVerMasTienda5 = view.findViewById<Button>(R.id.btnVerMasTienda5)
        btnVerMasTienda5.setOnClickListener {
            val intent = Intent(requireContext(), ver_mas_tienda5::class.java)
            startActivity(intent)
        }


        val btnLogout = view.findViewById<Button>(R.id.btn_logout)
        btnLogout.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(requireContext(), Login_Activity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TiendasFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

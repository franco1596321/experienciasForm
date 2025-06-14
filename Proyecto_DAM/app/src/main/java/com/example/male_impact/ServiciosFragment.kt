package com.example.male_impact
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ServiciosFragment : Fragment() {
    private lateinit var recycler_servicios: RecyclerView
    private lateinit var adaptador_servicios: AdaptadorServicios

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
        // Inflamos el layout para el fragmento
        val view = inflater.inflate(R.layout.fragment_servicios, container, false)

        // Encuentra el botón y configura el click listener para el ver mas
        val btnVerMasBarber1 = view.findViewById<Button>(R.id.btReservarJ)
        btnVerMasBarber1.setOnClickListener {
            val intent = Intent(requireContext(), Cita::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_servicios = view.findViewById(R.id.recyclerviewlistaTienda)
        llenar_data()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ServiciosFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun llenar_data() {
        val listarServicios = listOf(
            EntidadServicios(R.drawable.uno, "Asesoría en Estilo Personal",
                "Ayuda al cliente a definir y desarrollar un estilo propio que refleje su personalidad y estilo de vida."),
            EntidadServicios(R.drawable.dos, "Análisis de Color",
                "Realiza un estudio para identificar los colores que mejor favorecen al cliente según su tono de piel, ojos y cabello."),
            EntidadServicios(R.drawable.tres, "Organización y Renovación de Guardarropa",
                "Revisa y clasifica las prendas del cliente para optimizar su guardarropa."),
            EntidadServicios(R.drawable.cuatro, "Asesoría de Etiqueta y Protocolo",
                "Enseña normas de comportamiento y etiqueta para eventos formales, reuniones de negocios o situaciones sociales."),
            EntidadServicios(R.drawable.cinco, "Personal Shopping",
                "Acompaña al cliente en un recorrido de compras personalizado para elegir prendas y accesorios que complementen su estilo."),
            EntidadServicios(R.drawable.seis, "Asesoría en Cuidado Personal y Grooming",
                "Ofrece recomendaciones de productos y rutinas para el cuidado de la piel, el cabello, y la barba, mejorando el aspecto general del cliente."),
            EntidadServicios(R.drawable.siete, "Entrenamiento en Lenguaje Corporal y Comunicación No Verbal",
                "Entrenamiento en Lenguaje Corporal y Comunicación No Verbal")
        )

        adaptador_servicios = AdaptadorServicios(listarServicios)
        recycler_servicios.adapter = adaptador_servicios
        recycler_servicios.layoutManager = LinearLayoutManager(requireContext())
    }
}
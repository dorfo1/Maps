package rodolfo.com.br.maps

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_mapa_via_intent.*

class MapaViaIntentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa_via_intent)

        btExibirLocal.setOnClickListener {
            val latLng =  "-23.5565804,-46.662113"
            val zoom = 15
            val geo = "geo:$latLng?z=$zoom&marker=$latLng"
            exibirNoMapa(geo)
        }



        btExibirRestaurantes.setOnClickListener {
            val query="Restaurentes"
            val geo = "geo:0,0?q=$query"
            exibirNoMapa(geo)
        }

        btExibirRota.setOnClickListener {
            val end = "Rua Olimpiadas,186,São Paulo,São paulo ,brasil"
            val location = Uri.encode(end)
            val modo = "w"
            val geo = "google.navigation:q=$location&mode=$modo"
            exibirNoMapa(geo)

        }
    }

    fun exibirNoMapa(geo:String){
        val geoUri = Uri.parse(geo)

        val intent = Intent(Intent.ACTION_VIEW,geoUri)
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }
}

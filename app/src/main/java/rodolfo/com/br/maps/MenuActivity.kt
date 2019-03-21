package rodolfo.com.br.maps

import android.content.Intent
import android.drm.DrmStore
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        btMapaIntent.setOnClickListener {
            var Intent = Intent(this,MapaViaIntentActivity::class.java)
            startActivity(Intent)
        }

        btMapaNOAPP.setOnClickListener {
            var intent = Intent(this,MapsActivity::class.java)
            startActivity(intent)
        }

    }
}

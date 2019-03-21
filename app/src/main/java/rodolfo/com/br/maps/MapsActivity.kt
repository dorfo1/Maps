package rodolfo.com.br.maps

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import rodolfo.com.br.maps.Utils.PermissionUtils

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener

    fun initLocationListener(){
        locationListener = object : LocationListener{
            override fun onLocationChanged(location: Location?) {
                val latLng = LatLng(location!!.latitude,location!!.longitude)
                mMap.addMarker(MarkerOptions().position(latLng))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15f))
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }

            override fun onProviderEnabled(provider: String?) {
            }

            override fun onProviderDisabled(provider: String?) {
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        initLocationListener()
//        requestLocationUpdates()
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        PermissionUtils.validarPermissoes(
                listOf(Manifest.permission.ACCESS_FINE_LOCATION),this,1
        )

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for(result in grantResults){
            if(result == PackageManager.PERMISSION_DENIED){
                Toast.makeText(this,"Permissão negada",Toast.LENGTH_LONG).show()
            }else{
                requestLocationUpdates()
            }

        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapClickListener {
            mMap.addMarker(MarkerOptions().position(it))
        }

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-23.40,-46.50)
       // mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapDescriptorFactory.fromResource(R.drawable.placeholder)))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        adicionarFormaCircular()
    }


    private fun adicionarFormaCircular(){
        val circulo = CircleOptions()
                .center(LatLng(-23.40,-46.50))
                .radius(200.0)
                .fillColor(Color.argb(128,0,0,102))
                .strokeColor(Color.argb(128,0,50,50))
        mMap.addCircle(circulo)
    }
}

package com.example.canalluzhls


import android.content.Context
import android.content.Intent.getIntent
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.canalluzhls.model.Servidores
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.Player.*
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.*
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*


class  ExoPlayerView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr), Player.Listener {

    //REFERENCIA A LA BASE DE DATOS. USAR USUARIO GOOGLE TELEPUERTO
    //LINK BD: "https://canalluz-9ffd7-default-rtdb.firebaseio.com/"
    private lateinit var database: DatabaseReference
    val referencia = Firebase.database("https://canalluz-9ffd7-default-rtdb.firebaseio.com/").reference
    //FIN REFERENCIA BD
    var elementos: Int? = null
    private val PlayListURL = ArrayList<String>()

    var servidorActual: String? = null

    //CREANDO EXOPLAYER
    private var simpleExoPlayer: SimpleExoPlayer

    init {
        val playerView = PlayerView(context)
        addView(playerView)

        simpleExoPlayer = SimpleExoPlayer.Builder(context).build()
        simpleExoPlayer.addListener(this)

        simpleExoPlayer.playWhenReady = true

        playerView.player = simpleExoPlayer
        //FIN CREACIÓN EXOPLAYER
    }

    fun prepare() {
        val referenciaServ =
            referencia.child("servidores").orderByChild("conexiones").limitToLast(1000)
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot)  {
                var serv = Servidores()
                val i = 0
                for (ds in dataSnapshot.children) {
                    //VALOR DE LA URL DE VIDEO
                    serv = ds.getValue(Servidores::class.java)!!
                    if (serv.getEstado().equals("Activo")) {
                        if (serv.getConexMax()?.toInt()!! > serv.getConexiones()!!.toInt()) {


                            PlayListURL.add(serv.getUrl().toString())

                        }
                    }
                }


                var otems = PlayListURL.size

                for (i in PlayListURL.indices) {
                    //ExoPlayer con Video
                    var servidorEncontrado: MediaItem
                    servidorEncontrado = MediaItem.fromUri(PlayListURL[i])
                    //val servidorEncontrado: MediaItem =
                       // MediaItem.Builder().setUri(PlayListURL[i]).setTag(PlayListURL[i]).build()
                    simpleExoPlayer.addMediaItem(i, servidorEncontrado)
                    if(i==(otems-1)){
                        val linkVideoReserva = Uri.parse("asset:///video.mp4")
                        val servidorEncontrado = MediaItem.fromUri(linkVideoReserva)
                        simpleExoPlayer.addMediaItem( i+1, servidorEncontrado)
                        Log.i("ultimo elemento", (i.toString())) //Don't ignore errors!

                    }
                    Log.i("URLPlayList", (servidorEncontrado.toString())) //Don't ignore errors!

                }
                elementos = simpleExoPlayer.getMediaItemCount()
                simpleExoPlayer.seekTo(0)
                simpleExoPlayer.prepare()

                Log.i("Cant. Elemnto ", (simpleExoPlayer.getMediaItemCount().toString())) //Don't ignore errors!


            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.i("firebase", databaseError.getMessage()) //Don't ignore errors!
            }

        }
        referenciaServ.addListenerForSingleValueEvent(valueEventListener)


    }

    fun onPause() {
        simpleExoPlayer.playWhenReady = false
    }

    fun onResume() {
        simpleExoPlayer.playWhenReady = true
    }

    fun onDestroy() {
        simpleExoPlayer.stop()
    }

    override fun onPlayerError(error: PlaybackException) {
        simpleExoPlayer.seekToNextMediaItem()
        simpleExoPlayer.prepare()
   // Log.e("ExoPlayer", "Error: ", error)
    }

    override fun onTimelineChanged(timeline: Timeline, reason: Int) {

    }
    //LA URL DE STREAMING NO ESTÁ FUNCIONANDO
//SE LANZA VIDEO DESDE FIREBASE
    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {

            if (playWhenReady && playbackState == Player.STATE_READY) {
                servidorActual =
                    simpleExoPlayer.currentMediaItem?.playbackProperties?.uri.toString()
                Log.i("URLActual", (servidorActual.toString())) //Don't ignore errors!
                sumarConexion(servidorActual!!)


                //Toast.makeText(context, "termino video", Toast.LENGTH_LONG).show()

            } else if (playWhenReady) {
                // might be idle (plays after prepare()),
                // buffering (plays when data available)
                // or ended (plays when seek away from end)


             //   simpleExoPlayer.seekToNextMediaItem()



            } else {

                // player paused in any state
                if (servidorActual != null) {
                    restarConexion(servidorActual.toString())
                }

            }
        if(playbackState==Player.STATE_ENDED) {
            Toast.makeText(context, "termino video", Toast.LENGTH_LONG).show()
            Player.STATE_READY
            simpleExoPlayer.clearMediaItems()
            prepare()
        }
        }



    fun sumarConexion(servidor: String) {
        val referenciaServ = referencia.child("servidores")
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var serv = Servidores()
                for (ds in dataSnapshot.children) {
                    //VALOR DE LA URL DE VIDEO
                    serv = ds.getValue(Servidores::class.java)!!
                    if (serv.getUrl().toString() == servidor) {
                        var conexiones = serv.getConexiones()?.toInt()
                        conexiones = 1 + conexiones!!
                        serv.setConexiones(conexiones.toString())
                        referencia.child("servidores").child(serv.getNombre().toString())
                            .setValue(serv)
                        break
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.i("firebase", databaseError.getMessage()) //Don't ignore errors!
            }
        }
        referenciaServ.addListenerForSingleValueEvent(valueEventListener)
    }

    fun restarConexion(servidor: String) {
        val referenciaServ = referencia.child("servidores")
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var serv = Servidores()
                for (ds in dataSnapshot.children) {
                    //VALOR DE LA URL DE VIDEO

                    serv = ds.getValue(Servidores::class.java)!!
                    if (serv.getUrl().toString() == servidor) {
                        var conexiones = serv.getConexiones()?.toInt()
                        conexiones = conexiones!! - 1
                        serv.setConexiones(conexiones.toString())
                        referencia.child("servidores").child(serv.getNombre().toString())
                            .setValue(serv)
                        break
                    }
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.i("firebase", databaseError.getMessage()) //Don't ignore errors!
            }

        }
        referenciaServ.addListenerForSingleValueEvent(valueEventListener)
    }

}



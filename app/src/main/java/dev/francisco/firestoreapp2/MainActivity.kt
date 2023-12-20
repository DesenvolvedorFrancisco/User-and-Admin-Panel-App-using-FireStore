package dev.francisco.firestoreapp2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dev.francisco.firestoreapp2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Faz referencia a lista da CLASS listAdapter no ArrayList<User>

        var list= arrayListOf<User>()
        var listAdapter=ListAdapter(this,list)

        // Instancia o layout Main a partir do RecyclerView

        binding.list.layoutManager=LinearLayoutManager(this)
        binding.list.adapter=listAdapter


        // Firebase Firestone database

        val  db = Firebase.firestore
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
             list.clear()
                for (document in result) {
 //                   Log.d(TAG,"${document.id} => ${document.data}")
                    var user=document.toObject(User::class.java)
                    user.id=document.id
                    list.add(user)
                }

                // notifica as alteracoes

                listAdapter.notifyDataSetChanged()

            }
            .addOnFailureListener { exception ->
//                Log.w(TAG, "Error getting documents.", exception)
            }


    }
}
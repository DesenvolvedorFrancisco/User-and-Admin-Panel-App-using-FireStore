package dev.francisco.firestoreapp2

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dev.francisco.firestoreapp2.databinding.ListItemBinding

// Depois de configurar a Class ListAdapter implemente os metodos gerando-os automaticamente
class ListAdapter (var context: Context, var list: ArrayList<User>):
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(var binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)


    }

    override fun getItemCount(): Int {
        return list.size

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textViewUser.text=list.get(position).user
        holder.binding.textViewPassword.text=list.get(position).password

        // configuracao do botton Update

        holder.binding.buttonUpdate.setOnClickListener {
          val intent= Intent(context, MainActivity2::class.java)
            intent.putExtra("USER",list.get(position).user)
            intent.putExtra("PASSWORD",list.get(position).password)
            intent.putExtra("ID",list.get(position).id )
          context.startActivity(intent)
        }

          // configuracao do botton delete

        holder.binding.buttonDelete.setOnClickListener {
            val db = Firebase.firestore
            db.collection("users").document(list.get(position).id!!).delete().addOnSuccessListener {
             Toast.makeText(context,"Delete", Toast.LENGTH_LONG).show()

                // Remove o elemento da lista depois de deletado
                list.removeAt(position)

                notifyDataSetChanged()
            }.addOnFailureListener {
                Toast.makeText(context, "Failed to delete", Toast.LENGTH_LONG).show()
            }
        }

    }


}
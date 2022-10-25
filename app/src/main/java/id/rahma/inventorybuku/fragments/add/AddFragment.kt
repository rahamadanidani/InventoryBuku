package id.rahma.inventorybuku.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import id.rahma.inventorybuku.viewmodel.BukuViewModel
import id.rahma.inventorybuku.R
import id.rahma.inventorybuku.model.Buku
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mUserViewModel: BukuViewModel

    override fun onCreateView( //Melakukan pemanggilan saat fragment menggambar antarmuka pertamakali.
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Mengembangkan tata letak untuk fragmen
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(BukuViewModel::class.java)

        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val firstName = addFirstName_et.text.toString()
        val lastName = addLastName_et.text.toString()
        val age = addAge_et.text

        if(inputCheck(firstName, lastName, age)){
            // Membaca objek buku
            val user = Buku(
                0,
                firstName,
                lastName,
                Integer.parseInt(age.toString())
            )
            // Menanmbahkan data pada database,jika sukses maka akan ada pemberitahuan bahwa data
            //sukses ditambahkan,Namun jika tidak maka akan ada pemberitahuan "silahkan isi semua kolom".
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Pada saat data telah ditambahkan maka tampilan akan kembali ke halaman utama.
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}
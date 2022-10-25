package id.rahma.inventorybuku.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import  id.rahma.inventorybuku.R
import  id.rahma.inventorybuku.model.Buku
import  id.rahma.inventorybuku.viewmodel.BukuViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: BukuViewModel

    override fun onCreateView( // Sistem akan memanggil saat fragmen menggambar antarmuka penggunanya untuk yang pertama kali
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Mengembangkan layout pada fragmen update.
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(BukuViewModel::class.java)

        view.updateFirstName_et.setText(args.currentUser.JudulBuku)
        view.updateLastName_et.setText(args.currentUser.Pengarang)
        view.updateAge_et.setText(args.currentUser.Harga.toString())

        view.update_btn.setOnClickListener {
            updateItem()
        }

        //Menambahkan menu
        setHasOptionsMenu(true)

        return view
    }
//fungsi pengeditan pada item
    private fun updateItem() {
        val firstName = updateFirstName_et.text.toString()
        val lastName = updateLastName_et.text.toString()
        val age = Integer.parseInt(updateAge_et.text.toString())

        if (inputCheck(firstName, lastName, updateAge_et.text)) {
            // Membuat atau menampilkan objek
            val updatedBuku = Buku(args.currentUser.id, firstName, lastName, age)
            // Melakukan pengeditan pada buku
            mUserViewModel.updateUser(updatedBuku)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            //tampilan akan kembali ke navigasi
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }
// fungsi digunakan jika ingin melakukan penghapusan pada data yang di inputkan.
    //Yang mana jika data berhasil di hapus maka akan ada pesan bahwa "Berhasil dihapus".
    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentUser.JudulBuku}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentUser.JudulBuku}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.JudulBuku}?")
        builder.create().show()
    }
}
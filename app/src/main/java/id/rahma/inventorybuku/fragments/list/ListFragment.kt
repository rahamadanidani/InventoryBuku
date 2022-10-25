package id.rahma.inventorybuku.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
//import id.rahma.inventorybuku.viewmodel.BukuViewModel
import  id.rahma.inventorybuku.R
import id.rahma.inventorybuku.viewmodel.BukuViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var mUserViewModel: BukuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Melakukan pengembangan pada layout di fragment list
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // digunakan untuk menampilkan daftar data dari database(recyclerview)
        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext()) //layout yang mendukung tampilan list secara vertikal dan horizontal.


        // BukuViewModel
        mUserViewModel = ViewModelProvider(this).get(BukuViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // Digunakan untuk menambahkan menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }
// Fungsi ini di gunakan untuk menghapus semua data yang telah di masuka.Jika data berhasil di hapus
    // maka akan menghasilkan pesan bahwa "Berhasil menghapus semuanya".
    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteAllUsers()
            Toast.makeText(
                requireContext(),
                "Successfully removed everything",
                Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete everything?") //Menampilkan pesan untuk menghapus semua data
        builder.setMessage("Are you sure you want to delete everything?")// Menampilkan pesan apakah kita yakin akan menghapus
                                                                // semua data yang telah dimasukan.
        builder.create().show()
    }
}
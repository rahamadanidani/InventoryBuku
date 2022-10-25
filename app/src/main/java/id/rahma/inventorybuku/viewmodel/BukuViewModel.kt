package id.rahma.inventorybuku.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import id.rahma.inventorybuku.data.BukuDatabase
import id.rahma.inventorybuku.repository.BukuRepository
import id.rahma.inventorybuku.model.Buku
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BukuViewModel(application: Application): AndroidViewModel(application) {
// Membaca Semua data
    val readAllData: LiveData<List<Buku>>
    private val repository: BukuRepository

    init {
        val userDao = BukuDatabase.getDatabase(
            application
        ).userDao()
        repository = BukuRepository(userDao)
        readAllData = repository.readAllData
    }
//fungsi ini di gunakan untuk menambahkan data
    fun addUser(user: Buku){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
//fungsi ini dilakukan untuk pengeditan pada data
    fun updateUser(user: Buku){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }
//Penghapusan pada data
    fun deleteUser(user: Buku){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }
//fungsi ini di gunakan untuk menghapus semua data
    fun deleteAllUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUsers()
        }
    }

}
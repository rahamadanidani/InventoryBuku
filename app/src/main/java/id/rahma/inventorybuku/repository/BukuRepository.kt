package id.rahma.inventorybuku.repository

import androidx.lifecycle.LiveData
import id.rahma.inventorybuku.data.BukuDao
import id.rahma.inventorybuku.model.Buku

class BukuRepository(private val userDao: BukuDao) {

    val readAllData: LiveData<List<Buku>> = userDao.readAllData()

    suspend fun addUser(user: Buku){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: Buku){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: Buku){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }

}
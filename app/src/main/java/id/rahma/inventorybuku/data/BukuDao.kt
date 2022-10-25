package id.rahma.inventorybuku.data

import androidx.lifecycle.LiveData
import androidx.room.*
import id.rahma.inventorybuku.model.Buku

@Dao
interface BukuDao {
//Perintah dibawah ini di gunakan untuk menambahkan,mengedit,dan menghapus data buku.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: Buku)

    @Update
    suspend fun updateUser(user: Buku)

    @Delete
    suspend fun deleteUser(user: Buku)
//Perintah ini di gunakan untuk menghapus semua data pada pada tabel buku.
    @Query("DELETE FROM buku_table")
    suspend fun deleteAllUsers()
//Digunakan untuk menampilkan semua data pada tabel buku setelah diinputkan yang mana tampilannya
    // berupa urutan atau list data dari data yang ditambahkan.
    @Query("SELECT * FROM buku_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Buku>>

}
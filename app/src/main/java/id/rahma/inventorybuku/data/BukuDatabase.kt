package id.rahma.inventorybuku.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.rahma.inventorybuku.model.Buku
//Anotasi @Database memerlukan beberapa argumen, sehingga Room dapat membuat database.
//item buku sebagai satu-satunya class dengan daftar entities.
// version sebagai 1. Setiap kali mengubah skema tabel database, kita harus meningkatkan nomor versinya.
//exportSchema ke false digunakan agar tidak menyimpan cadangan histori versi skema.
@Database(entities = [Buku::class], version = 1, exportSchema = false)
abstract class BukuDatabase : RoomDatabase() {
    // untuk mengetahui DAO didalam isi class
    abstract fun userDao(): BukuDao
// Perintah berikut ini di gunakan untuk
    // membuat atau mendapatkan database dengan menggunakan nama class sebagai penentu.
    companion object {

    // perubahan yang dibuat oleh satu thread ke INSTANCE akan langsung
    // terlihat oleh semua thread lainnya.
        @Volatile
        private var INSTANCE: BukuDatabase? = null
//Menampilkan konten  pada room database
        fun getDatabase(context: Context): BukuDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BukuDatabase::class.java,
                    "buku_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
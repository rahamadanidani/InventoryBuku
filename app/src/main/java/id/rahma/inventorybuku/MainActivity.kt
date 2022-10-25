package id.rahma.inventorybuku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    //Melakukan pemanggilan kelas super onCreate untuk menyelesaikan pembuatan aktivitas seperti
    // hierarki tampilan
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //Mengatur tata letak antarmuka pengguna
        // untuk aktivitas ini file tata letak didefinisikan dalam file projek res/layout/main_activity.xml

        setupActionBarWithNavController(findNavController(R.id.fragment))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
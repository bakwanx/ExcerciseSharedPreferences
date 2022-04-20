package com.example.excercisesharedpreferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.excercisesharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val sharedPrefFile = "kotlinsharedpreference"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //melakukan instance
        val sharePreferences: SharedPreferences = this.getSharedPreferences(
            sharedPrefFile, Context.MODE_PRIVATE
        )
        val editor = sharePreferences.edit()

        binding.apply {
            btnSave.setOnClickListener {
                val idUser = Integer.parseInt(etInputId.text.toString())
                val name = etInputName.text.toString()

                editor.apply{
                    putInt(KEY_ID, idUser)
                    putString(KEY_NAME, name)
                    apply()
                }

                toastShow("Data Saved")
            }

            btnView.setOnClickListener {
                val sharedIdValue = sharePreferences.getInt(KEY_ID, 0)
                val sharedNameValue = sharePreferences.getString(KEY_NAME, "defaultname")

                if (sharedIdValue == 0 && sharedNameValue.equals("defaultname")) {
                    tvShowName.text = sharedNameValue
                    tvShowId.text = sharedIdValue.toString()
                    toastShow("Data View Kosong")
                } else {
                    tvShowName.text = sharedNameValue
                    tvShowId.text = sharedIdValue.toString()
                    toastShow("Data View Ditampilkan")
                }
            }

            btnClear.setOnClickListener {
                editor.clear()
                editor.apply()
                tvShowName.text = ""
                tvShowId.text = ""
                toastShow("Data clear")
            }
        }


    }

    private fun toastShow(message: String){
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

    companion object{
        const val KEY_ID = "id_key"
        const val KEY_NAME = "name_key"
    }
}
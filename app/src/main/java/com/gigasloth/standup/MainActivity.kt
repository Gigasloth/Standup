package com.gigasloth.standup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RawRes
import androidx.databinding.DataBindingUtil
import com.gigasloth.standup.databinding.ActivityMainBinding
import com.google.gson.GsonBuilder

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val gson = GsonBuilder().setPrettyPrinting().create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewModel = readData(R.raw.testdata).toString()
    }

    fun readData(@RawRes fileResource: Int): Model {
        val inputStream = resources.openRawResource(fileResource)
        val bytes = ByteArray(inputStream.available())
        inputStream.read(bytes)

        return gson.fromJson(String(bytes), Model::class.java)
    }
}
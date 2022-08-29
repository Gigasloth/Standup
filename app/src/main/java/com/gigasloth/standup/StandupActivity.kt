package com.gigasloth.standup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.gigasloth.standup.databinding.ActivityStandupBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StandupActivity : AppCompatActivity() {

    lateinit var binding: ActivityStandupBinding

    @Inject
    lateinit var jsonResourceReader: JsonResourceReader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_standup)

        val json = jsonResourceReader.readToClass(R.raw.testdata, StandupDisplayable::class.java)
        binding.viewModel = json.toString()
    }
}
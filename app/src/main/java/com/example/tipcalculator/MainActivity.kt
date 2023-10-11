package com.example.tipcalculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import androidx.activity.ComponentActivity
import com.example.tipcalculator.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.seekBarTipPercent.max = 30

        binding.seekBarTipPercent.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.textViewSelectedTipPercent.text = "$progress%"
                updateTipAndTotal()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        binding.editTextBaseAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                updateTipAndTotal()
            }
        })
    }

    private fun updateTipAndTotal() {
        val baseAmountStr = binding.editTextBaseAmount.text.toString()
        val tipPercent = binding.seekBarTipPercent.progress

        if (baseAmountStr.isEmpty()) {
            binding.textViewTipAmount.text = "Tip Amount: $0.00"
            binding.textViewTotalAmount.text = "Total Amount: $0.00"
            return
        }

        val baseAmount = baseAmountStr.toDouble()
        val tipAmount = (baseAmount * tipPercent) / 100.0
        val totalAmount = baseAmount + tipAmount

        binding.textViewTipAmount.text = "Tip Amount: $${String.format("%.2f", tipAmount)}"
        binding.textViewTotalAmount.text = "Total Amount: $${String.format("%.2f", totalAmount)}"
    }
}
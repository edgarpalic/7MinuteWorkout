package com.edgar.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.edgar.a7minuteworkout.databinding.ActivityBmiactivityBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBmiactivityBinding
    val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
    val IMPERIAL_UNITS_VIEW = "IMPERIAL_UNIT_VIEW"

    var currentVisibleView: String = METRIC_UNITS_VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        setSupportActionBar(binding.toolbarBmiActivity)

        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title = "Calculate BMI"
        }

        binding.toolbarBmiActivity.setNavigationOnClickListener {
            onBackPressed()
        }

        makeVisibleMetricUnitsView()

        binding.rgUnits.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.rbMetricUnits) {
                makeVisibleMetricUnitsView()
            } else {
                makeVisibleImperialUnitsView()
            }
        }

        binding.btnCalculateUnits.setOnClickListener {

            if (currentVisibleView == METRIC_UNITS_VIEW) {
                if (validateMetricUnits()) {
                    val heightValue: Float =
                        binding.etMetricUnitHeight.text.toString().toFloat() / 100
                    val weightValue: Float = binding.etMetricUnitWeight.text.toString().toFloat()

                    val bmi = weightValue / (heightValue * heightValue)
                    displayBMIResult(bmi)
                } else {
                    Toast.makeText(
                        this@BMIActivity,
                        "Please enter valid values.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            } else {
                if(validateImperialUnits()){
                    val imperialUnitHeightValueFeet: String = binding.etImperialUnitHeightFeet.text.toString()
                    val imperialUnitHeightValueInch: String = binding.etImperialUnitHeightInch.text.toString()
                    val imperialUnitWeightValue: Float = binding.etImperialUnitWeight.text.toString().toFloat()

                    val heightValue = imperialUnitHeightValueInch.toFloat() + imperialUnitHeightValueFeet.toFloat() * 12

                    val bmi = 703 * (imperialUnitWeightValue / (heightValue * heightValue))

                    displayBMIResult(bmi)
                }else {
                    Toast.makeText(
                        this@BMIActivity,
                        "Please enter valid values.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    private fun makeVisibleMetricUnitsView() {
        currentVisibleView = METRIC_UNITS_VIEW
        binding.tilMetricUnitWeight.visibility = View.VISIBLE
        binding.tilMetricUnitHeight.visibility = View.VISIBLE
        binding.tilImperialUnitWeight.visibility = View.GONE
        binding.llImperialUnitsHeight.visibility = View.GONE

        binding.etMetricUnitHeight.text!!.clear()
        binding.etMetricUnitWeight.text!!.clear()

        binding.llDiplayBMIResult.visibility = View.INVISIBLE
        binding.tvBMIValue.visibility = View.INVISIBLE
        binding.tvBMIType.visibility = View.INVISIBLE
        binding.tvBMIDescription.visibility = View.INVISIBLE
    }

    private fun makeVisibleImperialUnitsView() {
        currentVisibleView = IMPERIAL_UNITS_VIEW
        binding.tilMetricUnitWeight.visibility = View.GONE
        binding.tilMetricUnitHeight.visibility = View.GONE
        binding.tilImperialUnitWeight.visibility = View.VISIBLE
        binding.llImperialUnitsHeight.visibility = View.VISIBLE

        binding.etImperialUnitWeight.text!!.clear()
        binding.etImperialUnitHeightFeet.text!!.clear()
        binding.etImperialUnitHeightInch.text!!.clear()

        binding.llDiplayBMIResult.visibility = View.INVISIBLE
        binding.tvBMIValue.visibility = View.INVISIBLE
        binding.tvBMIType.visibility = View.INVISIBLE
        binding.tvBMIDescription.visibility = View.INVISIBLE
    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true

        if (binding.etMetricUnitWeight.text.toString().isEmpty())
            isValid = false
        else if (binding.etMetricUnitHeight.text.toString().isEmpty())
            isValid = false

        return isValid
    }

    private fun validateImperialUnits(): Boolean {
        var isValid = true

        if (binding.etImperialUnitWeight.text.toString().isEmpty()) {
            isValid = false
        } else if (binding.etImperialUnitHeightFeet.text.toString().isEmpty()) {
            isValid = false
        } else if (binding.etImperialUnitHeightInch.text.toString().isEmpty()) {
            isValid = false
        }

        return isValid
    }

    private fun displayBMIResult(bmi: Float) {
        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Severely underweight"
            bmiDescription = "You are in trouble, eat something!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0) {
            bmiLabel = "Very underweight"
            bmiDescription = "You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "Underweight"
            bmiDescription = "You are underweight! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in good shape!"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0
        ) {
            bmiLabel = "Overweight"
            bmiDescription = "You need to take care of your yourself! Lose some weight!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "You need to take care of your yourself! Lose some weight!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
        ) {
            bmiLabel = "Obese Class || (Very obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        binding.tvYourBMI.visibility = View.VISIBLE
        binding.tvBMIValue.visibility = View.VISIBLE
        binding.tvBMIType.visibility = View.VISIBLE
        binding.tvBMIDescription.visibility = View.VISIBLE

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        binding.llDiplayBMIResult.visibility = View.VISIBLE
        binding.tvBMIValue.text = bmiValue
        binding.tvBMIType.text = bmiLabel
        binding.tvBMIDescription.text = bmiDescription
    }
  }
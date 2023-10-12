package com.example.test

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var tvdate : TextView? = null
    private var tvmin: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePick: Button = findViewById(R.id.btndatepick)
        tvmin= findViewById(R.id.selectmin)
        tvdate = findViewById(R.id.tvdate)
        btnDatePick.setOnClickListener{
            clickDatePicker()
        }
    }

    fun clickDatePicker(){
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month=cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            {view,year,month,dayOfMonth ->
                val sel_date = "${dayOfMonth}/${month+1}/${year}"
                tvdate?.text = sel_date
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val DATE = sdf.parse(sel_date)
                DATE?.let {
                    val DateInMin= DATE.time/60000
                    val curDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    curDate?.let {
                        val curDateInMin = curDate.time / 60000
                        val difInMin= curDateInMin - DateInMin
                        tvmin?.text = difInMin.toString()
                    }

                }

            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }

}
package com.example.towersadmin.ui

import android.app.DatePickerDialog
import android.os.Build
import android.widget.CalendarView
import android.widget.DatePicker
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
class DatePicker(val callback:(result: String) -> Unit) : CalendarView.OnDateChangeListener {

    override fun onSelectedDayChange(view: CalendarView, year: Int, month: Int, dayOfMonth: Int) {
        var mesSelecionado = (month + 1).toString()
        var diaSelecionado = dayOfMonth.toString()
        var anoSelecionado = year.toString()
        callback("$diaSelecionado/$mesSelecionado/$anoSelecionado")
    }
}
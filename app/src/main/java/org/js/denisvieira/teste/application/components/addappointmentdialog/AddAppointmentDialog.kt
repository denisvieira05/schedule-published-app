package org.js.denisvieira.teste.application.components.addappointmentdialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.js.denisvieira.teste.R
import org.js.denisvieira.teste.databinding.ComponentAddAppointmentDialogBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AddAppointmentDialog(context: Context) : Dialog(context), AddAppointmentViewContract {

    private var mAddAppointmentDialogBinding: ComponentAddAppointmentDialogBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context)        ,
        R.layout.component_add_appointment_dialog, null, false)

    private var dayAppointmentDatepickerDialog : DatePickerDialog? = null
    private var hourAppointmentTimepickerDialog : TimePickerDialog? = null
    private var mOnSubmitAddAppointmentForm: AddAppointmentViewContract.OnSubmitAddAppointmentForm? = null
    private var mDayAppointmentDate: Date? = null
    private var mHourAppointmentDate: Date?  = null

    init {
        setContentView(mAddAppointmentDialogBinding.root)
        window?.setLayout(MATCH_PARENT, WRAP_CONTENT)

        mAddAppointmentDialogBinding.handler = this

        setupComponents()
    }

    private fun setupComponents() {
        configureSelectDayPickerDialog()
        configureSelectHourPickerDialog()
        setupIncreaseAndDecreaseDurationTimeSelector()
    }

    private fun setupIncreaseAndDecreaseDurationTimeSelector() {
        mAddAppointmentDialogBinding.addAppointmentDialogIncreaseDecreaseTimeDurationButtons.setOnPressDecreaseButton(
            View.OnClickListener {
                updateDurationTimeLabel(false)
            })
        mAddAppointmentDialogBinding.addAppointmentDialogIncreaseDecreaseTimeDurationButtons.setOnPressIncreaseButton(
            View.OnClickListener {
                updateDurationTimeLabel(true)
            })
    }

    private fun updateDurationTimeLabel(isIncrease: Boolean) {
        var valueWithMin = mAddAppointmentDialogBinding.addAppointmentDialogDurationTimeTextView.text.toString()
        valueWithMin = valueWithMin.replace("min","")
        var decimalTimeValue = valueWithMin.toInt()

        when {
            isIncrease -> decimalTimeValue += 15
            decimalTimeValue > 15 -> decimalTimeValue -= 15
            else -> {
                Toast.makeText(context, context.getString(R.string.add_availability_dialog_duration_is_zero), Toast.LENGTH_SHORT).show()
                return
            }
        }

        mAddAppointmentDialogBinding.addAppointmentDialogDurationTimeTextView.text = "${decimalTimeValue}min"
    }

    private fun configureSelectDayPickerDialog() {
        val calendar = Calendar.getInstance()
        val textViewToChange = mAddAppointmentDialogBinding.addAppointmentDialogSelectDayValueTextView
        val dateSetListener = createDateSetListener(calendar, textViewToChange)

        dayAppointmentDatepickerDialog = DatePickerDialog(
            context, dateSetListener, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    private fun configureSelectHourPickerDialog() {
        val calendar = Calendar.getInstance()
        val textViewToChange = mAddAppointmentDialogBinding.addAppointmentDialogSelectHourValueTextView
        val dateSetListener = createTimeSetListener(calendar, textViewToChange)

        hourAppointmentTimepickerDialog = TimePickerDialog(
            context, dateSetListener,calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE), false
        )
    }

    private fun createDateSetListener(calendar: Calendar, dateTextViewToChange: TextView): DatePickerDialog.OnDateSetListener {
        return DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            updateDateLabel(dateTextViewToChange, calendar.time)

            mDayAppointmentDate = calendar.time
        }
    }

    private fun createTimeSetListener(calendar: Calendar, dateTextViewToChange: TextView): TimePickerDialog.OnTimeSetListener {
        return TimePickerDialog.OnTimeSetListener { timePicker: TimePicker, hourOfDay: Int, minute: Int ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);

            updateTimeLabel(dateTextViewToChange, calendar.time)

            mHourAppointmentDate = calendar.time
        }
    }

    private fun updateDateLabel(textView: TextView, dateToUpdate: Date) {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())

        textView.text = sdf.format(dateToUpdate)
    }

    private fun updateTimeLabel(textView: TextView, dateToUpdate: Date) {
        textView.text = DateFormat.getTimeInstance(DateFormat.SHORT).format(dateToUpdate)
    }

    override fun setOnSubmitForm(onSubmitAddAppointmentForm: AddAppointmentViewContract.OnSubmitAddAppointmentForm) {
        mOnSubmitAddAppointmentForm = onSubmitAddAppointmentForm
    }

    override fun showSelectDayPicker(view: View) {
        dayAppointmentDatepickerDialog?.show()
    }

    override fun showSelectHourPicker(view: View) {
        hourAppointmentTimepickerDialog?.show()
    }

    override fun onPressSubmitButton(view: View) {
        dismiss()

        mOnSubmitAddAppointmentForm?.onSubmit()
    }


}
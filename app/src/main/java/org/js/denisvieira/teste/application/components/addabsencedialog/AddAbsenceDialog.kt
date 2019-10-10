package org.js.denisvieira.teste.application.components.addabsencedialog

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
import androidx.databinding.DataBindingUtil
import org.js.denisvieira.teste.R
import org.js.denisvieira.teste.databinding.ComponentAddAbsenceDialogBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AddAbsenceDialog(context: Context) : Dialog(context), AddAbsenceViewContract {
    private var mAddAbsenceDialogBinding: ComponentAddAbsenceDialogBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context)        ,
        R.layout.component_add_absence_dialog, null, false)

    private var dayFromAbsenceDatepickerDialog : DatePickerDialog? = null
    private var dayAtAbsenceDatepickerDialog : DatePickerDialog? = null
    private var hourFromAbsenceTimepickerDialog : TimePickerDialog? = null
    private var hourAtAbsenceTimepickerDialog : TimePickerDialog? = null
    private var mOnSubmitAddAbsenceForm: AddAbsenceViewContract.OnSubmitAddAbsenceForm? = null
    private var mDayAbsenceDate: Date? = null
    private var mHourAbsenceDate: Date?  = null

    init {
        setContentView(mAddAbsenceDialogBinding.root)
        window?.setLayout(MATCH_PARENT, WRAP_CONTENT)

        mAddAbsenceDialogBinding.handler = this

        setupComponents()
    }

    private fun setupComponents() {
        dayFromAbsenceDatepickerDialog = configureSelectDayPickerDialog(mAddAbsenceDialogBinding.addAbsenceDialogSelectDayFromValueTextView)
        dayAtAbsenceDatepickerDialog = configureSelectDayPickerDialog(mAddAbsenceDialogBinding.addAbsenceDialogSelectDayAtValueTextView)

        hourFromAbsenceTimepickerDialog = configureSelectHourPickerDialog(mAddAbsenceDialogBinding.addAbsenceDialogSelectHourFromValueTextView)
        hourAtAbsenceTimepickerDialog = configureSelectHourPickerDialog(mAddAbsenceDialogBinding.addAbsenceDialogSelectHourAtValueTextView)

        setupRadioGroupAbsenceSelector()
    }

    private fun setupRadioGroupAbsenceSelector() {
        mAddAbsenceDialogBinding.addAbsenceDialogAlternativesRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.to_define -> showToDefineAbsenceContainer()
                R.id.from_now_on -> hideToDefineAbsenceContainer()
            }
        }
    }

    private fun showToDefineAbsenceContainer() {
        mAddAbsenceDialogBinding.addAbsenceDialogToDefineContainer.visibility = View.VISIBLE
    }

    private fun hideToDefineAbsenceContainer() {
        mAddAbsenceDialogBinding.addAbsenceDialogToDefineContainer.visibility = View.GONE
    }

    private fun configureSelectDayPickerDialog(textViewToChange: TextView): DatePickerDialog {
        val calendar = Calendar.getInstance()
        val dateSetListener = createDateSetListener(calendar, textViewToChange)

        return DatePickerDialog(
            context, dateSetListener, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    private fun configureSelectHourPickerDialog(textViewToChange: TextView): TimePickerDialog {
        val calendar = Calendar.getInstance()
        val dateSetListener = createTimeSetListener(calendar, textViewToChange)

        return TimePickerDialog(
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

            mDayAbsenceDate = calendar.time
        }
    }

    private fun createTimeSetListener(calendar: Calendar, dateTextViewToChange: TextView): TimePickerDialog.OnTimeSetListener {
        return TimePickerDialog.OnTimeSetListener { timePicker: TimePicker, hourOfDay: Int, minute: Int ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);

            updateTimeLabel(dateTextViewToChange, calendar.time)

            mHourAbsenceDate = calendar.time
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

    override fun setOnSubmitForm(onSubmitAddAbsenceForm: AddAbsenceViewContract.OnSubmitAddAbsenceForm) {
        mOnSubmitAddAbsenceForm = onSubmitAddAbsenceForm
    }

    override fun showSelectDayFromPicker(view: View) {
        dayFromAbsenceDatepickerDialog?.show()
    }

    override fun showSelectDayAtPicker(view: View) {
        dayAtAbsenceDatepickerDialog?.show()
    }


    override fun showSelectHourFromPicker(view: View) {
        hourFromAbsenceTimepickerDialog?.show()
    }

    override fun showSelectHourAtPicker(view: View) {
        hourAtAbsenceTimepickerDialog?.show()
    }

    override fun onPressSubmitButton(view: View) {
        dismiss()

        mOnSubmitAddAbsenceForm?.onSubmit()
    }

}
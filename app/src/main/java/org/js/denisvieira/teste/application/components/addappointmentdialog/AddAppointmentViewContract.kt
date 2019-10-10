package org.js.denisvieira.teste.application.components.addappointmentdialog

interface AddAppointmentViewContract {

    fun showSelectDayPicker(view: android.view.View)

    fun showSelectHourPicker(view: android.view.View)

    fun onPressSubmitButton(view: android.view.View)

    fun setOnSubmitForm(onSubmitAddAvailabilityForm: OnSubmitAddAppointmentForm)

    interface OnSubmitAddAppointmentForm {
        fun onSubmit()
    }

}
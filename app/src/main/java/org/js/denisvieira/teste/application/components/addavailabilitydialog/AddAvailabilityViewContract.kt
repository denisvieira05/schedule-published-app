package org.js.denisvieira.teste.application.components.addavailabilitydialog

interface AddAvailabilityViewContract {

    fun showSelectDayPicker(view: android.view.View)

    fun showSelectHourPicker(view: android.view.View)

    fun onPressSubmitButton(view: android.view.View)

    fun setOnSubmitForm(onSubmitAddAvailabilityForm: OnSubmitAddAvailabilityForm)

    interface OnSubmitAddAvailabilityForm {
        fun onSubmit()
    }

}
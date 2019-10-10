package org.js.denisvieira.teste.application.components.addabsencedialog

interface AddAbsenceViewContract {

    fun showSelectDayFromPicker(view: android.view.View)

    fun showSelectDayAtPicker(view: android.view.View)

    fun showSelectHourFromPicker(view: android.view.View)

    fun showSelectHourAtPicker(view: android.view.View)

    fun onPressSubmitButton(view: android.view.View)

    fun setOnSubmitForm(onSubmitAddAvailabilityForm: OnSubmitAddAbsenceForm)

    interface OnSubmitAddAbsenceForm {
        fun onSubmit()
    }

}
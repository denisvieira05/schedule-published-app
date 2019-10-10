package org.js.denisvieira.teste.application.modules.selectservice

interface SelectServiceContract {

    interface View {
        fun showAddAbsenceDialog(view: android.view.View)

        fun showAddAppointmentDialog(view: android.view.View)

        fun showAddAvailabilityDialog(view: android.view.View)
    }

    interface Presenter {

    }
}
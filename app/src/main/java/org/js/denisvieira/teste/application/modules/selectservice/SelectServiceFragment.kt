package org.js.denisvieira.teste.application.modules.selectservice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import org.js.denisvieira.teste.R
import org.js.denisvieira.teste.application.components.addabsencedialog.AddAbsenceDialog
import org.js.denisvieira.teste.application.components.addabsencedialog.AddAbsenceViewContract
import org.js.denisvieira.teste.application.components.addabsencedialog.AddAbsenceViewContract.OnSubmitAddAbsenceForm
import org.js.denisvieira.teste.application.components.addappointmentdialog.AddAppointmentDialog
import org.js.denisvieira.teste.application.components.addappointmentdialog.AddAppointmentViewContract
import org.js.denisvieira.teste.application.components.addappointmentdialog.AddAppointmentViewContract.OnSubmitAddAppointmentForm
import org.js.denisvieira.teste.application.components.addavailabilitydialog.AddAvailabilityDialog
import org.js.denisvieira.teste.application.components.addavailabilitydialog.AddAvailabilityViewContract.OnSubmitAddAvailabilityForm
import org.js.denisvieira.teste.application.helper.FragmentBase
import org.js.denisvieira.teste.databinding.MainformFragmentBinding

class SelectServiceFragment : FragmentBase(), SelectServiceContract.View {

    private lateinit var mBinding: MainformFragmentBinding
    private lateinit var mAddAbsenceDialog: AddAbsenceDialog
    private lateinit var mAddAvailabilityDialog: AddAvailabilityDialog
    private lateinit var mAddAppointmentDialog: AddAppointmentDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.mainform_fragment, container, false)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.lifecycleOwner = this
        mBinding.handler        = this

        initDialogConfigurations()
    }

    private fun initDialogConfigurations() {
        context?.let {
            mAddAvailabilityDialog = AddAvailabilityDialog(it)
            mAddAvailabilityDialog.setOnSubmitForm(object : OnSubmitAddAvailabilityForm {
                override fun onSubmit() {
                    val snackbar = Snackbar.make(mBinding.root, getString(R.string.add_availability_dialog_submit_success), Snackbar.LENGTH_LONG)
                    snackbar.view.setBackgroundColor(ContextCompat.getColor(context!!,R.color.colorPrimary))
                    snackbar.show()

                    mAddAvailabilityDialog = AddAvailabilityDialog(it)
                }
            })

            mAddAppointmentDialog = AddAppointmentDialog(it)
            mAddAppointmentDialog.setOnSubmitForm(object : OnSubmitAddAppointmentForm{
                override fun onSubmit() {
                    val snackbar = Snackbar.make(mBinding.root, getString(R.string.add_appointment_dialog_submit_success), Snackbar.LENGTH_LONG)
                    snackbar.view.setBackgroundColor(ContextCompat.getColor(context!!,R.color.colorPrimary))
                    snackbar.show()

                    mAddAppointmentDialog = AddAppointmentDialog(it)
                }
            })
            mAddAbsenceDialog = AddAbsenceDialog(it)
            mAddAbsenceDialog.setOnSubmitForm(object : OnSubmitAddAbsenceForm {
                override fun onSubmit() {
                    val snackbar = Snackbar.make(mBinding.root, getString(R.string.add_absence_dialog_submit_success), Snackbar.LENGTH_LONG)
                    snackbar.view.setBackgroundColor(ContextCompat.getColor(context!!,R.color.colorPrimary))
                    snackbar.show()

                    mAddAppointmentDialog = AddAppointmentDialog(it)
                }
            })
        }
    }

    override fun showAddAbsenceDialog(view: View) {
        mAddAbsenceDialog.let {  mAddAbsenceDialog.show() }
    }

    override fun showAddAppointmentDialog(view: View) {
        mAddAppointmentDialog.let {  mAddAppointmentDialog.show() }
    }

    override fun showAddAvailabilityDialog(view: View) {
        mAddAvailabilityDialog.let {  mAddAvailabilityDialog.show() }
    }


}
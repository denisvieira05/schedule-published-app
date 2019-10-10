package org.js.denisvieira.teste.application.helper

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import org.js.denisvieira.teste.R

open class FragmentBase: Fragment() {

    fun showAlert(message: String) {
        val builder = AlertDialog.Builder(context!!)

        builder
            .setMessage(message.toInt())
            .setCancelable(false)
            .setPositiveButton(getString(R.string.common_ok)) { _, _ ->  }

        val alert = builder.create()
        alert.show()
    }

}
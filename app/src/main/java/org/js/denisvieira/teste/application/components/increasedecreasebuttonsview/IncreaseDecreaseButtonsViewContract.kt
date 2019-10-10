package org.js.denisvieira.teste.application.components.increasedecreasebuttonsview

import android.view.View

interface IncreaseDecreaseButtonsViewContract {

    fun setOnPressIncreaseButton(onClickListener: View.OnClickListener?)

    fun setOnPressDecreaseButton(onClickListener: View.OnClickListener?)

    fun onPressIncreaseButton(view: View?)

    fun onPressDecreaseButton(view: View?)

}
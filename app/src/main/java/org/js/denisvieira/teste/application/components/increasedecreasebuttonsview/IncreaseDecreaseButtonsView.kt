package org.js.denisvieira.teste.application.components.increasedecreasebuttonsview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import org.js.denisvieira.teste.R
import org.js.denisvieira.teste.databinding.ComponentIncreaseDecreaseButtonsViewBinding


class IncreaseDecreaseButtonsView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs),
    IncreaseDecreaseButtonsViewContract {

    private var mIncreaseDecreaseButtonsViewBinding: ComponentIncreaseDecreaseButtonsViewBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(
                context
            ), R.layout.component_increase_decrease_buttons_view, this, true
        )
    private var onPressIncreaseButton: OnClickListener? = null
    private var onPressDecreaseButton: OnClickListener? = null

    init {
        mIncreaseDecreaseButtonsViewBinding.handler = this
        orientation = HORIZONTAL
    }

    override fun setOnPressIncreaseButton(onClickListener: OnClickListener?) {
        onPressIncreaseButton = onClickListener
    }

    override fun setOnPressDecreaseButton(onClickListener: OnClickListener?) {
        onPressDecreaseButton = onClickListener
    }

    override fun onPressIncreaseButton(view: View?) {
        if (onPressIncreaseButton != null) onPressIncreaseButton!!.onClick(view)
    }

    override fun onPressDecreaseButton(view: View?) {
        if (onPressDecreaseButton != null) onPressDecreaseButton!!.onClick(view)
    }

}

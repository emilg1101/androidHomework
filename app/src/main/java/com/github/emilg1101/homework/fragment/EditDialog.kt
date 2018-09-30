package com.github.emilg1101.homework.fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import com.github.emilg1101.homework.R
import kotlinx.android.synthetic.main.dialog_edit.view.*

class EditDialog : DialogFragment() {

    var listener: EditListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_edit, null)
        return AlertDialog.Builder(this.requireActivity())
                .setTitle(getString(R.string.dialog_edit_title))
                .setView(view)
                .setPositiveButton(getString(R.string.dialog_edit_button_ok)) { _, _ ->
                    listener?.onInfoEdited(
                            view.input_login.editText?.text.toString(),
                            view.input_email.editText?.text.toString()
                    )
                }
                .setNegativeButton(getString(R.string.dialog_edit_button_cancel)) { _, _ -> dismiss() }
                .create()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as? EditListener
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface EditListener {
        fun onInfoEdited(login: String, email: String)
    }
}

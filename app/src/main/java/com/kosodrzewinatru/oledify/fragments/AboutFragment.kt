package com.kosodrzewinatru.oledify.fragments

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.kosodrzewinatru.oledify.R
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : DialogFragment() {
    private val licenceFragment = LicenceFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val packageInfo =
            context?.packageManager?.getPackageInfo((context ?: return).packageName, 0)
        val versionName = packageInfo?.versionName

        app_version.text = "${getString(R.string.version)}: $versionName"
        licence.text = "- PhotoView"

        source_button.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/chrisbanes/PhotoView"))
            startActivity(browserIntent)
        }

        licence_button.setOnClickListener {
            dismiss()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace((requireView().parent as ViewGroup).id, licenceFragment)
                ?.addToBackStack(null)
                ?.commit()
        }

        oledify_source.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/kosodrzewina/oledify"))
            startActivity(browserIntent)
        }
    }
}
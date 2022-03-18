package fr.isen.henry.erestaurant

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso


private const val ARG_PARAM = "param"

class CarouselFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(ARG_PARAM)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_carousel, container, false)
        url = url.takeIf { it != "" }
        Picasso.get().load(url).placeholder(R.drawable.logo).into(view.findViewById(R.id.frImage) as ImageView)
        Log.d("CFOCV00", "$url" )
        return view
    }

    companion object {
        fun newInstance(url: String) =
            CarouselFragment().apply {
                Log.d("CFOCV01", "$url" )
                arguments = Bundle().apply {
                    putString(ARG_PARAM, url)
                }
            }
    }
}
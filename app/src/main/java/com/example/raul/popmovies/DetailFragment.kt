package com.example.raul.popmovies

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.raul.popmovies.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
import java.io.Serializable
import javax.xml.transform.Result


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val EXTRA_DETAIL = "EXTRA_DETAIL"


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DetailFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class DetailFragment : Fragment() {

    private var movie: Movie? = null
    private var listener: OnFragmentInteractionListener? = null
    private var firebase = Firebase()
    private val uriBase = "https://image.tmdb.org/t/p/w780"
    //https://image.tmdb.org/t/p/w300/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg

    companion object {
        val EXTRA_DETAIL = "EXTRA_DETAIL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movie = it.getSerializable(EXTRA_DETAIL) as Movie
            activity?.findViewById<Toolbar>(R.id.toolbar_main)?.title = movie?.title
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_detail, container, false)

        movie?.let {

            view.txtOverview.text = it.overview

            view.checkFavorite.setOnClickListener {
                if(checkFavorite.isChecked) {
                    val userId = firebase.auth.currentUser?.uid
                    userId?.let {
                        val uid = it
                        movie?.let {
                            firebase.registerFavoriteMovie(uid, it)
                        }
                    }
                }
            }

            val uri = Uri.parse(uriBase + it.backdrop_path)
            Picasso
                    .get()
                    .load(uri.toString())
                    .placeholder(R.drawable.no_movie_img)
                    .fit().centerCrop()
                    .into(view.imgMovieDetail)

        }
        // Inflate the layout for this fragment
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
//    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

//    companion object {
//
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment DetailFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(result: Serializable) =
//                DetailFragment().apply {
//                    arguments = Bundle().apply {
//                        putSerializable(EXTRA_DETAIL, result)
//                    }
//                }
//    }
}

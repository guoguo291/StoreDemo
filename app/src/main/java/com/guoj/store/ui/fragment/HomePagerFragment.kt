package com.guoj.store.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guoj.store.R
import com.guoj.store.model.bean.Categories

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ID = "materialId"
private const val TITLE = "title"

/**
 * A simple [Fragment] subclass.
 * Use the [HomePagerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomePagerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var id: String? = null
    private var title: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString(ID)
            title = it.getString(TITLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_pager, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomePagerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(categories: Categories.Data) =
            HomePagerFragment().apply {
                arguments = Bundle().apply {
                    putLong(ID, categories.id)
                    putString(TITLE, categories.title)
                }
            }
    }
}
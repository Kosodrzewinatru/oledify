package com.kosodrzewinatru.oledify.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kosodrzewinatru.oledify.GalleryItem
import com.kosodrzewinatru.oledify.R
import com.kosodrzewinatru.oledify.RecyclerAdapter
import kotlinx.android.synthetic.main.fragment_gallery.*
import java.io.File

class GalleryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val directory =
            File(Environment.getExternalStorageDirectory().toString() + "/Pictures/Oledify")

        if (directory.isDirectory &&
            directory.listFiles() != null &&
            directory.listFiles().isNotEmpty()
        ) {
            val files = directory.listFiles().toList()
            val galleryItems = mutableListOf<GalleryItem>()

            for (file in files) {
                val currentBitmap = BitmapFactory.decodeFile(file.path)
                galleryItems.add(GalleryItem(currentBitmap))
            }

            imagesRecyclerView.adapter = RecyclerAdapter(galleryItems)
            imagesRecyclerView.layoutManager = LinearLayoutManager(activity)
            imagesRecyclerView.setHasFixedSize(true)
        }
    }
}
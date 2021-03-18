package com.app.ola.core.util

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.google.android.material.card.MaterialCardView
import com.app.ola.R
import java.io.File


internal class ImageListAdapter internal constructor(
        context: Context, private val resource: Int, private val itemList: MutableList<String>) : ArrayAdapter<ImageListAdapter.ItemHolder>(context, resource) {

    override fun getCount(): Int {
        return if (this.itemList != null) this.itemList.size else 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        val holder: ItemHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null)
            holder = ItemHolder()

//            holder.icon = convertView.findViewById(R.id.imgOC)
//            holder.base = convertView.findViewById(R.id.base)
            convertView.tag = holder
        } else {
            holder = convertView.tag as ItemHolder
        }

//        holder.name!!.text = this.itemList!![position]
        try{
            if(position==0){
                holder.base!!.strokeWidth = 0
                holder.icon!!.setImageResource(R.drawable.ic_add_image)
            }
        }
        catch (e:Exception){

        }

        var imgFile = File(itemList[position])

        if(imgFile.exists()){

            val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)


            //myImage.setImageBitmap(myBitmap)
            holder.icon!!.setImageBitmap(myBitmap)
        }
        //holder.icon!!.setImageResource()
        //Glide.with(this).load(e.urlPic).into(v.profilex)
//        holder.base!!.setOnClickListener {
//            (context as TheftStep1Activity).finish()
//            val i = Intent(context, TheftStep1Activity::class.java)
//            i.putExtra("call_loader","1")
//            context.startActivity(i)
//
//        }

        return convertView!!
    }

    internal class ItemHolder {
        //var name: TextView? = null
        var icon: ImageView? = null
        var base: MaterialCardView? = null
    }
}

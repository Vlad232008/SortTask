package com.example.sorttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    private val random = Random()
    private fun rand() : Int {
        return random.nextInt(1000)
    }
    var arrayList: MutableList<Int> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvText: TextView = findViewById(R.id.tvArray)
        val btnCount: Button = findViewById(R.id.btnCount)
        btnCount.setOnClickListener {
            val count:EditText = findViewById(R.id.etCount)
            for(i in 1..count.text.toString().toInt()){
                arrayList.add(rand())
            }
            //arrayList.sort()
            val sBuilder = StringBuilder()
            for((counter, i) in (1..arrayList.size).withIndex()){
                if (i == arrayList.size) sBuilder.append("${arrayList[counter]}")
                else sBuilder.append("${arrayList[counter]},")
            }
            tvText.text = sBuilder.toString()
        }
        val btnOne: Button = findViewById(R.id.btnOne)
        btnOne.setOnClickListener {
            var counter = 0
            arrayList = foolSortAlgorithm(arrayList)
            val aBuilder = StringBuilder()
            for(i in 1..arrayList.size){
                if (i == arrayList.size) aBuilder.append("${arrayList[counter]}")
                else aBuilder.append("${arrayList[counter]},")
                ++counter
            }
            tvText.text = aBuilder.toString()
        }
        val btnTwo: Button = findViewById(R.id.btnTwo)
        btnTwo.setOnClickListener {
            var counter = 0
            arrayList = bubbleSortFromFool(arrayList)
            val aBuilder = StringBuilder()
            for(i in 1..arrayList.size){
                if (i == arrayList.size) aBuilder.append("${arrayList[counter]}")
                else aBuilder.append("${arrayList[counter]},")
                ++counter
            }
            tvText.text = aBuilder.toString()
        }
        val btnThree: Button = findViewById(R.id.btnThree)
        btnThree.setOnClickListener {
            var counter = 0
            arrayList = cocktailSortAlgorithm(arrayList)
            val aBuilder = StringBuilder()
            for(i in 1..arrayList.size){
                if (i == arrayList.size) aBuilder.append("${arrayList[counter]}")
                else aBuilder.append("${arrayList[counter]},")
                ++counter
            }
            tvText.text = aBuilder.toString()
        }
    }

    //Пузырьковая Сортировка
    private fun bubbleSortFromFool(items: MutableList<Int>):MutableList<Int>{
        do{
            var swapped = false
            for(index in 1 until items.size){
                val previousItem = items[index-1]
                val currentItem = items[index]
                if(previousItem.compare(currentItem)){
                    items.swapItems(index-1, index)
                    swapped = true
                }
            }
        }while (swapped)
        return items
    }


    //Глупая Сортировка
    private fun foolSortAlgorithm(items: MutableList<Int>):MutableList<Int>{
        var swapped = true
        while (swapped){
            swapped = swapIfNeedAndReturn(items)
        }
        return items
    }

    private fun swapIfNeedAndReturn(items: MutableList<Int>):Boolean{
        for(index in 1 until items.size){
            val previousItem = items[index-1]
            val currentItem = items[index]
            if(previousItem.compare(currentItem)){
                items.swapItems(index-1, index)
                return true
            }
        }
        return false
    }

    private fun MutableList<Int>.swapItems(leftIndex: Int, rightIndex: Int){
        if (leftIndex != rightIndex) {
            val temporary = this[leftIndex]
            this[leftIndex] = this[rightIndex]
            this[rightIndex] = temporary
        }
    }

    private fun Int.compare(anotherItem:Int):Boolean{
        return this-anotherItem>0
    }

    private fun cocktailSortAlgorithm(items: MutableList<Int>): MutableList<Int>{
        var rightBorder = items.size
        var leftBorder = 1

        fun swapIfNeedGoUp(items: MutableList<Int>): Boolean {
            var swapped = false
            for (index in 1 until rightBorder) {
                val previousItem = items[index - 1]
                val currentItem = items[index]
                if (previousItem.compare(currentItem)) {
                    items.swapItems(index - 1, index)
                    rightBorder = index-1
                    swapped = true
                }
            }
            return swapped
        }

        fun swapIfNeedGoDown(items: MutableList<Int>): Boolean {
            var swapped = false
            for (index in items.size - 1 downTo leftBorder) {
                val previousItem = items[index - 1]
                val currentItem = items[index]
                if (previousItem.compare(currentItem)) {
                    items.swapItems(index - 1, index)
                    leftBorder = index
                    swapped = true
                }
            }
            return swapped
        }

        do {
            val swapped: Boolean = swapIfNeedGoUp(items) && swapIfNeedGoDown(items)
        } while (swapped)
    return items
    }
}
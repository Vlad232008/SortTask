package com.example.sorttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private val random = Random()
    private fun rand(): Int {
        return random.nextInt(1000)
    }

    private var arrayList: MutableList<Int> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvText: TextView = findViewById(R.id.tvArray)
        val btnCount: Button = findViewById(R.id.btnCount)
        btnCount.setOnClickListener {
            tvText.text = ""
            arrayList.clear()
            val count: EditText = findViewById(R.id.etCount)
            for (i in 1..count.text.toString().toInt()) {
                arrayList.add(rand())
            }
            //arrayList.sort()
            val sBuilder = StringBuilder()
            for ((counter, i) in (1..arrayList.size).withIndex()) {
                if (i == arrayList.size) sBuilder.append("${arrayList[counter]}")
                else sBuilder.append("${arrayList[counter]},")
            }
            tvText.text = sBuilder.toString()
        }
        val btnOne: Button = findViewById(R.id.btnOne)
        btnOne.setOnClickListener {
            val startTime = System.nanoTime()
            arrayList = foolSortAlgorithm(arrayList)
            val totalTime = System.nanoTime() - startTime
            Log.d("MyLog", "Глупая сортировка пройдена за: $totalTime")
            val aBuilder = StringBuilder()
            for ((counter, i) in (1..arrayList.size).withIndex()) {
                if (i == arrayList.size)
                    aBuilder.append("${arrayList[counter]}")
                else aBuilder.append("${arrayList[counter]},")
            }
            tvText.text = aBuilder.toString()
        }
        val btnTwo: Button = findViewById(R.id.btnTwo)
        btnTwo.setOnClickListener {
            val startTime = System.nanoTime()
            arrayList = bubbleSortFromFool(arrayList)
            val totalTime = System.nanoTime() - startTime
            Log.d("MyLog", "Пузырьковая сортировка пройдена за: $totalTime")
            val aBuilder = StringBuilder()
            for ((counter, i) in (1..arrayList.size).withIndex()) {
                if (i == arrayList.size) aBuilder.append("${arrayList[counter]}")
                else aBuilder.append("${arrayList[counter]},")
            }
            tvText.text = aBuilder.toString()
        }
        val btnThree: Button = findViewById(R.id.btnThree)
        btnThree.setOnClickListener {
            val startTime = System.nanoTime()
            arrayList = cocktailSortAlgorithm(arrayList)
            val totalTime = System.nanoTime() - startTime
            Log.d("MyLog", "Шейкерная сортировка пройдена за: $totalTime")
            val aBuilder = StringBuilder()
            for ((counter, i) in (1..arrayList.size).withIndex()) {
                if (i == arrayList.size) aBuilder.append("${arrayList[counter]}")
                else aBuilder.append("${arrayList[counter]},")
            }
            tvText.text = aBuilder.toString()
        }
        val btnFour: Button = findViewById(R.id.btnFour)
        btnFour.setOnClickListener {
            val startTime = System.nanoTime()
            arrayList = combSortAlgorithm(arrayList)
            val totalTime = System.nanoTime() - startTime
            Log.d("MyLog", "Сортировка вставками пройдена за: $totalTime")
            val aBuilder = StringBuilder()
            for ((counter, i) in (1..arrayList.size).withIndex()) {
                if (i == arrayList.size) aBuilder.append("${arrayList[counter]}")
                else aBuilder.append("${arrayList[counter]},")
            }
            tvText.text = aBuilder.toString()
        }
        val btnFive: Button = findViewById(R.id.btnFive)
        btnFive.setOnClickListener {
            val startTime = System.nanoTime()
            arrayList = shellSort(arrayList)
            val totalTime = System.nanoTime() - startTime
            Log.d("MyLog", "Сортировка Шелла пройдена за: $totalTime")
            val aBuilder = StringBuilder()
            for ((counter, i) in (1..arrayList.size).withIndex()) {
                if (i == arrayList.size) aBuilder.append("${arrayList[counter]}")
                else aBuilder.append("${arrayList[counter]},")
            }
            tvText.text = aBuilder.toString()
        }
    }


    //Глупая Сортировка
    private fun foolSortAlgorithm(items: MutableList<Int>): MutableList<Int> {
        var swapped = true
        while (swapped) {
            swapped = swapIfNeedAndReturn(items)
        }
        return items
    }

    //Пузырьковая Сортировка
    private fun bubbleSortFromFool(items: MutableList<Int>): MutableList<Int> {
        do {
            var swapped = false
            for (index in 1 until items.size) {
                val previousItem = items[index - 1]
                val currentItem = items[index]
                if (previousItem.compare(currentItem)) {
                    items.swapItems(index - 1, index)
                    swapped = true
                }
            }
        } while (swapped)
        return items
    }

    private fun swapIfNeedAndReturn(items: MutableList<Int>): Boolean {
        for (index in 1 until items.size) {
            val previousItem = items[index - 1]
            val currentItem = items[index]
            if (previousItem.compare(currentItem)) {
                items.swapItems(index - 1, index)
                return true
            }
        }
        return false
    }

    private fun MutableList<Int>.swapItems(leftIndex: Int, rightIndex: Int) {
        if (leftIndex != rightIndex) {
            val temporary = this[leftIndex]
            this[leftIndex] = this[rightIndex]
            this[rightIndex] = temporary
        }
    }

    private fun Int.compare(anotherItem: Int): Boolean {
        return this - anotherItem > 0
    }

    //Шейкерная сортировка
    private fun cocktailSortAlgorithm(items: MutableList<Int>): MutableList<Int> {
        var rightBorder = items.size
        var leftBorder = 1

        fun swapIfNeedGoUp(items: MutableList<Int>): Boolean {
            var swapped = false
            for (index in 1 until rightBorder) {
                val previousItem = items[index - 1]
                val currentItem = items[index]
                if (previousItem.compare(currentItem)) {
                    items.swapItems(index - 1, index)
                    rightBorder = index - 1
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

    //Расческой
    private fun combSortAlgorithm(items: MutableList<Int>): MutableList<Int> {
        var gap = items.size
        var swapped = true
        var index: Int

        while (gap > 1 || swapped) {
            if (gap > 1) {
                gap = (gap / 1.3).toInt()
            }
            swapped = false
            index = 0
            while (index + gap < items.size) {
                if (items[index] > items[index + gap]) {
                    items.swapItems(index, index + gap)
                }
                index++
            }
        }
        return items
    }

    //Вставкой
    private fun shellSort(array: MutableList<Int>): MutableList<Int> {
        var interval = 1
        var insertedValue: Int
        var insertedIndex: Int
        //получаем первоначальный интервал, с которым буем ходить по массиву
        //согласно круту, интервал расчитывается по формуле h = 3*h + 1
        while (interval < array.size / 3) {
            interval = 3 * interval + 1
        } //используем метод сортировки вставками на большом интервале. //Как только интервал становится равен 1 - сортировка становится обычной сортировкой вставками
        while (interval > 0) {
            for (i in interval until array.size) {
                insertedValue = array[i]
                insertedIndex = i

                while (insertedIndex >= interval && insertedValue < array[insertedIndex - interval]) {
                    array[insertedIndex] = array[insertedIndex - interval]
                    insertedIndex -= interval
                }

                array[insertedIndex] = insertedValue
            }

            interval = (interval - 1) / 3
        }
        return array
    }
}
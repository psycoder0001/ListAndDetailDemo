package com.ewo.laddemo

import com.ewo.laddemo.helpers.kotlin.convertListToJson
import org.junit.Assert
import org.junit.Test


class KotlinTest {
    @Test
    fun arrayList_jsonConverter_isCorrect() {
        val emptyArrayList = ArrayList<String>()
        val emptyListJsonData = emptyArrayList.convertListToJson()
        Assert.assertEquals(emptyListJsonData, "")
        println("ArrayList Json Converter - Test With Empty List : TEST SUCCESS!");

        val expectedJson = "[\"1\",\"1\",\"2\",\"3\",\"5\",\"8\",\"FinalValue\"]"
        val correctValueArrayList = listOf("1", "1", "2", "3", "5", "8", "FinalValue")
        val correctValuesJsonData = correctValueArrayList.convertListToJson()
        Assert.assertEquals(correctValuesJsonData, expectedJson)
        println("ArrayList Json Converter - Test With Correct Values : TEST SUCCESS!");
    }
}
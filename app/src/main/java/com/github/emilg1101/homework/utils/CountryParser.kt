package com.github.emilg1101.homework.utils

import android.content.Context
import com.github.emilg1101.homework.R
import com.github.emilg1101.homework.model.Country
import org.xmlpull.v1.XmlPullParser

object CountryParser {

    private const val TAG_COUNTRY = "country"
    private const val TAG_NAME = "name"
    private const val TAG_ISO = "iso"
    private const val TAG_LOCATION = "location"

    fun parse(context: Context): List<Country> {
        val parser = context.resources.getXml(R.xml.country_list)
        val list = arrayListOf<Country>()

        var id = 0
        var name = ""
        var iso = 0
        var location = ""

        var nameTagStarted = false
        var isoTagStarted = false
        var locationTagStarted = false

        while (parser.eventType != XmlPullParser.END_DOCUMENT) {

            when (parser.eventType) {
                XmlPullParser.START_TAG -> {
                    nameTagStarted = parser.name == TAG_NAME
                    isoTagStarted = parser.name == TAG_ISO
                    locationTagStarted = parser.name == TAG_LOCATION
                }
                XmlPullParser.END_TAG -> {
                    if (parser.name == TAG_COUNTRY) {
                        list.add(Country(id++, name, iso, location))
                    }
                }
                XmlPullParser.TEXT -> {
                    if (nameTagStarted) name = parser.text
                    if (isoTagStarted) iso = Integer.parseInt(parser.text)
                    if (locationTagStarted) location = parser.text
                }
            }
            parser.next()
        }
        return list
    }
}

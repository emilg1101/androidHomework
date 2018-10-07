package com.github.emilg1101.homework.utils

import com.github.emilg1101.homework.R
import com.github.emilg1101.homework.model.Contact

object ContactsGenerator {

    fun generate(): List<Contact> = arrayListOf(
            Contact(R.drawable.ic_marvel_iron_man, "Iron Man", "ironman@gmail.com", "+7 (917) 243-52-34"),
            Contact(R.drawable.ic_marvel_groot, "Groot", "groot_tree123@gmail.com", "+7 (917) 243-52-34"),
            Contact(R.drawable.ic_marvel_deadpool, "Deadpool", "deadpoool@mail.ru", "+7 (917) 243-52-34"),
            Contact(R.drawable.ic_marvel_ant_man, "Ant Man", "ant_man@rambler.ru", "+7 (917) 243-52-34"),
            Contact(R.drawable.ic_marvel_captain_america, "Captain America", "steve.rogers@gmail.com", "+7 (917) 243-52-34"),
            Contact(R.drawable.ic_marvel_cyclops, "Cyclops", "cyclops@xman.com", "+7 (917) 243-52-34"),
            Contact(R.drawable.ic_marvel_daredevil, "Daredevil", "daredevill@mail.ru", "+7 (917) 243-52-34"),
            Contact(R.drawable.ic_marvel_hawkeye, "Hawkeye", "hawkeye@gmail.com", "+7 (917) 243-52-34"),
            Contact(R.drawable.ic_marvel_magneto, "Magneto", "magneto@outlook.com", "+7 (917) 243-52-34"),
            Contact(R.drawable.ic_marvel_spider_man, "Spider-Man", "piter.parker@yandex.ru", "+7 (917) 243-52-34"),
            Contact(R.drawable.ic_marvel_thanos, "Thanos", "thanos@mail.ru", "+7 (917) 243-52-34"),
            Contact(R.drawable.ic_marvel_thor, "Thor", "manwithhammer@asgard.org", "+7 (917) 243-52-34"),
            Contact(R.drawable.ic_marvel_wolverine, "Wolverine", "wolfwolfwolf@xman.com", "+7 (917) 243-52-34"),
            Contact(R.drawable.ic_minion_bob, "Bob", "bob.minion@bana.na", "+7 (917) 243-52-34"),
            Contact(R.drawable.ic_minion_kevin, "Kevin", "kevin.minion@bana.na", "+7 (917) 243-52-34"),
            Contact(R.drawable.ic_minion_stuart, "Stuart", "stuart.minion@bana.na", "+7 (917) 243-52-34"),
            Contact(R.drawable.ic_dc_batman, "Batman", "dark.knight@gothem.com", "+7 (917) 243-52-34"),
            Contact(R.drawable.ic_dc_flash, "Flash", "flash@gmail.com", "+7 (917) 243-52-34")
    ).shuffled()
}

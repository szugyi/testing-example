package io.supercharge.testingexample.util

import io.supercharge.testingexample.R

object NoteUtil {

    fun getLocalizedNoteTitle(title: String): String{
        return applicationContext.getString(R.string.note_title, title)
    }
}
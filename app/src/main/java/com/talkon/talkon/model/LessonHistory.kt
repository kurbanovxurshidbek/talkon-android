package com.talkon.talkon.model

class LessonHistory(
    var teacher: Teacher,
    var startedHour: String,
    var finishedHour: String,
    var date: String,
    var videoPath: String,
    var isExpandable:Boolean = false
)
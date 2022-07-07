package com.talkon.uz.model

class Teacher {
    var profile: String= ""
    var fullName: String= ""
    var aboutInfo: String= ""
    var stars: Int = 0
    var rating: String?= null
    var lessons: String?= null
    var new: String?= null
    var isOnline: Boolean = false
    var isRated: Boolean = false

    constructor(profile: String, fullName: String, aboutInfo: String, lessons: String ,stars: Int, rating: String,
                 isOnline: Boolean,  isRated: Boolean){
        this.profile = profile
        this.fullName = fullName
        this.stars = stars
        this.rating = rating
        this.lessons = lessons
        this.aboutInfo = aboutInfo
        this.isOnline = isOnline
        this.isRated = isRated
    }
    constructor(profile: String, fullName: String,  aboutInfo: String,lessons: String? = null , new: String,
                isOnline: Boolean,  isRated: Boolean){
        this.profile = profile
        this.fullName = fullName
        this.new = new
        this.aboutInfo = aboutInfo
        this.lessons = lessons
        this.isOnline = isOnline
        this.isRated = isRated
    }
}
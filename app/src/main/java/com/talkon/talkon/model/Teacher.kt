package com.talkon.talkon.model

class Teacher {
    var profile: String= ""
    var fullName: String= ""
    var aboutInfo: String= ""
    var stars: String?= null
    var rating: String?= null
    var lessons: String?= null
    var new: String?= null
    var isOnline: Boolean = false
    var isRated: Boolean = false
    var isCertified: Boolean?= false

    constructor(profile: String, fullName: String, stars: String? = null, rating: String? = null, lessons: String? = null,
                aboutInfo: String, isOnline: Boolean, isCertified: Boolean,isRated: Boolean){
        this.profile = profile
        this.fullName = fullName
        this.stars = stars
        this.rating = rating
        this.lessons = lessons
        this.aboutInfo = aboutInfo
        this.isOnline = isOnline
        this.isCertified = isCertified
        this.isRated = isRated
    }
    constructor(profile: String, fullName: String, new: String? = null, lessons: String? = null,
                aboutInfo: String, isOnline: Boolean, isCertified: Boolean,isRated: Boolean){
        this.profile = profile
        this.fullName = fullName
        this.new = new
        this.lessons = lessons
        this.aboutInfo = aboutInfo
        this.isOnline = isOnline
        this.isCertified = isCertified
        this.isRated = isRated
    }
}
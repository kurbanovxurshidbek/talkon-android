package com.talkon.talkon.model

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
    var isVerified: Boolean = false
    var isBusy: Boolean = false

    constructor(profile: String, fullName: String, aboutInfo: String, lessons: String ,stars: Int, rating: String,
                 isOnline: Boolean, isVerified: Boolean,isBusy: Boolean, isRated: Boolean){
        this.profile = profile
        this.fullName = fullName
        this.stars = stars
        this.rating = rating
        this.lessons = lessons
        this.aboutInfo = aboutInfo
        this.isOnline = isOnline
        this.isVerified = isVerified
        this.isBusy = isBusy
        this.isRated = isRated
    }
    constructor(profile: String, fullName: String, aboutInfo: String, lessons: String ,stars: Int, rating: String,
                 isOnline: Boolean, isVerified: Boolean, isRated: Boolean){
        this.profile = profile
        this.fullName = fullName
        this.stars = stars
        this.rating = rating
        this.lessons = lessons
        this.aboutInfo = aboutInfo
        this.isOnline = isOnline
        this.isVerified = isVerified
        this.isRated = isRated
    }
    constructor(profile: String, fullName: String,  aboutInfo: String, new: String,
                isOnline: Boolean, isVerified: Boolean,isBusy: Boolean = false, isRated: Boolean){
        this.profile = profile
        this.fullName = fullName
        this.new = new
        this.aboutInfo = aboutInfo
        this.isOnline = isOnline
        this.isVerified = isVerified
        this.isBusy = isBusy
        this.isRated = isRated
    }
    constructor(profile: String, fullName: String,  aboutInfo: String, new: String,
                isOnline: Boolean, isVerified: Boolean, isRated: Boolean){
        this.profile = profile
        this.fullName = fullName
        this.new = new
        this.aboutInfo = aboutInfo
        this.isOnline = isOnline
        this.isVerified = isVerified
        this.isBusy = isBusy
        this.isRated = isRated
    }
}
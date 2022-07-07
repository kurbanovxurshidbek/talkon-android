package com.talkon.uz.model

class Chat {
    var profile: String= ""
    var fullName: String= ""
    var message: String = ""
    var messageCount: Int? = 0
    var time: String= ""
    var isOnline: Boolean = false

    constructor(
        profile: String, fullName: String,
        message: String, messageCount: Int?, time: String, isOnline: Boolean){
        this.profile = profile
        this.fullName = fullName
        this.isOnline = isOnline
        this.message = message
        this.time = time
        this.messageCount = messageCount
    }
}
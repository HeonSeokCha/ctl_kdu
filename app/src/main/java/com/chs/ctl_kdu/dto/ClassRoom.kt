package com.chs.ctl_kdu.dto


data class ClassRoom(
    val course_id: String = "",
    val class_no: String = "",
    val title: String = "",
    val professor: String = "",
    val credit: String = "",
    val lec_type: String = ""
)

data class Content (
    val subject: String = "",
    val date: String = "",
    val total_time: String? = "",
)
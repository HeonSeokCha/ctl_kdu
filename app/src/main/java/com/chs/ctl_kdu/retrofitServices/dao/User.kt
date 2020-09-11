import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("userMap")
    val userMap: UserMap,
)

data class UserMap(
    @SerializedName("user_no")
    val userNo: String = "",
    @SerializedName("user_name")
    val userName: String = "",
    @SerializedName("email")
    val email: String = "",
    @SerializedName("dept_nm")
    val deptNm: String = "",
)
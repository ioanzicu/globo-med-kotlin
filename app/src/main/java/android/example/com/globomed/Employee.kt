package android.example.com.globomed

class Employee(
    val id: String,
    val name: String,
    val dob: Long,
    val designation: String) {

    override fun toString(): String {
        return "id: $id, name: $name, DOB: $dob, designation: $designation"
    }
}

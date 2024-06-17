package parking

class ParkingLot(private val car: String) {

    fun hasParked() {
        println("$car car has parked.")
    }
    fun justParked() {
        println("$car car just parked here.")
    }
    fun leftParked() {
        println("$car car left the parking lot.")
    }
}
fun main() {
    ParkingLot(readln()).hasParked()
    ParkingLot(readln()).leftParked()
    ParkingLot(readln()).justParked()
}

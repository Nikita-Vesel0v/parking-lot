package parking

class Car(val id: String, val color: String, var parked: Boolean = false)
class ParkingSpot(var status: String = "free", var car: Car? = null)

class ParkingLot {
    private val parkingSpots: Map<Int, ParkingSpot>
    init {
        parkingSpots = buildMap {
            put(0, ParkingSpot("close"))
            put(1, ParkingSpot())
        }
    }
    private fun findFreeSpot(): Int {
        parkingSpots.forEach { (id, car) -> if (car.status == "free") return id  }
        return -1
    }
    fun countFreeSpots() = parkingSpots.count { (_, spot) -> spot.status == "free" }

    fun park(car: Car) {
        val spotId = findFreeSpot()
        if (spotId == -1) println("Sorry, No free spot =(")
        else {
            println("${car.color} car parked in spot ${spotId + 1}.")
            parkingSpots[spotId]!!.status = "close"; car.parked = true
            parkingSpots[spotId]!!.car = car
        }
    }
    fun leave(spotId: Int) {
        try {
            when (parkingSpots[spotId - 1]!!.status) {
                "close" -> parkingSpots[spotId - 1]!!.status = "free".also { println("Spot $spotId is free.") }
                "free" -> println("There is no car in spot $spotId.")
            }
        } catch (e: Exception) {
            println("No such spot: $spotId.")
        }
    }
}
fun main() {
    val parkingLot = ParkingLot()
    val input = readln().split(" ")
    val action = input[0]
    parkingLot
        .apply {
            when (action) {
                "park" -> this.park(Car(id = input[1], color = input[2]))
                "leave" -> this.leave(input[1].toInt())
            }
        }
        .also {
            println("Available parking spots: ${it.countFreeSpots()}")
        }
}

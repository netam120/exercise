
import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDateTime

class OrdersAnalyzer {

    data class Order(val orderId: Int, val creationDate: LocalDateTime, val orderLines: List<OrderLine>)

    data class OrderLine(val productId: Int, val name: String, val quantity: Int, val unitPrice: BigDecimal)

    fun totalDailySales(orders: List<Order>): Map<DayOfWeek, Int> {

        val orderMap = HashMap<DayOfWeek, Int>()
        for (order:Order in orders) {
            val dayOfWeek = order.creationDate.dayOfWeek
            val quantity = order.orderLines.sumBy { it.quantity }
            if (orderMap.contains(dayOfWeek)) {
                orderMap[dayOfWeek]?.let { orderMap[dayOfWeek] = it + quantity} ?: run { orderMap[dayOfWeek] = quantity }
            } else {
                orderMap[dayOfWeek] = quantity

            }
        }
        return orderMap
    }
}

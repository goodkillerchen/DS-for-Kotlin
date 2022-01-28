import java.io.File
import kotlin.concurrent.timer
import kotlin.random.Random
import kotlin.time.measureTime
import kotlin.math.*
fun main(){

    var start = System.currentTimeMillis()
//    val fd = File("./src/").readText().split(' ', '\n' ).filter { it!=" " }
//    fd.forEachIndexed{
//        index, element -> bst[element] = index
//    }

    var j = 10000
    while(j > 0) {
        var min1 = Int.MAX_VALUE
        var max1 = Int.MIN_VALUE
        val bst = Map<Int,Int>()
        j--
        val t = Random.nextInt(17)
        var m = 0
        for (i in 0 until 17) {
            val key = Random.nextInt(100)
            if (i == t)
                m = key
            min1 = min(min1, key)
            max1 = max(max1, key)
            bst[key] = i
        }
        if(m == max1 || m == min1)
            continue
        println("$max1 $min1 $m")
        println("$bst ${bst.size()}")
        bst.deleteMin()
        println("$bst ${bst.size()}")
        bst.deleteMax()
        println("$bst ${bst.size()}")
        bst.delete(m)
        println("$bst ${bst.size()}")
    }
}
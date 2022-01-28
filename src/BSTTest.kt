import java.io.File
import kotlin.concurrent.timer
import kotlin.random.Random
import kotlin.time.measureTime
import kotlin.math.*
fun main(){
    val bst = Map<Int,Int>()
    var start = System.currentTimeMillis()
//    val fd = File("./src/").readText().split(' ', '\n' ).filter { it!=" " }
//    fd.forEachIndexed{
//        index, element -> bst[element] = index
//    }
    var min1 = Int.MAX_VALUE
    var max1 = Int.MIN_VALUE
    for(i in 0 until 17){
        val key = Random.nextInt(100)
        min1 = min(min1, key)
        max1 = max(max1, key)
        bst[key] = i
    }
    println("$max1 $min1")
    println(bst)
    bst.deleteMin()
    println(bst)
    bst.deleteMax()
    println(bst)
//    println(bst)
//    bst.set(2,0)
//    bst.set(44,1)
//    bst.set(24,2)
//    bst.set(12,4)
//    bst.set(14,5)
//    bst.set(17,6)
//    bst.set(1,7)
//    bst.set(7,9)
//    bst.set(49,10)
//    bst.set(3,11)
//    bst.set(26,12)
//    bst.set(25,13)
//    bst.set(30,14)
//    bst.set(40,15)

}
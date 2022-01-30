fun main(){
    val list = LinkedList<Int>()
    for(i in 0..10){
        list.add(i)
    }
    list.insert(5,2)
    list.delete(7)
    println(list.size())
    println(list[5])
    println(list)
    while(!list.isEmpty()){
        list.delete(0)
    }
    print(list)

}
data class Node<T>(var item:T, var next:Node<T>?=null) {
    override fun toString(): String {
        return if(next != null){
            "$item -> ${next.toString()}"
        }
        else{
            "$item"
        }
    }
}
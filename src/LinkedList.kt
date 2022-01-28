class LinkedList<T>{
    private var head: Node<T>?=null
    private var tail: Node<T>?=null
    private var size = 0
    fun size(): Int{
        return size
    }
    fun isEmpty():Boolean{
        return size == 0
    }
    fun add(item: T){
        if(tail == null) {
            head = Node(item, null)
            tail = head
        }
        else{
            tail!!.next = Node(item,null)
            tail = tail?.next
        }
        size++
    }
    fun insert(item: T, pos:Int){
        var currentNode = head
        var currentIndex = 0
        while(currentNode != null && currentIndex < pos-1){
            currentNode = currentNode.next
            currentIndex++
        }
        if(pos == 0){
            val newNode = Node(item, head)
            head = newNode
        }
        else if(currentNode == tail)
            this.add(item)
        else{
            val newNode = Node(item, currentNode?.next)
            currentNode?.next = newNode
        }
        size++
    }
    fun delete(pos:Int){
        var currentNode = head
        var currentIndex = 0
        if(pos == 0)
            head = head?.next
        else{
            while(currentNode != null && currentIndex < pos-1){
                currentNode = currentNode.next
                currentIndex++
            }
            currentNode?.next = currentNode?.next?.next
        }
        size--
    }
    operator fun get(pos: Int):String{
        var currentNode = head
        var currentIndex = 0
        while(currentNode != null && currentIndex<pos){
            currentNode = currentNode.next
            currentIndex++
        }
        return "${currentNode?.item}"
    }
    override fun toString(): String {
        return if(isEmpty()){
            "Empty list\n"
        } else{
            this.head.toString()
        }
    }
}
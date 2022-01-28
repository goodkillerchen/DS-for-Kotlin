import java.util.ArrayDeque


class Map<Key: Comparable<Key>,Value: Any> {
    private val RED = true
    private val BLACK = false
    private var root : Node<Key, Value> ?= null
    data class Node<Key, Value>(val key: Key, var value: Value, var color: Boolean, var size:Int){
        var left: Node<Key, Value>?=null
        var right: Node<Key, Value>?=null
        override fun toString(): String {
            return "${this.key} -> ${this.value}: ${this.color}"
        }
    }
    fun size(): Int{
        return size(root)
    }
    fun deleteMin(){
        if (!isRed(root?.left) && !isRed(root?.right))
            root!!.color = RED
        root = deleteMin(root)
        if(this.size()!=0)
            root?.color = BLACK
    }
    fun deleteMax(){
        if (!isRed(root?.left) && !isRed(root?.right))
            root!!.color = RED
        root = deleteMax(root)
        if(this.size()!=0)
            root?.color = BLACK
    }
    fun delete(){}
    private fun isRed(x: Node<Key, Value>?) = x?.let { it.color == RED }?:false
    private fun rotateLeft(x: Node<Key, Value>): Node<Key, Value>{
        val h = x.right
        x.right = h?.left
        h?.left = x
        h!!.color = x.color
        x.color = RED
        h.size = x.size
        x.size = size(x.left) + size(x.right) + 1
        return h
    }
    private fun rotateRight(x: Node<Key, Value>): Node<Key, Value>{
        val h = x.left
        x.left = h?.right
        h?.right = x
        h!!.color = x.color
        x.color = RED
        h.size = x.size
        x.size = size(x.left) + size(x.right) + 1
        return h
    }
    private fun flipColors(x: Node<Key, Value>){
        x.color = !x.color
        x.left?.color = !x.left?.color!!
        x.right?.color = !x.right?.color!!
    }
    private fun size(x: Node<Key, Value>?): Int{
        return x?.size ?: 0
    }
    private fun get(x: Node<Key, Value>?, key:Key): Node<Key, Value>?{
        if(x == null)
            return null
        val cmp = key.compareTo(x.key)
        return if(cmp > 0)
            get(x.right, key)
        else if(cmp < 0)
            get(x.left, key)
        else{
            x
        }
    }
    private fun set(x: Node<Key, Value>?, key:Key, value:Value): Node<Key, Value>{
        if(x == null){
            return Node(key, value, RED, 1)
        }
        val cmp = key.compareTo(x.key)
        if(cmp > 0)
            x.right = set(x.right, key, value)
        else if(cmp < 0)
            x.left = set(x.left, key, value)
        else{
            x.value = value
        }
        return balance(x)
    }
    private fun moveRedLeft(x: Node<Key, Value>?) {
        var x1 = x
        flipColors(x1!!)
        if(isRed(x1.right?.left)){
            x1.right = rotateRight(x1.right!!)
            x1 = rotateLeft(x1)
            flipColors(x1)
        }
    }
    private fun deleteMin(x: Node<Key, Value>?): Node<Key, Value>?{
        if(x!!.left == null)
            return null
        if(!isRed(x.left) && !isRed((x.left))){
            moveRedLeft(x)
        }
        x.left = deleteMin(x.left)
        return balance(x)
    }
    private fun deleteMax(x: Node<Key, Value>?): Node<Key, Value>?{
        var x1 = x
        if(isRed(x1?.left)){
            x1 = rotateRight(x1!!)
        }
        if(x1!!.right == null){
            return null
        }
        if(!isRed(x1.right) && !isRed((x1.right?.left))){
            moveRedRight(x1)
        }
        x1.right = deleteMax(x1.right)
        return balance(x1)
    }

    private fun moveRedRight(x: Map.Node<Key, Value>) {
        var x1 = x
        flipColors(x1)
        if(isRed(x1.left!!.left)){
            x1 = rotateRight(x1)
            flipColors(x1)
        }
    }

    private fun balance(x: Map.Node<Key, Value>): Node<Key, Value> {
        var x1= x
        if(isRed(x1.right) && !isRed(x1.left)){
            x1 = rotateLeft(x1)
        }
        if(isRed(x1.left) && isRed(x1.left?.left)){
            x1 = rotateRight(x1)
        }
        if(isRed(x1.right) && isRed(x1.left)){
            flipColors(x1)
        }
        x1.size = size(x1.left) + size(x1.right) + 1
        return x1
    }
    operator fun get(key:Key): Value?{
        return root?.let { get(it, key)?.value }
    }
    operator fun set(key: Key, value: Value){
        root = set(root, key, value)
        root!!.color = BLACK
    }
    override fun toString(): String {
        val strB = StringBuilder()
        if(root == null)
            return "Empty BST"
        val q = ArrayDeque<Node<Key, Value>>()
        q.push(root!!)
        while(!q.isEmpty()){
            val size = q.size
            for(i in 0 until size) {
                val now = q.first
                strB.append("$now ")
                q.pop()
                if (now.left != null) {
                    q.addLast(now.left!!)
                }
                if (now.right != null) {
                    q.addLast(now.right!!)
                }
            }
            strB.append("\n")
        }
        return strB.toString()
    }
}
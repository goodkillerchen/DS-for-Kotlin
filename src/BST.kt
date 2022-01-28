import java.util.*

class BST<Key: Comparable<Key>, Value : Any> {
    data class Node<Key, Value>(val key: Key, var value: Value){
        var left: Node<Key, Value>? = null
        var right: Node<Key, Value>? = null
        var size = 0
        override fun toString(): String {
            return "${this.key} -> ${this.value}"
        }
    }
    private var root: Node<Key, Value>?= null
    fun size(): Int {
        return size(root)
    }
    private fun size(x: Node<Key, Value>?): Int{
        return x?.size ?: 0
    }
    operator fun get(key: Key): Value? {
        return root?.let { get(key, it)?.value}
    }
    private fun get(key: Key, x: Node<Key, Value>?): Node<Key, Value>? {
        if (x==null)
            return null
        val cmp = x.key.let { key.compareTo(it) }
        return if(cmp > 0){
            get(key, x.right)
        } else if(cmp < 0){
            get(key, x.left)
        } else{
            x
        }
    }
    operator fun set(key:Key, value:Value){
        val currentNode = Node(key, value)
        root = set(root, currentNode)
    }
    private fun set(x: Node<Key, Value>?, currentNode: Node<Key, Value>): Node<Key, Value> {
        if(x == null){
            currentNode.size = 1
            return currentNode
        }
        val cmp = x.key.compareTo(currentNode.key)
        if(cmp < 0){
            x.right = set(x.right, currentNode)
        }
        else if(cmp > 0){
            x.left = set(x.left, currentNode)
        }
        else{
            x.value = currentNode.value
        }
        x.size = size(x.left) + size(x.right) + 1
        return x
    }
    fun min(): Node<Key,Value>?{
        return root?.let { min(it) }
    }
    fun max(): Node<Key, Value>?{
        return root?.let{max(it)}
    }
    private fun min(x:Node<Key,Value>): Node<Key, Value>{
        return if(x.left == null)
            x
        else
            min(x.left!!)
    }
    private fun max(x:Node<Key, Value>): Node<Key, Value>{
        return if(x.right == null)
            x
        else
            max(x.right!!)
    }
    fun deleteMin(){
        if(root == null)
            throw NoSuchElementException()
        root = deleteMin(root!!)
    }
    fun deleteMax(){
        if(root == null)
            throw NoSuchElementException()
        root = deleteMax(root!!)
    }
    fun delete(key: Key){
        if(root == null)
            throw NoSuchElementException()
        root = delete(root, key)
    }
    private fun deleteMin(x: Node<Key, Value>): Node<Key, Value>?{
        if (x.left == null){
            return x.right
        }else{
            x.left = deleteMin(x.left!!)
        }
        x.size = size(x.left) + size(x.right) + 1
        return x
    }
    private fun deleteMax(x: Node<Key, Value>): Node<Key, Value>?{
        if(x.right == null){
            return x.left
        }else{
            x.right = deleteMax(x.right!!)
        }
        x.size = size(x.left) + size(x.right) + 1
        return x
    }
    private fun delete(x: Node<Key, Value>?, key:Key): Node<Key, Value>?{
        if(x == null){
            return null
        }
        var x1 = x
        val cmp = key.compareTo(x.key)
        if(cmp > 0) {
            x.right = delete(x.right, key)
            x.size = size(x.left) + size(x.right) + 1
            return x
        }
        else if(cmp < 0) {
            x.left = delete(x.left, key)
            x.size = size(x.left) + size(x.right) + 1
            return x
        }
        else{
            if(x.left == null)
                return x.right
            if(x.right == null)
                return x.left
            val tmp: Node<Key, Value> = x1
            x1 = min(x1.right!!)
            x1.right = deleteMin(tmp.right!!)
            x1.left = tmp.left
            x1.size = size(x1.left) + size(x1.right) + 1
            return x1
        }
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
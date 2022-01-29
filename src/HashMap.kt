class HashMap<Key: Any, Value: Any>(capacity: Int?=null) {
    private var n = 0
    var m = capacity ?: 16
        private set
    private var keys = Array<Any?>(m) {null}
    private var value = Array<Any?>(m) {null}
    fun size():Int{
        return n
    }
    fun erase(key:Key){
        if(!contains(key))
            return
        var i = hash(key)
        while(key!=keys[i]){
            i = (i+1)%m
        }
        keys[i] = null
        value[i] = null
        while(keys[i] != null){
            val rehashKey = keys[i]
            val rehashValue = value[i]
            keys[i] = null
            value[i] = null
            n--
            @Suppress("UNCHECKED_CAST")
            set(rehashKey as Key, rehashValue as Value)
            i = (i+1)%m
        }
        n--
        if(n > 0 && n <= m/8){
            resize(m/2)
        }
    }
    private fun resize(capacity: Int) {
        val tmp = HashMap<Key,Value>(capacity)
        for(index in 0 until m){
            if(keys[index] != null) {
                @Suppress("UNCHECKED_CAST")
                tmp[keys[index] as Key] = value[index] as Value
            }
        }
        keys = tmp.keys
        value = tmp.value
        m = tmp.m

    }
    private fun hash(key:Key): Int{
        var h = key.hashCode()
        h = h xor (h ushr 20) xor (h ushr 12) xor (h ushr 7) xor (h ushr 4)
        return h and (m-1)
    }
    private fun contains(key:Key): Boolean{
        return get(key) != null
    }
    operator fun get(key:Key): Any? {
        var i = hash(key)
        while(keys[i] != null){
            if(keys[i] == key){
                return value[i]
            }
            i = (i+1)%m
        }
        return null
    }
    operator fun set(key: Key, value: Value){
        if(n >= m/2){
            resize(2*m)
        }
        var i = hash(key)
        while(keys[i] != null){
            if(keys[i] == key){
                this.value[i] = value
                return
            }
            i = (i+1)%m
        }
        keys[i] = key
        this. value[i] = value
        n++
    }

    override fun toString(): String {
        val strB = StringBuilder()
        for(index in 0 until m){
            keys[index]?.let { strB.append("$it -> ${value[index]}\n") } ?: continue
        }
        return strB.toString()
    }
}
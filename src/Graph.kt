class Graph(var V: Int, var E: Int){
    var adjMatrix = Matrix(V, V)
    var degree = Array<Int>(V){0}
    init {
        for(i in 1..E) {
            val (a, b) = readLine()!!.split(' ').map(String::toInt)
            adjMatrix.matrix[a][b] = 1
            adjMatrix.matrix[b][a] = 1
            degree[a]++
            degree[b]++
        }
    }
}
val ans = ArrayList<Matrix>()
fun main(args: Array<String>){
    while(true) {
        val nextLine = readLine().takeUnless {
            it.isNullOrEmpty()
        } ?: break
        val (V, E) = nextLine.split(' ').map(String::toInt)
        val graphA = Graph(V, E)
        val (V1, E1) = readLine()!!.split(' ').map(String::toInt)
        val graphB = Graph(V1, E1)
        val m = Matrix(V, V1)
        init(m, graphA, graphB)
        val visColumns = BooleanArray(V1){ false }
        //val getAns = ArrayList<Matrix>()
        dfs(graphA, graphB, visColumns, m, 0)
        if(ans.size > 0){
            for(item in ans){
                print(item)
            }
        }
        else{
            print("no\n")
        }

    }

}

fun init(m: Matrix, graphA: Graph, graphB: Graph){
    for(i in 1..graphA.V)
        for(j in 1..graphB.V){
            if(graphA.degree[i-1] <= graphB.degree[j-1])
                m.matrix[i-1][j-1] = 1
        }
}


fun dfs(graphA: Graph, graphB: Graph, visColumns: BooleanArray, m: Matrix, step: Int){
    if(step == graphA.V){
        for(i in 1..graphA.V){
            //println(m.matrix[i-1].sum())
            if (m.matrix[i-1].sum() != 1)
                return
        }
        ans.add(m.clone())
        return;
    }
    val m1 = refine(graphA, graphB, m.clone())
    for(i in 0 until step){
        if(m1.matrix[i].sum() == 0)
            return
    }
    val curRow = m1.matrix[step].clone()
    for(i in 1..visColumns.size){
        if(m1.matrix[step][i-1] == 1 && !visColumns[i-1]){
            visColumns[i-1] = true
            val newRow = IntArray(visColumns.size){0}
            newRow[i-1] = 1
            m1.matrix[step] = newRow
            dfs(graphA, graphB, visColumns, m1.clone(), step+1)
            m1.matrix[step] = curRow
            visColumns[i-1] = false
        }
    }
}

fun refine(graphA: Graph, graphB: Graph, m: Matrix): Matrix{
    for(i in 1..m.matrix.size)
        for(j in 1..m.matrix[0].size){
            if(m.matrix[i-1][j-1] == 1){
                for(k in 1..graphA.V){
                    var flag = false
                    if(graphA.adjMatrix.matrix[i-1][k-1] == 1){
                        for(l in 1..graphB.V){
                            if(graphB.adjMatrix.matrix[j-1][l-1] * m.matrix[k-1][l-1] == 1){
                                flag = true
                                break
                            }
                        }
                        if(!flag){
                            m.matrix[i-1][j-1] = 0
                        }
                    }
                }
            }
        }
    return m
}
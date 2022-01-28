class Matrix(var rows : Int, var columns : Int) {
    var matrix: Array<IntArray> = Array(rows){IntArray(columns) { 0 } }
    operator fun times(matrix1: Matrix) : Matrix{
        if (this.columns != matrix1.rows){
            fail("Matrix times fail")
        }
        val result = Matrix(rows, matrix1.columns)
        for (i in 1..rows)
            for(j in 1..matrix1.columns)
                for(k in 1..columns){
                    result.matrix[i-1][j-1] += this.matrix[i-1][k-1] * matrix1.matrix[k-1][j-1]
                }
        return result
    }

    override fun toString(): String {
        val strb = StringBuilder()
        for(i in 1..rows) {
            for (j in 1..columns) {
                strb.append(this.matrix[i-1][j-1])
                strb.append(' ')
            }
            strb.append('\n')
        }
        strb.append('\n')
        return strb.toString()
    }

    override fun equals(other: Any?): Boolean {
        other as Matrix
        if(other.rows != this.rows || other.columns != this.columns)
            return false
        else{
            for(i in 1..rows)
                for(j in 1..columns){
                    if(this.matrix[i-1][j-1] != other.matrix[i-1][j-1])
                        return false
                }
        }
        return true
    }
    fun transform(): Matrix{
        val trans_matrix = Matrix(columns, rows)
        for(i in 1..rows)
            for(j in 1..columns)
                trans_matrix.matrix[j-1][i-1] = this.matrix[i-1][j-1]
        return trans_matrix
    }
    private fun fail(message: String): Nothing{
        throw IllegalArgumentException(message)
    }
}
fun Matrix.clone(): Matrix{
    val mClone = Matrix(this.rows, this.columns)
    mClone.matrix = this.matrix.copy()
    return mClone
}
fun Array<IntArray>.copy() = map { it.clone() }.toTypedArray()
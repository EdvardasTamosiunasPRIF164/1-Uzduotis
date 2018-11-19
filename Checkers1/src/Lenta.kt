

import checker.Saske
import checker.Saskesej
import checker.SaskesSpalv

class Lenta {

    private var lenta = Array(8) {
        Array(8) {
            Lang()
        }
    }

    init {
        setUpGame()
    }

    private fun setUpGame() {
        for (row in 0..7) {
            for (col in 0..7) {
                if (row % 2 == col % 2) {
                    when {
                        row < 3 -> lenta[row][col].saske = Saske(SaskesSpalv.BLACK)
                        row > 4 -> lenta[row][col].saske = Saske(SaskesSpalv.RED)
                        else -> lenta[row][col].saske = null
                    }
                } else {
                    lenta[row][col].saske = null
                }
            }
        }
    }

    fun getBoardAsArray(): Array<Array<Lang>> {
        return lenta
    }

    fun getLenta(): Lenta {
        return this
    }

    fun makeMove(checkerMove: Saskesej){
        makeMove(checkerMove.r1,checkerMove.c1,checkerMove.r2,checkerMove.c2)
    }

    fun makeMove(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int) {

        lenta[toRow][toCol].saske = lenta[fromRow][fromCol].saske
        lenta[fromRow][fromCol].saske = null
        if (fromRow - toRow == 2 || fromRow - toRow == -2) {

            val jumpRow = (fromRow + toRow) / 2
            val jumpCol = (fromCol + toCol) / 2
            lenta[jumpRow][jumpCol].saske = null
        }
    }

    fun printBoard(){
        val number = arrayOf("8","7","6","5","4","3","2","1")
        val letters = arrayOf("A","B","C","D","E","F","G","H")
        lenta.forEachIndexed { index, arrayOfCells ->
            print(number[index])
            print("  ")
            arrayOfCells.forEachIndexed { index, cell ->
                cell.printOccupation()
                print(" ")
            }
            println()
        }
        print("  ")
        lenta.forEachIndexed { index, arrayOfCells ->
            print(" ")
            print(letters[index])
        }
    }
}
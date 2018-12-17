import checker.SaskesEjimas
import java.util.*

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        val input = Scanner(System.`in`)

        val game = Game()
        game.isGameOn = true
        game.Lenta.printBoard()

        println()
        println("Red starts")

        while (game.isGameOn){
            println()
            println("please enter current column")
            val column = input.nextLine()
            println("please enter current row")
            val row = input.nextLine()
            println("please enter destination column")
            val destinationColumn = input.nextLine()
            println("please enter destination row")

            val destinationRow = input.nextLine()
            val legalMoves = game.getLegalMoves(game.currentPlayer)
            val checkerMove = getMoveFromUserInput(row,column,destinationRow,destinationColumn)
            if (checkerMove != null){
                val isLegit = game.checkIfMoveIsLegit(checkerMove, legalMoves)
                if (isLegit){
                    game.doMakeMove(checkerMove)
                    game.Lenta.printBoard()
                }else{
                    printInvalidMove()
                }

            }else{
                game.Lenta.printBoard()
                printInvalidMove()

            }
        }
    }

    fun printInvalidMove(){
        println(" ")
        println("Incorrect move")
    }

    fun getMoveFromUserInput(r1: String, c1: String,destinationRow: String, destinationColumn: String): SaskesEjimas? {
        val number = arrayOf("8","7","6","5","4","3","2","1")
        val letters = arrayOf("A","B","C","D","E","F","G","H")
        val columnIndex = getItemIndex(c1, letters)
        val rowIndex = getItemIndex(r1, number)
        val destColumnIndex = getItemIndex(destinationColumn, letters)
        val destRowIndex = getItemIndex(destinationRow, number)
        columnIndex?.let {
            rowIndex?.let {
                destColumnIndex?.let {
                    destRowIndex?.let {
                        return SaskesEjimas (rowIndex, columnIndex, destRowIndex, destColumnIndex)
                    }
                }
            }
        }
        return null
    }

    private fun getItemIndex(item: String, array: Array<String>):Int?{
        array.forEachIndexed { index, s ->
            if (item == s){
                return index
            }
        }
        return null
    }
}

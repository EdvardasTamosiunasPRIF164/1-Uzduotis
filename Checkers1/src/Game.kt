import checker.Saske
import checker.Saskesej
import checker.SaskesSpalv
import java.util.*

class Game {
    val Lenta = Lenta()
    var isGameOn = false
    var currentPlayer: Saske = Saske(SaskesSpalv.RED)
    fun getLegalMoves(player: Saske): Array<Saskesej?>? {

        if (player.saskesSpalv != SaskesSpalv.RED && player.saskesSpalv != SaskesSpalv.BLACK)
            return null

        val moves = Vector<Saskesej>() // Moves will be stored in this vector.



        for (row in 0..7) {
            for (col in 0..7) {
                if (Lenta.getBoardAsArray()[row][col].saske?.saskesSpalv == player.saskesSpalv) {
                    if (canJump(player, row, col, row + 1, col + 1, row + 2,
                                    col + 2))
                        moves.addElement(Saskesej(row, col, row + 2,
                                col + 2))
                    if (canJump(player, row, col, row - 1, col + 1, row - 2,
                                    col + 2))
                        moves.addElement(Saskesej(row, col, row - 2,
                                col + 2))
                    if (canJump(player, row, col, row + 1, col - 1, row + 2,
                                    col - 2))
                        moves.addElement(Saskesej(row, col, row + 2,
                                col - 2))
                    if (canJump(player, row, col, row - 1, col - 1, row - 2,
                                    col - 2))
                        moves.addElement(Saskesej (row, col, row - 2,
                                col - 2))
                }
            }
        }



        if (moves.size == 0) {
            for (row in 0..7) {
                for (col in 0..7) {
                    if (Lenta.getBoardAsArray()[row][col].saske?.saskesSpalv == player.saskesSpalv) {
                        if (canMove(player, row, col, row + 1, col + 1))
                            moves.addElement(Saskesej(row, col,
                                    row + 1, col + 1))
                        if (canMove(player, row, col, row - 1, col + 1))
                            moves.addElement(Saskesej(row, col,
                                    row - 1, col + 1))
                        if (canMove(player, row, col, row + 1, col - 1))
                            moves.addElement(Saskesej (row, col,
                                    row + 1, col - 1))
                        if (canMove(player, row, col, row - 1, col - 1))
                            moves.addElement(Saskesej (row, col,
                                    row - 1, col - 1))
                    }
                }
            }
        }



        return if (moves.size == 0)
            null
        else {
            val moveArray = arrayOfNulls<Saskesej>(moves.size)
            for (i in moves.indices)
                moveArray[i] = moves.elementAt(i) as Saskesej
            moveArray
        }

    } // end getLegalMoves

    private fun canJump(player: Saske, r1: Int, c1: Int, r2: Int, c2: Int, r3: Int,
                        c3: Int): Boolean {

        if (r3 < 0 || r3 >= 8 || c3 < 0 || c3 >= 8)
            return false

        if (Lenta.getBoardAsArray()[r3][c3].saske != null)
            return false

        if (player.saskesSpalv == SaskesSpalv.RED) {
            if (Lenta.getBoardAsArray()[r1][c1].saske?.saskesSpalv == SaskesSpalv.RED && r3 > r1)
                return false
            return Lenta.getBoardAsArray()[r2][c2].saske?.saskesSpalv === SaskesSpalv.BLACK

        } else {
            if (Lenta.getBoardAsArray()[r1][c1].saske?.saskesSpalv == SaskesSpalv.BLACK && r3 < r1)
                return false
            return Lenta.getBoardAsArray()[r2][c2].saske?.saskesSpalv == SaskesSpalv.RED

        }

    }

    private fun canMove(player: Saske, r1: Int, c1: Int, r2: Int, c2: Int): Boolean {


        if (r2 < 0 || r2 >= 8 || c2 < 0 || c2 >= 8)
            return false

        if (Lenta.getBoardAsArray()[r2][c2].saske != null)
            return false

        return if (player.saskesSpalv == SaskesSpalv.RED) {
            !(Lenta.getBoardAsArray()[r1][c1].saske?.saskesSpalv == SaskesSpalv.RED && r2 > r1)

        } else {
            !(Lenta.getBoardAsArray()[r1][c1].saske?.saskesSpalv == SaskesSpalv.BLACK && r2 < r1)

        }

    }

    fun checkIfMoveIsLegit(userMove: Saskesej, legalMoves: Array<Saskesej?>?): Boolean{
        legalMoves?.forEach {
            it?.let {saskesej ->
                if (userMove == saskesej){
                    return true
                }
            }
        }
        return false
    }

    fun doMakeMove(move: Saskesej) {

        Lenta.makeMove(move)



        if (move.isJump()) {
            val legalMoves = getLegalJumpsFrom(currentPlayer, move.r2, move.c2)
            if (legalMoves != null) {
                if (currentPlayer.saskesSpalv == SaskesSpalv.RED)
                    println("RED:  You must continue jumping.")
                else
                    println("BLACK:  You must continue jumping.")

                return
            }
        }



        if (currentPlayer.saskesSpalv == SaskesSpalv.RED) {
            currentPlayer.saskesSpalv = SaskesSpalv.BLACK
            val legalMoves = getLegalMoves(currentPlayer)
            if (legalMoves == null)
                gameOver("BLACK has no moves.  RED wins.")
            else if (legalMoves[0]!!.isJump())
                println("BLACK:  Make your move.  You must jump.")
            else
                println("BLACK:  Make your move.")
        } else {
            currentPlayer.saskesSpalv = SaskesSpalv.RED
            val legalMoves = getLegalMoves(currentPlayer)
            if (legalMoves == null)
                gameOver("RED has no moves.  BLACK wins.")
            else if (legalMoves[0]!!.isJump())
                println("RED:  Make your move.  You must jump.")
            else
                println("RED:  Make your move.")
        }



        val legalMoves = getLegalMoves(currentPlayer)
        if (legalMoves != null) {
            var sameStartSquare = true
            for (i in 1 until legalMoves.size)
                if (legalMoves[i]?.r1 != legalMoves[0]?.r1 || legalMoves[i]?.c1 != legalMoves[0]?.c1) {
                    sameStartSquare = false
                    break
                }
            if (sameStartSquare) {

            }
        }



    }

    fun gameOver(str: String) {
        println(str)
        isGameOn = false
    }

    fun getLegalJumpsFrom(player: Saske, row: Int, col: Int): Array<Saskesej?>? {

        if (player.saskesSpalv != SaskesSpalv.RED && player.saskesSpalv != SaskesSpalv.BLACK)
            return null

        val moves = Vector<Saskesej>()
        val cellsChecker = Lenta.getBoardAsArray()[row][col].saske
        if ( cellsChecker == player) {
            if (canJump(player, row, col, row + 1, col + 1, row + 2, col + 2))
                moves.addElement(Saskesej(row, col, row + 2, col + 2))
            if (canJump(player, row, col, row - 1, col + 1, row - 2, col + 2))
                moves.addElement(Saskesej(row, col, row - 2, col + 2))
            if (canJump(player, row, col, row + 1, col - 1, row + 2, col - 2))
                moves.addElement(Saskesej(row, col, row + 2, col - 2))
            if (canJump(player, row, col, row - 1, col - 1, row - 2, col - 2))
                moves.addElement(Saskesej(row, col, row - 2, col - 2))
        }
        if (moves.size == 0)
            return null
        else {
            val moveArray = arrayOfNulls<Saskesej>(moves.size)
            for (i in moves.indices)
                moveArray[i] = moves.elementAt(i) as Saskesej
            return moveArray
        }
    }

}

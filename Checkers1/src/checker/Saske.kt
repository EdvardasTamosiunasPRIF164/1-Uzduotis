package checker

class Saske(var saskesSpalv: SaskesSpalv) {

    fun printSaske(){
        when(saskesSpalv){
            SaskesSpalv.BLACK -> print("B")
            SaskesSpalv.RED -> print("R")
        }
    }
}
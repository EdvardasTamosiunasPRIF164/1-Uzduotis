package checker

class Saske(var saskesSpalv: SaskesSpalv) : BaseSaske() {
    override fun printSaske(saskesSpalv: SaskesSpalv) {


            when(saskesSpalv){
                SaskesSpalv.BLACK -> print("B")
                SaskesSpalv.RED -> print("R")
            }
        }
    }




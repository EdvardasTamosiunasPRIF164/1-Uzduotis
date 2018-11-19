import checker.Saske

class Lang(var saske: Saske? = null) {

    fun printOccupation(){
        when(isOccupied()){
            true ->{
                saske?.printSaske()
            }
            false ->{
                print("_")
            }
        }
    }

    fun isOccupied():Boolean{
        if (saske != null){
            return true
        }
        return false
    }
}
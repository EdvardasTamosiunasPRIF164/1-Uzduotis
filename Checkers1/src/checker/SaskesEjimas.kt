package checker

class SaskesEjimas(val r1: Int, val c1: Int, val r2: Int, val c2: Int) {

    override fun equals(other: Any?): Boolean {
        if (other is SaskesEjimas){
            if (this.r1 == other.r1 &&
                    this.c1 == other.c1 &&
                    this.r2 == other.r2 &&
                    this.c2 == other.c2){
                return true
            }
            return false
        }else{
            return false
        }
    }

    fun isJump(): Boolean {

        return r1 - r2 == 2 || r1 - r2 == -2
    }
}
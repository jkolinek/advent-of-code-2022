package task17bv3

class Chamber {
    val fragments: MutableSet<Fragment> = mutableSetOf()

    fun moveRight(rock: Rock) {
        if (rock.maxX() == 7) {
            return
        }
        if (canMoveX(rock, +1)) {
            rock.x++
        }
        return
    }

    fun moveLeft(rock: Rock) {
        if (rock.minX() == 1) {
            return
        }
        if (canMoveX(rock, -1)) {
            rock.x--
        }
        return
    }

    fun moveDown(rock: Rock): Boolean {
        if (rock.minY() == 1L) {
            stop(rock)
            return false
        }
        val canMove = rock.fragments.stream()
            .noneMatch { fragments.contains(Fragment(it.x + rock.x, it.y + rock.y - 1)) }
        if (canMove) {
            rock.y--
            return true
        }
        stop(rock)
        return false
    }

    private fun stop(rock: Rock) {
        rock.fragments.forEach {
            fragments.add(Fragment(it.x + rock.x, it.y + rock.y))
        }
        println("rock $rock stopped")
//        printChamber()
//        readLine()!!
    }

    private fun canMoveX(rock: Rock, xChange: Int): Boolean {
        return rock.fragments.stream()
            .noneMatch { fragments.contains(Fragment(it.x + rock.x + xChange, it.y + rock.y)) }
    }

    fun maxY(): Long {
        return fragments.stream()
            .map { it.y }
            .max(Long::compareTo).orElse(0)
    }

    fun printChamber() {
        val maxY = maxY()
        for (y in maxY downTo 1 ) {
            for (x in 1..7) {
                val f = Fragment(x, y)
                if (fragments.contains(f)) {
                    print('#')
                } else {
                    print('.')
                }
            }
            println()
        }
    }

}

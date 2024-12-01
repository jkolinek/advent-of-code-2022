package task17bv2

class Chamber {
    val fragments: MutableMap<Long, MutableSet<StoppedFragment>> = mutableMapOf()
    var maxY = 0

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
        if (rock.y - 1 > maxY){
            rock.moveDown()
            return true
        }

        if (rock.minY() == 1L) {
            stop(rock)
            return false
        }

        val canMove = rock.parts.stream()
            .noneMatch {
                val yFragments: Set<StoppedFragment>? = fragments[it.y + rock.y - 1]
                yFragments == null || yFragments.contains(StoppedFragment(it.x + rock.x))
            }
        if (canMove) {
            rock.moveDown()
            return true
        }
        stop(rock)
        return false
    }

    private fun stop(rock: Rock) {
        rock.parts.forEach {
            if (fragments[it.y + rock.y] == null){
                fragments[it.y + rock.y] = HashSet(7)
                fragments[it.y + rock.y]!!.add(StoppedFragment(it.x + rock.x))
            }
        }
        maxY += 4 - rock.down + rock.height()
//        println("rock $rock stopped")
//        printChamber()
//        readLine()!!
    }

    private fun canMoveX(rock: Rock, xChange: Int): Boolean {
        if(rock.y > maxY) return true

        return rock.parts.stream()
            .noneMatch {
                val yFragments: Set<StoppedFragment>? = fragments[it.y + rock.y]
                yFragments == null || yFragments.contains(StoppedFragment(it.x + rock.x + xChange))
            }
    }

//    fun printChamber() {
//        for (y in maxY downTo 1 ) {
//            for (x in 1..7) {
//                val f = RockPart(x, y)
//                if (fragments.contains(f)) {
//                    print('#')
//                } else {
//                    print('.')
//                }
//            }
//            println()
//        }
//    }

    fun cleanUp() {
        val maxY = maxY
        val iterator = fragments.iterator()
        while (iterator.hasNext()) {
            if (iterator.next().key < maxY - 100) {
                iterator.remove()
            }
        }
    }

}

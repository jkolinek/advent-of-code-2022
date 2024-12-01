package task18b

class Ball(val cubes: List<Cube>) {

    val cubesByX = cubes.groupBy { it.x }
    val cubesByY = cubes.groupBy { it.y }
    val cubesByZ = cubes.groupBy { it.z }

    val maxX = cubes.stream()
        .map { it.x }
        .max(Int::compareTo).orElseThrow() + 1
    val minX = cubes.stream()
        .map { it.x }
        .min(Int::compareTo).orElseThrow() - 1
    val maxY = cubes.stream()
        .map { it.y }
        .max(Int::compareTo).orElseThrow() + 1
    val minY = cubes.stream()
        .map { it.y }
        .min(Int::compareTo).orElseThrow() - 1
    val maxZ = cubes.stream()
        .map { it.z }
        .max(Int::compareTo).orElseThrow() + 1
    val minZ = cubes.stream()
        .map { it.z }
        .min(Int::compareTo).orElseThrow() - 1

//    val surfaceAir: MutableSet<Cube> = mutableSetOf()
    val air: MutableSet<Cube> = mutableSetOf()

    fun surface(): Int {

//        scan(cubesByX, minX..maxX) { Cube(it.x - 1, it.y, it.z) }
//        scan(cubesByX, maxX downTo minX) { Cube(it.x + 1, it.y, it.z) }
//        scan(cubesByY, minY..maxY) { Cube(it.x, it.y - 1, it.z) }
//        scan(cubesByY, maxY downTo minY) { Cube(it.x, it.y + 1, it.z) }
//        scan(cubesByZ, minZ..maxZ) { Cube(it.x, it.y, it.z - 1) }
//        scan(cubesByZ, maxZ downTo minZ) { Cube(it.x, it.y, it.z + 1) }

//        println(surfaceAir)

        expand(Cube(minX, minY, minZ))

        println(air)

        return countSurface()
    }

    private fun countSurface(): Int {
        var count = 0
        for ((x, y, z) in air) {
            if (cubes.contains(Cube(x + 1, y, z))) count++
            if (cubes.contains(Cube(x - 1, y, z))) count++
            if (cubes.contains(Cube(x, y + 1, z))) count++
            if (cubes.contains(Cube(x, y - 1, z))) count++
            if (cubes.contains(Cube(x, y, z + 1))) count++
            if (cubes.contains(Cube(x, y, z - 1))) count++
        }
        return count
    }

//    private fun scan(
//        cubesBy: Map<Int, Collection<Cube>>,
//        intRange: Iterable<Int>,
//        movedCubeProvider: (Cube) -> Cube
//    ) {
//        for (axis in intRange) {
//            surfaceAir.addAll(
//                cubesBy[axis]!!.stream()
//                    .filter { isVisibleFromOutside(it, movedCubeProvider) }
//                    .map {
//                        movedCubeProvider.invoke(it)
//                    }
//                    .filter { !outOfBoundaries(it) }
//                    .toList())
//        }
//    }
//
//    private fun isVisibleFromOutside(cube: Cube, mover: (Cube) -> Cube): Boolean {
//        var nextSector = cube
//        while (true) {
//            nextSector = mover.invoke(nextSector)
//            if (cubes.contains(nextSector)) {
//                return false
//            }
//
//            if (outOfBoundaries(nextSector)) {
//                return true
//            }
//        }
//    }

    private fun outOfBoundaries(nextSector: Cube): Boolean {
        val (x, y, z) = nextSector
        return x > maxX || x < minX || y > maxY || y < minY || z > maxZ || z < minZ
    }


    fun expand(seed: Cube): Set<Cube> {
        if (!canExpand(seed)) return setOf()
        air.add(seed)
        val (x, y, z) = seed
        expand(Cube(x - 1, y, z)).let { air.addAll(it) }
        expand(Cube(x + 1, y, z)).let { air.addAll(it) }
        expand(Cube(x, y - 1, z)).let { air.addAll(it) }
        expand(Cube(x, y + 1, z)).let { air.addAll(it) }
        expand(Cube(x, y, z - 1)).let { air.addAll(it) }
        expand(Cube(x, y, z + 1)).let { air.addAll(it) }
        return air
    }

    fun canExpand(cube: Cube): Boolean {
        if (outOfBoundaries(cube)) {
            return false
        }
        if (air.contains(cube)) {
            return false
        }
        if (cubes.contains(cube)) {
            return false
        }

        return true
    }

}

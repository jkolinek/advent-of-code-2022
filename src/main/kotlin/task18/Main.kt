package task18

import java.nio.file.Files
import java.nio.file.Path

fun main() {
    val cubes = Files.lines(Path.of("src/main/resources/18.txt"))
        .map(String::trim)
        .map { it.split(",") }
        .map { Cube(it[0].toInt(), it[1].toInt(), it[2].toInt()) }
        .toList()

    val cubesByX = cubes.groupBy { it.x }
    val cubesByY = cubes.groupBy { it.y }
    val cubesByZ = cubes.groupBy { it.z }

    val maxX = cubes.stream()
        .map { it.x }
        .max(Int::compareTo).orElseThrow()
    val minX = cubes.stream()
        .map { it.x }
        .min(Int::compareTo).orElseThrow()
    val maxY = cubes.stream()
        .map { it.y }
        .max(Int::compareTo).orElseThrow()
    val minY = cubes.stream()
        .map { it.y }
        .min(Int::compareTo).orElseThrow()
    val maxZ = cubes.stream()
        .map { it.z }
        .max(Int::compareTo).orElseThrow()
    val minZ = cubes.stream()
        .map { it.z }
        .min(Int::compareTo).orElseThrow()

    var visible = 0
    visible += scanX(cubesByX, minX..maxX) * 2
    visible += scanY(cubesByY, minY..maxY) * 2
    visible += scanZ(cubesByZ, minZ..maxZ) * 2

    println(visible)


}

private fun scanX(
    cubesByX: Map<Int, Collection<Cube>>,
    intRange: Iterable<Int>
): Int {
    var visible = 0
    var previusSlice: Collection<Cube> = setOf()
    for (x in intRange) {
        val slice = cubesByX[x]
        val subtract = slice?.filter {!previusSlice.contains(Cube(it.x - 1, it.y, it.z))} ?: continue
        visible += subtract.size
        previusSlice = slice
    }
    return visible
}
private fun scanY(
    cubesByY: Map<Int, Collection<Cube>>,
    intRange: Iterable<Int>
): Int {
    var visible = 0
    var previusSlice: Collection<Cube> = setOf()
    for (y in intRange) {
        val slice = cubesByY[y]
        val subtract = slice?.filter {!previusSlice.contains(Cube(it.x, it.y - 1, it.z))} ?: continue
        visible += subtract.size
        previusSlice = slice
    }
    return visible
}private fun scanZ(
    cubesByZ: Map<Int, Collection<Cube>>,
    intRange: Iterable<Int>
): Int {
    var visible = 0
    var previusSlice: Collection<Cube> = setOf()
    for (z in intRange) {
        val slice = cubesByZ[z]
        val subtract = slice?.filter {!previusSlice.contains(Cube(it.x, it.y, it.z -1))} ?: continue
        visible += subtract.size
        previusSlice = slice
    }
    return visible
}



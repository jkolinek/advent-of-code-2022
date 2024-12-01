package taks8b
import taks8b.Tree
import java.nio.file.Files
import java.nio.file.Path


fun main() {
    val matrix = Files.lines(Path.of("src/main/resources/8.txt"))
        .map(String::trim)
        .map {
            it.toList().stream()
                .map { height -> Tree(height.digitToInt()) }
                .toList()
        }
        .toList()

    fun countVisibleLeft(r: Int, c: Int, height: Int): Int {
        var visibleTrees = 0
        for (sr in r - 1 downTo 0) {
            visibleTrees++
            if (matrix[c][sr].height >= height) break
        }
        return visibleTrees
    }

    fun countVisibleRight(r: Int, c: Int, height: Int): Int {
        var visibleTrees = 0
        for (sr in r + 1 until matrix[0].size) {
            visibleTrees++
            if (matrix[c][sr].height >= height) break
        }
        return visibleTrees
    }

    fun countVisibleBottom(r: Int, c: Int, height: Int): Int {
        var visibleTrees = 0
        for (sc in c + 1 until matrix.size) {
            visibleTrees++
            if (matrix[sc][r].height >= height) break
        }
        return visibleTrees
    }


    fun countVisibleTop(r: Int, c: Int, height: Int): Int {
        var visibleTrees = 0
        for (sc in c - 1 downTo 0) {
            visibleTrees++
            if (matrix[sc][r].height >= height) break
        }
        return visibleTrees
    }

    // rows
    for (c in matrix.indices) {
        for (r in matrix[c].indices) {
            val height = matrix[c][r].height
            matrix[c][r].score = countVisibleLeft(r, c, height) * countVisibleRight(r, c, height) * countVisibleTop(r, c, height) * countVisibleBottom(r, c, height)
        }
    }

    println(matrix)

    val max = matrix.stream()
        .flatMap { it.stream() }
        .max { o1, o2 -> o1.score.compareTo(o2.score) }

    println(max)


}



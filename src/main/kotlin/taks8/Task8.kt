package taks8
import java.nio.file.Files
import java.nio.file.Path


fun main() {
    val regexDir = """^dir (.+)""".toRegex()

    val matrix = Files.lines(Path.of("src/main/resources/8.txt"))
        .map(String::trim)
        .map {
            it.toList().stream()
                .map { height -> Tree(height.digitToInt()) }
                .toList()
        }
        .toList()

    // rows
    for (c in matrix.indices) {
        var rowLeftMax = Int.MIN_VALUE
        for (r in matrix[c].indices) {
            val tree = matrix[c][r]
            if (tree.height > rowLeftMax) {
                rowLeftMax = tree.height
                tree.visible = true
            }
        }

        var rowRightMax = Int.MIN_VALUE
        for (r in matrix[c].indices.reversed()) {
            val tree = matrix[c][r]
            if (tree.height > rowRightMax) {
                rowRightMax = tree.height
                tree.visible = true
            }
        }
    }

    // cols
    for (r in matrix[0].indices) {
        var colTopMax = Int.MIN_VALUE
        for (c in matrix.indices) {
            val tree = matrix[c][r]
            if (tree.height > colTopMax) {
                colTopMax = tree.height
                tree.visible = true
            }
        }

        var colBottomMax = Int.MIN_VALUE
        for (c in matrix.indices.reversed()) {
            val tree = matrix[c][r]
            if (tree.height > colBottomMax) {
                colBottomMax = tree.height
                tree.visible = true
            }
        }


    }


    println(matrix)

    val count = matrix.stream()
        .flatMap { it.stream() }
        .filter { it.visible == true }
        .count()

    println(count)


}

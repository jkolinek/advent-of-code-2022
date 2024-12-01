import java.nio.file.Files
import java.nio.file.Path


fun main() {
    val input = Files.readString(Path.of("src/main/resources/6.txt"))
    val indexOfFirst = input.toCharArray().asList().windowed(14)
        .indexOfFirst { it.toSet().size == 14 }

    println(indexOfFirst + 14)

}

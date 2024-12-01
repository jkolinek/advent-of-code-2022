import java.nio.file.Files
import java.nio.file.Path


fun main() {
    val input = Files.readString(Path.of("src/main/resources/6.txt"))
    val indexOfFirst = input.toCharArray().asList().windowed(4)
        .indexOfFirst { it.toSet().size == 4 }

    println(indexOfFirst + 4)

}

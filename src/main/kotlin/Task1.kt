import java.nio.file.Files
import java.nio.file.Path


fun main() {
    val lines = Files.lines(Path.of("src/main/resources/1.txt"))
        .map { str -> str.trim() }
        .toList()

    val sums = mutableListOf<Int>();
    var elf = 0
    for (line in lines) {
        if (line.isBlank()) {
            sums.add(elf)
            elf = 0
            continue
        }
        elf += Integer.parseInt(line)
    }

    sums.sortDescending()
    val top3 = sums.slice(IntRange(0, 2)).sum()

    println(top3)

}

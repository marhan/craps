package de.marhan.craps

class TestFiles {

    def readGame(fileName) {
        new File("src/test/resources/result/game/${fileName}.txt").readLines().join("\n")
    }

    def read(folder, fileName) {
        new File("src/test/resources/result/${folder}/${fileName}.txt").readLines().join("\n")
    }
}

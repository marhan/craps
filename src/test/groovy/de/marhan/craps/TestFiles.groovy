package de.marhan.craps

class TestFiles {

    def read(folder, fileName) {
        new File("src/test/resources/result/${folder}/${fileName}.txt").readLines().join("\n")
    }
}

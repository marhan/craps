package de.marhan.craps

class TestFiles {

    public read(fileName) {
        new File("src/test/resources/result/${fileName}.txt").readLines().join("\n")
    }
}

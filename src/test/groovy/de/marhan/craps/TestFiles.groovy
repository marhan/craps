package de.marhan.craps

class TestFiles {

    def static String LINE_ENDING = "\n"
    def folder

    TestFiles(folder) {
        this.folder = folder
    }

    def read(fileName) {
        new File("src/test/resources/result/${folder}/${fileName}.txt").readLines().join(LINE_ENDING)
    }
}

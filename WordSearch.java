import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class WordSearch {
    private final char[][] data;
    private final ArrayList<String> keyWords;

    private WordSearch(int rows, int cols) {
        data = new char[rows][cols];
        for (int r = 0; r <= rows - 1; r++) {
            for (int c = 0; c <= cols - 1; c++) {
                data[r][c] = '_';
            }
        }
        keyWords = new ArrayList<>();
    }

    private void clear() {
        for (int i = 0; i < data.length; i++) {
            for (int x = 0; x < data[i].length; i++) {
                data[i][x] = '_';
            }
        }
    }

    public String toString() {
        StringBuilder puzzle = new StringBuilder();
        for (char[] row : data) {
            StringBuilder line = new StringBuilder(" ");
            for (char col : row) {
                line.append(col).append(" ");
            }
            puzzle.append(line).append("\n");
        }
        return (puzzle.toString());
    }

    private boolean addWordHorizontal(String word, int row, int col) {
        StringBuilder check = new StringBuilder();
        try {
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == data[row - 1][col - 1 + i] || data[row - 1][col - 1 + i] == '_') {
                    check.append(word.charAt(i));
                    continue;
                }
                break;
            }
        } catch (Exception ArrayIndexOutOfBoundsException) {
            return false;
        }
        if (word.equals(check.toString())) {
            for (int i = 0; i < word.length(); i++) {
                data[row - 1][col - 1 + i] = word.charAt(i);
            }
            return true;
        }
        return false;
    }

    private boolean addWordVertical(String word, int row, int col) {
        StringBuilder check = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            try {
                if (word.charAt(i) == data[row - 1 + i][col - 1] || data[row - 1 + i][col - 1] == '_') {
                    check.append(word.charAt(i));
                    continue;
                }
                break;
            } catch (Exception ArrayIndexOutOfBoundsException) {
                return false;
            }
        }
        if (word.equals(check.toString())) {
            for (int i = 0; i < word.length(); i++) {
                data[row - 1 + i][col - 1] = word.charAt(i);
            }
            return true;
        }
        return false;
    }

    private boolean addWordDiagonal(String word, int row, int col) {
        if ((row - 1) + (word.length() - 1) - 1 < data.length) {
            if ((col - 1) + (word.length() - 1) - 1 < data.length) {
                StringBuilder check = new StringBuilder();
                for (int i = 0; i < word.length(); i++) {
                    try {
                        if (word.charAt(i) == data[row - 1 + i][col - 1 + i] || data[row - 1 + i][col - 1 + i] == '_') {
                            check.append(word.charAt(i));
                            continue;
                        }
                        break;
                    } catch (Exception ArrayIndexOutOfBoundsException) {
                        break;
                    }
                }
                if (word.equals(check.toString())) {
                    for (int i = 0; i < word.length(); i++) {
                        data[row - 1 + i][col - 1 + i] = word.charAt(i);
                    }
                    return true;
                }
            } else if ((col - 1) - (word.length() - 1) + 1 >= 0) {
                StringBuilder check = new StringBuilder();
                for (int i = 0; i < word.length(); i++) {
                    try {
                        if ((word.charAt(i) == data[row - 1 + i][col - 1 - i]) || (data[row - 1 + i][col - 1 + i] == '_')) {
                            check.append(word.charAt(i));
                            continue;
                        }
                        break;
                    } catch (Exception ArrayIndexOutOfBoundsException) {
                        break;
                    }
                }
                if (word.equals(check.toString())) {
                    for (int i = 0; i < word.length(); i++) {
                        data[row - 1 + i][col - 1 - i] = word.charAt(i);
                    }
                    return true;
                }
            }
        }
        if ((row - 1) - (word.length() - 1) + 1 >= 0) {
            if ((col - 1) + (word.length() - 1) - 1 < data.length) {
                StringBuilder check = new StringBuilder();
                for (int i = 0; i < word.length(); i++) {
                    try {
                        if (word.charAt(i) == data[row - 1 - i][col - 1 + i] || data[row - 1 + i][col - 1 + i] == '_') {
                            check.append(word.charAt(i));
                            continue;
                        }
                        break;
                    } catch (Exception ArrayIndexOutOfBoundsException) {
                        break;
                    }
                }
                if (word.equals(check.toString())) {
                    for (int i = 0; i < word.length(); i++) {
                        data[row - 1 - i][col - 1 + i] = word.charAt(i);
                    }
                    return true;
                }
            } else if ((col - 1) - (word.length() - 1) + 1 >= 0) {
                StringBuilder check = new StringBuilder();
                for (int i = 0; i < word.length(); i++) {
                    try {
                        if (word.charAt(i) == data[row - 1 - i][col - 1 - i] || data[row - 1 + i][col - 1 + i] == '_') {
                            check.append(word.charAt(i));
                            continue;
                        }
                        break;
                    } catch (Exception ArrayIndexOutOfBoundsException) {
                        break;
                    }
                }
                if (word.equals(check.toString())) {
                    for (int i = 0; i < word.length(); i++) {
                        data[row - 1 - i][col - 1 - i] = word.charAt(i);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private void addWords(int numOfWords) throws FileNotFoundException {
        Scanner s = new Scanner(new File("/Users/code/IdeaProjects/wordSearch/src/words.text"));
        ArrayList<String> words = new ArrayList<>();
        while (s.hasNextLine()) {
            words.add(s.nextLine());
        }
        s.close();

        int word = 0;
        while (word < numOfWords) {
            int random = (int) (Math.random() * 1000);
            int row = (int) (Math.random() * ((data.length - 1) + 1));
            int col = (int) (Math.random() * ((data[0].length - 1) + 1));
            int location = (int) (Math.random() * 3 + 1);

            if (location == 1) {
                if (addWordHorizontal(words.get(random), row, col)) {
                    keyWords.add(words.get(random));
                    word += 1;
                }
            } else if (location == 2) {
                if (addWordVertical(words.get(random), row, col)) {
                    keyWords.add(words.get(random));
                    word += 1;
                }
            } else if (location == 3) {
                if (addWordDiagonal(words.get(random), row, col)) {
                    keyWords.add(words.get(random));
                    word += 1;
                }
            }
        }
    }

    private void replaceBlanks() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char blank = '_';
        for (int i = 0; i < data.length; i++) {
            int length = 0;
            while (length < data[0].length) {
                if (data[i][length] == blank) {
                    int letter = (int) (Math.random() * 26);
                    data[i][length] = alphabet.charAt(letter);
                }
                length += 1;
            }
        }
    }

    private String printKeyWords() {
        StringBuilder wordlist = new StringBuilder("FIND THE FOLLOWING WORDS: \n");
        for (int i = 0; i < keyWords.size(); i++) {
            if (i % 4 != 0) {
                wordlist.append(keyWords.get(i)).append("     ");
            } else {
                wordlist.append(keyWords.get(i)).append("\n");
            }
        }
        return wordlist.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        WordSearch ws = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        ws.addWords(Integer.parseInt(args[2]));
        System.out.println(ws.toString());
        ws.replaceBlanks();
        System.out.println(ws.toString());
        System.out.println(ws.printKeyWords());
    }

}

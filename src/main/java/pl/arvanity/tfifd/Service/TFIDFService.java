package pl.arvanity.tfifd.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.arvanity.tfifd.Model.Frequency;
import pl.arvanity.tfifd.Model.Search;

import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TFIDFService {

    public static List<String> clearSpecials(String doc) {
        String spec = "[^a-zA-Z0-9]";

        doc = doc.replaceAll(spec, " ");
        String[] wordsArray = doc.split(" ");
        List<String> words = Arrays.asList(wordsArray);

        return words;
    }


    public static int countPresence(String searchedWord, List<String> docs) {
        int occurrences = 0;
        char[] letters = searchedWord.toCharArray();

        for (String doc : docs) {
            char[] docLetters = doc.toCharArray();

            for (char docLetter : docLetters) {

                for (char letter : letters) {
                    if (String.valueOf(letter).equalsIgnoreCase(String.valueOf(docLetter))) {
                        occurrences++;
                    }
                }
            }
        }
        return occurrences;
    }


    public static List<List<String>> getWordsSortedByLength(List<String> doc) {
        List<List<String>> wordsSortedByLength = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        int currLength = -1;

        doc.sort(Comparator.comparingInt(String::length));

        for (int i = 0; i < doc.size(); i++) {
            if (currLength == doc.get(i).length()) {
                temp.add(doc.get(i));
            } else {
                if (!temp.isEmpty()) {
                    wordsSortedByLength.add(temp);
                    temp = new ArrayList<>();
                }
                currLength = doc.get(i).length();
                temp.add(doc.get(i));
            }
        }
        wordsSortedByLength.add(temp);

        return wordsSortedByLength;
    }


    public static Frequency calculateFrequency(String docsWord, char[] searchedWord, int searchedWordTotalMatchesInDocument) {
        int totalMatches = 0;
        boolean appears = false;
        float frequency;
        List<String> sequence = new ArrayList<>();

        for (int i = 0; i < docsWord.length(); i++) {
            appears = false;

            for (char c : searchedWord) {
                if (c == docsWord.charAt(i)) {
                    sequence.add(String.valueOf(c));
                    appears = true;
                    totalMatches++;
                    break;
                }
            }
        }

        frequency = (float) totalMatches / searchedWordTotalMatchesInDocument;
        sequence = sequence.stream().distinct().collect(Collectors.toList());
        Frequency f = new Frequency(sequence, frequency, totalMatches, docsWord.length(), searchedWordTotalMatchesInDocument);

        return f;
    }

    public void calculate(Search search, HttpServletRequest req) {
        String str = search.getDocument();
        String word = search.getWordToSearch();
        List<Frequency> tfidf = new ArrayList<>();

        List<String> doc = clearSpecials(str);

        int searchedWordTotalMatchesInDocument = countPresence(word, doc);

        List<List<String>> sorted = getWordsSortedByLength(doc);

        char[] arrayWord = word.toLowerCase().toCharArray();

        for (List<String> text : sorted) {
            for (String sample : text) {
                tfidf.add(calculateFrequency(sample.toLowerCase(), arrayWord, searchedWordTotalMatchesInDocument));
            }
        }

        tfidf.sort(Comparator.comparing(Frequency::getFrequency));

        tfidf.removeIf(f -> f.getSearchedLettersTotalMatches() == 0);

        displayResult(tfidf, doc, req);

    }


    private static void displayResult(List<Frequency> tfidf, List<String> doc, HttpServletRequest req) {
        int allCharacters = 0;
        int totalMatches = 0;
        float totalFreq;
        List<String> result = new ArrayList<>();
        String summary = "";

        for (Frequency t : tfidf) {
            System.out.println(t);
            result.add(t.toString());
            totalMatches += t.getSearchedLettersTotalMatches();
        }

        for (String str : doc) {
            allCharacters += str.length();
        }

        totalFreq = (float) totalMatches / allCharacters;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setRoundingMode(RoundingMode.CEILING);
        String freq = (df.format(totalFreq));
        summary = "TOTAL Frequency: " + freq + " " + "(" + totalMatches + "/" + allCharacters + ")";
        result.add(summary);

        req.getSession().setAttribute("freq", tfidf);
        req.getSession().setAttribute("result", summary);
    }

    public void transform(MultipartFile file, String wordToSearch, HttpServletRequest req) throws IOException {
        String content = new String(file.getBytes());
        Search search = new Search(wordToSearch, content);
        calculate(search, req);
    }

    public void saveInFile(HttpServletRequest req) {
        List<Frequency> freq = (List<Frequency>) req.getSession().getAttribute("freq");
        String result = (String) req.getSession().getAttribute("result");

        try (PrintWriter writer = new PrintWriter(new FileWriter("output.txt"))) {
            for (Frequency f : freq) {
                writer.append(f.toString() + "\n");
            }
            writer.append(result);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}

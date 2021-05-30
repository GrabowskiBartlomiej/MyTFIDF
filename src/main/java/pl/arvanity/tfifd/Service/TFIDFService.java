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
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TFIDFService {


    public void calculateTfidf(Search search, HttpServletRequest req) {
        String document = search.getDocument().toLowerCase(Locale.ROOT);
        String wordToSearch = search.getWordToSearch().toLowerCase(Locale.ROOT);
        List<Frequency> tfidf = new ArrayList<>();
        char[] lettersToSearch = wordToSearch.toCharArray();

        List<String> wordsFromDocumentWithoutSpecials = clearSpecials(document);
        int totalOccurrencesOfSearchedWordInDocument = totalOccurrencesOfSearchedWordInDocument(lettersToSearch, document);

        for (String singleWordFromDocument : wordsFromDocumentWithoutSpecials) {
            tfidf.add(calculateFrequency(singleWordFromDocument, lettersToSearch, totalOccurrencesOfSearchedWordInDocument));
        }

        tfidf.removeIf(f -> f.getSearchedLettersTotalMatches() == 0);
        tfidf.sort(Comparator.comparing(Frequency::getFrequency));

        displayResult(tfidf, wordsFromDocumentWithoutSpecials, req);
    }


    public static List<String> clearSpecials(String doc) {
        String spec = "[^a-zA-Z0-9]";

        doc = doc.replaceAll(spec, " ");
        String[] wordsArray = doc.split(" ");
        List<String> words = Arrays.asList(wordsArray);

        return words;
    }


    public int totalOccurrencesOfSearchedWordInDocument(char[] lettersToSearch, String document) {
        int totalOccurrencesOfSearchedWordInDocument = 0;
        for (int i = 0; i < document.length(); i++) {
            for (char letter : lettersToSearch) {
                if (document.charAt(i) == letter) {
                    totalOccurrencesOfSearchedWordInDocument++;
                    break;
                }
            }
        }
        return totalOccurrencesOfSearchedWordInDocument;
    }


    public Frequency calculateFrequency(String singleWordFromDocument, char[] lettersToSearch, int totalOccurrencesOfSearchedWordInDocument) {
        int totalMatches = 0;
        boolean appears = false;
        float frequency;
        List<String> sequence = new ArrayList<>();

        for (int i = 0; i < singleWordFromDocument.length(); i++) {
            appears = false;
            for (char letter : lettersToSearch) {
                if (letter == singleWordFromDocument.charAt(i)) {
                    sequence.add(String.valueOf(letter));
                    appears = true;
                    totalMatches++;
                    break;
                }
            }
        }

        frequency = (float) totalMatches / totalOccurrencesOfSearchedWordInDocument;
        sequence = sequence.stream().distinct().collect(Collectors.toList());
        Frequency singleFrequency = new Frequency(sequence, frequency, totalMatches, singleWordFromDocument.length(), totalOccurrencesOfSearchedWordInDocument);

        return singleFrequency;
    }


    private static void displayResult(List<Frequency> tfidf, List<String> wordsFromDocumentWithoutSpecials, HttpServletRequest req) {
        int allCharacters = 0;
        int totalMatches = 0;
        float totalFreq;
        List<String> result = new ArrayList<>();
        String summary = "";

        for (Frequency t : tfidf) {
            result.add(t.toString());
            totalMatches += t.getSearchedLettersTotalMatches();
        }

        for (String word : wordsFromDocumentWithoutSpecials) {
            allCharacters += word.length();
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


    public void transformFileInString(MultipartFile file, String wordToSearch, HttpServletRequest req) throws IOException {
        String document = new String(file.getBytes());
        Search search = new Search(wordToSearch, document);
        calculateTfidf(search, req);
    }


    public void saveResultInFile(HttpServletRequest req) {
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

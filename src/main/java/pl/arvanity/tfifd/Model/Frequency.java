package pl.arvanity.tfifd.Model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Frequency {

    private List<String> sequence = new ArrayList<>();
    private float frequency;
    private int searchedLettersTotalMatches;
    private int documentsWordLength;
    private int searchedWordTotalMatchesInDocument;
    private String freq2;

    public Frequency() {
    }

    public Frequency(List<String> sequence, float frequency, int searchedLettersTotalMatches, int documentsWordLength, int searchedWordTotalMatchesInDocument) {
        this.sequence = sequence;
        this.frequency = frequency;
        this.searchedLettersTotalMatches = searchedLettersTotalMatches;
        this.documentsWordLength = documentsWordLength;
        this.searchedWordTotalMatchesInDocument = searchedWordTotalMatchesInDocument;
    }

    public List<String> getSequence() {
        return sequence;
    }

    public void setSequence(List<String> sequence) {
        this.sequence = sequence;
    }

    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    public int getSearchedLettersTotalMatches() {
        return searchedLettersTotalMatches;
    }

    public void setSearchedLettersTotalMatches(int searchedLettersTotalMatches) {
        this.searchedLettersTotalMatches = searchedLettersTotalMatches;
    }

    public int getDocumentsWordLength() {
        return documentsWordLength;
    }

    public void setDocumentsWordLength(int documentsWordLength) {
        this.documentsWordLength = documentsWordLength;
    }

    public int getSearchedWordTotalMatchesInDocument() {
        return searchedWordTotalMatchesInDocument;
    }

    public void setSearchedWordTotalMatchesInDocument(int searchedWordTotalMatchesInDocument) {
        this.searchedWordTotalMatchesInDocument = searchedWordTotalMatchesInDocument;
    }

    public String getFreq2() {
        return freq2;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        String freq = (df.format(frequency));
        this.freq2 = freq;
        return "{" + sequence + ", " + documentsWordLength + "} = " + freq + " (" + searchedLettersTotalMatches + "/" + searchedWordTotalMatchesInDocument + ")";
    }
}

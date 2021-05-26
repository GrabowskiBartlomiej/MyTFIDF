package pl.arvanity.tfifd.Model;

public class Search {

    private String wordToSearch;
    private String document;

    public Search() {
    }

    public Search(String wordToSearch, String document) {
        this.wordToSearch = wordToSearch;
        this.document = document;
    }

    public String getWordToSearch() {
        return wordToSearch;
    }

    public void setWordToSearch(String wordToSearch) {
        this.wordToSearch = wordToSearch;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "Search{" +
                "wordToSearch='" + wordToSearch + '\'' +
                ", document='" + document + '\'' +
                '}';
    }
}

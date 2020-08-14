package com.mycompany.app;


import java.util.List;

public class Term implements Comparable<Term>{

    private String content;
    private List<Term> childTermsArray;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setChildTermsArray(List<Term> childTermsArray) {
        this.childTermsArray = childTermsArray;
    }

    public List<Term> getChildTermsArray() {
        return childTermsArray;
    }

    public Term(String content) {
        this.content = content;
    }

    public boolean isSimple() {
        if (this.content.length() == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.content;
    }

    private String toWord() {
        if (this.isSimple()) {
            return this.content;
        }
        String word = "";
        for (int i = 0; i < this.content.length(); i++) {
            String one_symbol = this.content.substring(i, i + 1);
            if (!one_symbol.equals("+") && !one_symbol.equals(" ") && !one_symbol.equals("(") && !one_symbol.equals(")")) {
                word += one_symbol;
            }
        }
        return word;
    }

    public int compareTo(Term term) {
        return this.toWord().compareTo(term.toWord());
    }
}


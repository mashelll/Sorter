package com.mycompany.app;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TermsTree {

    private String expression;
    private Term rootTerm;

    public TermsTree(String expression) {
        this.expression = expression;
        this.rootTerm = new Term(this.expression);
        fillTree(rootTerm);
    }

    private void fillTree(Term term) {
        if (term.isSimple()) {
            return;
        }
        List<Term> termsArray = parseTerms(term);
        term.setChildTermsArray(termsArray);
        for (Term simpleTerm : term.getChildTermsArray()) {
            fillTree(simpleTerm);
        }
    }

    private boolean isCompleteBracketExpression(String str) {
        int check_bracket = 0;
        for (int i = 0; i < str.length(); i++) {
            if (check_bracket < 0) {
                return false;
            }
            String one_symbol = str.substring(i, i + 1);
            if (one_symbol.equals("(")) {
                check_bracket++;
            }
            if (one_symbol.equals(")")) {
                check_bracket--;
            }
        }
        if (check_bracket == 0) {
            return true;
        } else {
            return false;
        }
    }

    private List<Term> parseTerms(Term inputTerm) {
        String string = inputTerm.getContent();
        List<Term> termsArray = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            String one_symbol = string.substring(i, i + 1);
            Term term;
            String termString = "";
            if (!one_symbol.equals("+") && !one_symbol.equals(" ") && !one_symbol.equals("(") && !one_symbol.equals(")")) {
                term = new Term(string.substring(i, i + 1));
                termsArray.add(term);
            }
            if (one_symbol.equals("(")) {
                termString += one_symbol;
                for (int j = i + 1; !isCompleteBracketExpression(termString); j++) {
                    one_symbol = string.substring(j, j + 1);
                    termString += one_symbol;
                    i = j;
                }
                term = new Term(termString.substring(1, termString.length() - 1));
                termsArray.add(term);
            }
        }
        return termsArray;
    }

    public String sort() {
        sortTerm(this.rootTerm);
        return this.rootTerm.getContent();
    }

    private void sortTerm(Term term) {
        if (term.isSimple()) {
            return;
        }
        for (Term simpleTerm : term.getChildTermsArray()) {
            sortTerm(simpleTerm);
        }
        Collections.sort(term.getChildTermsArray());
        changeTermContent(term);
    }

    private void changeTermContent(Term term) {
        String newContent = "";
        for (int i = 0; i < term.getChildTermsArray().size() - 1; i++) {
            if (term.getChildTermsArray().get(i).isSimple()) {
                newContent += term.getChildTermsArray().get(i) + " " + "+" + " ";
            } else {
                newContent += "(" + term.getChildTermsArray().get(i) + ")" + " " + "+" + " ";
            }
        }
        int lastIndex = term.getChildTermsArray().size() - 1;
        if (term.getChildTermsArray().get(lastIndex).isSimple()) {
            newContent += term.getChildTermsArray().get(lastIndex);
        } else {
            newContent += "(" + term.getChildTermsArray().get(lastIndex) + ")";
        }
        term.setContent(newContent);
    }
}




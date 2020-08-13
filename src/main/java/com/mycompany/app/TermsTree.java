package com.mycompany.app;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class TermsTree {

    String expression;
    Term rootTerm;

    public TermsTree(String expression) {
        this.expression = expression;
        this.rootTerm = new Term(this.expression);
        fillTheTree(rootTerm);
    }


    private void fillTheTree(Term term) {
        if (term.isSimple()) {
            return;
        }
        ArrayList<Term> termsArray = parseTerms(term);
        term.childTermsArray = termsArray;
        for (Term simpleTerm : term.childTermsArray) {
            fillTheTree(simpleTerm);
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


    private ArrayList<Term> parseTerms(Term inputTerm) {
        String string = inputTerm.content;
        ArrayList<Term> termsArray = new ArrayList<>();
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

                term = new Term(termString.substring(1, termString.length()-1));
                termsArray.add(term);

            }
        }
        return termsArray;
    }


    public String sort() {
        sortTerm(this.rootTerm);
        return this.rootTerm.content;
    }

    private void sortTerm(Term term) {
        if (term.isSimple()) {
            return;
        }

        for (Term simpleTerm : term.childTermsArray) {
            sortTerm(simpleTerm);
        }

        Collections.sort(term.childTermsArray, new Comparator<Term>() {
            @Override
            public int compare(Term o1, Term o2) {
                return o1.compareTo(o2);
            }
        });
        changeTermContent(term);
    }

    public void changeTermContent(Term term) {

        String newContent = "";
        for (int i = 0; i < term.childTermsArray.size() - 1; i++) {
            if (term.childTermsArray.get(i).isSimple()) {
                newContent += term.childTermsArray.get(i) + " " + "+" + " ";
            } else {
                newContent += "(" + term.childTermsArray.get(i) + ")" + " " + "+" + " ";
            }

        }
        int lastIndex = term.childTermsArray.size() - 1;
        if (term.childTermsArray.get(lastIndex).isSimple()) {
            newContent += term.childTermsArray.get(lastIndex);
        } else {
            newContent += "(" + term.childTermsArray.get(lastIndex) + ")";
        }
        term.content = newContent;
    }

}




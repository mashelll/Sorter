package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class AppTest {

    @Test
    public void testStringWithoutBrackets() {
        String string = "A + A";
        TermsTree tree = new TermsTree(string);
        String outputString = tree.sort();
        assertEquals("A + A", outputString);
    }

    @Test
    public void testLongStringWithoutBrackets() {
        String string = "W + A + H + K + L";
        TermsTree tree = new TermsTree(string);
        String outputString = tree.sort();
        assertEquals("A + H + K + L + W", outputString);
    }

    @Test
    public void testStringWithBrackets() {
        String string = "D + A + (F + D)";
        TermsTree tree = new TermsTree(string);
        String outputString = tree.sort();
        assertEquals("A + D + (D + F)", outputString);
    }

    @Test
    public void testStringWithoutBracketsWithIncorrectOrder() {
        String string = "B + A";
        TermsTree tree = new TermsTree(string);
        String outputString = tree.sort();
        assertEquals("A + B", outputString);
    }

    @Test
    public void testStringWithoutBracketsWithAAndZ() {
        String string = "Z + A";
        TermsTree tree = new TermsTree(string);
        String outputString = tree.sort();
        assertEquals("A + Z", outputString);
    }

    @Test
    public void testStringWithBracketsWithTwoBrackets() {
        String string = "(D + A + C) + (F + D)";
        TermsTree tree = new TermsTree(string);
        String outputString = tree.sort();
        assertEquals("(A + C + D) + (D + F)", outputString);
    }

    @Test
    public void testStringWithBracketsComplicatedBrackets() {
        String string = "((A + D) + (A + (D + B)) + C)";
        TermsTree tree = new TermsTree(string);
        String outputString = tree.sort();
        assertEquals("((A + (B + D)) + (A + D) + C)", outputString);
    }
}

/*
 *  Copyright 2016 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.google.engedu.anagrams;

import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();

    private HashMap<String, ArrayList<String>> lettersToWord = new HashMap<>();
    private List<String> words = new ArrayList<String>();

    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            //
            //  Your code here
            //
            String sorted = sortLetters(word);
            if (!lettersToWord.containsKey(sorted)) {
                lettersToWord.put(sorted, new ArrayList());
            }
            lettersToWord.get(sorted).add(word);
            words.add(word);
        }
    }

    /**
     * Check if words are anagrams but not the same words
     * @param word
     * @param base
     * @return boolean
     */
    public boolean isGoodWord(String word, String base) {
        if (word.toUpperCase().equals(base.toUpperCase())){
            return false;
        }
        //if anagrams but not the same
        return getAnagramsWithOneMoreLetter(base).contains(word);
    }


    /**
     * go through the list tracker and only get the ones that are the anagrams
     * @param targetWord
     * @return
     */
    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        //
        // Your code here
        //
        String sorted = sortLetters(targetWord);
        /*for (String word : words ) {
            if (sortLetters(word).equals(sorted)) {
                result.add(word);
            }
        }*/
        if (!lettersToWord.containsKey(sorted)) {
            return new ArrayList<>();
        }
        return lettersToWord.get(sorted);
    }

    @VisibleForTesting
    static boolean isAnagram(String first, String second) {
        //
        // Your code here
        //
        //sort both words
        return sortLetters(first).equals(sortLetters(second));
    }

    @VisibleForTesting
    static String sortLetters(String input) {
        char[] chars = input.toCharArray();
        Arrays.sort(chars);

        return (new String(chars));
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        //
        // Your code here
        //
        //
        String sorted = sortLetters(word).toLowerCase();
        String temp;
        for(int i = 'a' ; i <= 'z'; i++ ) {
            temp = sortLetters(sorted + (char)i);
            result.addAll(getAnagrams(temp));

        }


        return result;
    }

    public String pickGoodStarterWord() {
        //
        // Your code here
        //
       /* random.setSeed(DEFAULT_WORD_LENGTH);
        int randomLength = random.nextInt(MAX_WORD_LENGTH);
        System.out.println(randomLength);
        Set<String> iterator = lettersToWord.keySet();
        String startWord = "stop";
        for(String word : iterator) {
            if (word.length() == randomLength) {
                if (lettersToWord.get(word).size() >= MIN_NUM_ANAGRAMS) {
                    startWord = lettersToWord.get(word).get(randomLength);
                    break;
                }
            }
        }
8*/
        List<String> anagrams = new ArrayList<>();
        String word = "stop";
        do {
            word = words.get(Math.abs(random.nextInt())%words.size());
            if (word.length() < DEFAULT_WORD_LENGTH || word.length() > MAX_WORD_LENGTH) {
                continue;
            }
            anagrams = getAnagramsWithOneMoreLetter(word);
        } while (anagrams.size() < MIN_NUM_ANAGRAMS);
        return word;
    }
}

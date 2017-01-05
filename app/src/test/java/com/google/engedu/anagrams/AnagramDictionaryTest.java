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

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


/**
 * Tests for AnagramDictionary
 */

public class AnagramDictionaryTest{


    private AnagramDictionary anagrams;

    @Before
    public void buildDictionary() throws Exception {
        //we know the words so much better
        InputStream inputStream = new ByteArrayInputStream((
                "stop\npouts\ntypists\ntypo\ntypography\ntypos\n"
        ).getBytes());
        anagrams = new AnagramDictionary(inputStream);
    }

    @Test
    public void getAnagrams() {
        assertArrayEquals(
                anagrams.getAnagrams("typo").toArray(),
                // using built in stuff is much better, show off java skills
                new String[]{"typo"}
        );
    }

    @Test
    public void getAnagramsWithOneMoreLetter() {
        assertArrayEquals(
                anagrams.getAnagramsWithOneMoreLetter("typo").toArray(),
                // using built in stuff is much better, show off java skills
                new String[]{"typos"}
        );
    }
    @Test
    public void testSortLetters() {

        assertEquals(AnagramDictionary.sortLetters("a"), "a");
        assertEquals(AnagramDictionary.sortLetters("spot"), "opst");
        assertEquals(AnagramDictionary.sortLetters("spot"), "post");

    }

    @Test
    public void testIsAnagram() {

        assertTrue(AnagramDictionary.isAnagram("a", "a"));
        assertTrue(AnagramDictionary.isAnagram("stop", "pots"));
        //make sure it fails
        assertFalse(AnagramDictionary.isAnagram("stop", "start"));
        // just make sure that one of them fails, but remove it later assertTrue(AnagramDictionary.isAnagram("stop", "start"));
    }

    @Test
    public void testIsGoodWord() {
       // TODO: This may need to be in AndroidTest

    }

}

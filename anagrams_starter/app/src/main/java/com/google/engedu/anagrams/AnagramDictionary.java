package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Arrays;




public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;

    HashSet<String> wordSet = new HashSet<>();
    HashMap<String, ArrayList<String>> lettersToWord = new HashMap<>();

    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;

        while((line = in.readLine()) != null) {

            String word = line.trim();
            wordSet.add(word);

            //if key does not exist, do this
            if(!lettersToWord.containsKey(sortLetters(word))){

                lettersToWord.put(sortLetters(word), new ArrayList<String>());
                lettersToWord.get(sortLetters(word)).add(word);

            }

            //if key exists, do this
            else{
                lettersToWord.get(sortLetters(word)).add(word);
            }
        }
    }

    public boolean isGoodWord(String word, String base) {

        String match;
        Boolean flag = false ;

        Iterator<String> iterator = wordSet.iterator();

        while(iterator.hasNext())
        {
            match = iterator.next();
            if(word.equals(match)){
                flag = true;
                break;
            }

        }

        return (flag && !word.contains(base));
    }

    public ArrayList<String> getAnagrams(String targetWord) {

        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> shortResult = new ArrayList<>();
        String[] oneMoreLetter = new String[30];
        String get_word;
        int i;


        get_word = pickGoodStarterWord();


        //This loop appends one extra letter
        for(i = 0;i<26;i++){

            oneMoreLetter[i] = get_word+(Character.toString((char)(((int)'a')+i)));
        }

        for(i=0; i<26;i++){

            oneMoreLetter[i] = sortLetters(oneMoreLetter[i]);

            if(lettersToWord.containsKey(oneMoreLetter[i])){

                shortResult = lettersToWord.get(oneMoreLetter[i]);
                result.addAll(shortResult);

            }
        }
        return result;
    }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {

        ArrayList<String> result = new ArrayList<>();

        return result;
    }

    public String pickGoodStarterWord() {

        //Words with at least 5 anagrams
        ArrayList<String> goodAnagrams = new ArrayList<>();

        for(String key : lettersToWord.keySet()){

            if(lettersToWord.get(key).size()>4){
                goodAnagrams.addAll(lettersToWord.get(key));
            }

        }
//
//        String[] stockArr = new String[goodAnagrams.size()];
//        stockArr =




        return "badge";  //Put your word here
    }

    //Function that takes up a string and returns sorted string
    public String sortLetters(String word){

        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        return sorted;
    }
}

package data.isbukunbunun.parser;

import java.util.List;
import java.util.ArrayList;

public class Parser 
{
    //IPA Vowels
    public static final List<Character> IPA_VOWELS = new ArrayList<>();
    
    //Global Variables
    private String input;
    private String output = "";

    private List<String> syllables;
    
    public void setup(String input)
    {
        this.input = input + "-()";
        this.syllables = new ArrayList<>(){};
        
        char[] vowelArray = {'i', 'y', 'ɨ', 'ʉ', 'ɯ', 'u', 'ɪ', 'ʏ', 'ʊ', 'e', 'ø', 'ɘ', 'ɵ', 'ɤ', 'o', 'ə', 'ɛ', 'œ', 'ɜ', 'ɞ', 'ʌ', 'ɔ', 'ɐ', 'æ', 'a', 'ɶ', 'ɑ', 'ɒ'};
       
        for (char vowel : vowelArray)
            IPA_VOWELS.add(vowel);


        StringBuilder syllable = new StringBuilder();

        for (int index = 0; index < this.input.length(); index++)
        {
            if (IPA_VOWELS.contains(this.input.charAt(index)))
            {
                if (this.input.charAt(index - 1) == 'j')
                    syllable.append(this.input.charAt(index - 2));
                
                syllable.append(this.input.charAt(index - 1)).append(this.input.charAt(index));

                if (this.input.charAt(index + 1) == ':' || (!IPA_VOWELS.contains(this.input.charAt(index + 2)) && this.input.charAt(index + 1) != '-'))
                    syllable.append(this.input.charAt(index + 1));

                if ((this.input.charAt(index + 1) == 'j' || this.input.charAt(index + 1) == 'w') && this.input.charAt(index + 2) != '-')
                    syllable.append(this.input.charAt(index + 2));

                this.syllables.add(syllable.toString());
                System.out.println(syllable);
                syllable.setLength(0);
            }

            if (this.input.charAt(index) == '-' && this.input.charAt(index + 1) == '(')
                break;
        }
    }

    public void parse()
    {
        
    }

    public String getOutput()
    {
        return this.output;
    }
}

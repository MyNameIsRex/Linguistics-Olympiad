package data.isbukunbunun.parser;

public class Parser 
{
    //IPA Vowels
    public static final char[] IPA_VOWELS = {'i', 'y', 'ɨ', 'ʉ', 'ɯ', 'u', 'ɪ', 'ʏ', 'ʊ', 'e', 'ø', 'ɘ', 'ɵ', 'ɤ', 'o', 'ə', 'ɛ', 'œ', 'ɜ', 'ɞ', 'ʌ', 'ɔ', 'ɐ', 'æ', 'a', 'ɶ', 'ɑ', 'ɒ'};
    
    //Global Variables
    private String input;
    private String output = "";
    
    public void setup(String input)
    {
        this.input = input + "-()";
    }

    public void parse()
    {
        
    }

    public String getOutput()
    {
        return this.output;
    }
}

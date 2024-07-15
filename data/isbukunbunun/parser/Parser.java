package data.isbukunbunun.parser;

import java.util.List;
import java.util.ArrayList;

public class Parser 
{
    //IPA Vowels
    public static final List<Character> IPA_VOWELS = new ArrayList<>();
    
    //Global Variables
    private String input;
    public String output = "";
    private List<String> syllableList = new ArrayList<>();

    private StringBuilder reproduction;
    
    public void setup(String input)
    {
        this.input = input;
        System.out.println("Lexical Word: /" + input + "/");
        this.reproduction = new StringBuilder();
        IPA_VOWELS.add('i');
        IPA_VOWELS.add('a');
        IPA_VOWELS.add('u');
    }

    public void createSyllableList()
    {
        StringBuilder currentSyllableBuilder = new StringBuilder();

        for (int charIndex = 0; charIndex < this.input.length(); charIndex++)
        {
            if (IPA_VOWELS.contains(this.input.charAt(charIndex)))
            {
                //[Vowel]
                currentSyllableBuilder.append(this.input.charAt(charIndex));

                //[Onset]
                //Consonant-Glide-Vowel-
                //t-ɕ-Vowel-
                if (charIndex - 2 >= 0 && (charIndex + 1 < this.input.length() && this.isHeavyCharacter(this.input.charAt(charIndex - 1)) ||
                    (this.input.charAt(charIndex - 2) == 't' && this.input.charAt(charIndex - 1) == 'ɕ')))
                    currentSyllableBuilder.insert(0, this.input.charAt(charIndex - 1)).insert(0, this.input.charAt(charIndex - 2));
                
                //t-ɕ-Glide-Vowel-
                if (charIndex - 3 >= 0 && this.input.charAt(charIndex - 3) == 't' && this.input.charAt(charIndex - 2) == 'ɕ')
                    currentSyllableBuilder.insert(0, this.input.charAt(charIndex - 3));
                
                //Consonant-Vowel-
                if (charIndex - 1 >= 0 && (!this.isHeavyCharacter(this.input.charAt(charIndex - 1))) && !(this.input.charAt(charIndex - 1) == 'ɕ'))
                    currentSyllableBuilder.insert(0, this.input.charAt(charIndex - 1));
                
                //[Coda]
                //-Vowel-Glide
                //-Vowel-Consonant
                if (charIndex + 1 < this.input.length() && (this.isHeavyCharacter(this.input.charAt(charIndex + 1))) || 
                    charIndex + 1 == this.input.length() - 1 ||
                    (charIndex + 2 < this.input.length()) && (!this.isHeavyCharacter(this.input.charAt(charIndex + 2)) && !IPA_VOWELS.contains(this.input.charAt(charIndex + 2))))
                    currentSyllableBuilder.append(this.input.charAt(charIndex + 1));
                
                this.syllableList.add(currentSyllableBuilder.toString());
                currentSyllableBuilder.setLength(0);
            }
        }
    }

    public void parse()
    {
        this.reproduction.append("-('");

        int lastSyllableIndex = this.syllableList.size() - 1;
        
        //L(H)
        if (this.isHeavySyllable(this.syllableList.get(lastSyllableIndex)))
            this.reproduction.append(this.syllableList.get(lastSyllableIndex));
        
        //(LL) or (HL)
        if (!this.isHeavySyllable(this.syllableList.get(lastSyllableIndex)))
            this.reproduction.append(this.syllableList.get(lastSyllableIndex - 1)).append(this.syllableList.get(lastSyllableIndex));

        this.output = this.reproduction.insert(0, this.input).append(")").toString();
    }

    private boolean isHeavyCharacter(char c)
    {
        return c == 'w' || c == 'j' || c == ':';
    }

    private boolean isHeavySyllable(String s)
    {
        return s.contains("j") || s.contains("w") || s.contains(":");
    }
}

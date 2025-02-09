package javaTester;

import java.util.Random;

public class Topic_02_Email_Random {
    public  String getEmailAddress (){
        Random rand = new Random();
        return "hai" + rand.nextInt(99999) + "@gamil.com";
    }
}

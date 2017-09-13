package com.example;

import java.util.Random;

public class MyClass {

    private static int mLastRand = -1;

    public static String getJoke(){

        // Get a random number
        int max = mJokeArray.length;
        int rand = new Random().nextInt(max);

        // Make sure the random number doesn't repeat
        while(mLastRand == rand) rand = new Random().nextInt(max);
        mLastRand = rand;

        // Use the random number as an array index for joke array
        return mJokeArray[rand];
    }

    private static String[] mJokeArray = new String[]{

            "Q: When does a joke become a dad joke?\n" +
                    "A: When the punchline becomes apparent.",

            "Q: What do you call a dog that does magic tricks?\n" +
                    "A: A labracadabrador.",

            "Q: What's it called when a chameleon can't change its colors anymore?\n" +
                    "A: A reptile dysfunction.",

            "Q: Why did the cross-eyed teacher get fired?\n" +
                    "A: She couldn't control her pupils.",

            "I was wondering why my computer was getting so hot.\n\n" +
                    "Turns out it needed to vent.",

            "Q: Where do the poor meatballs live?\n" +
                    "A: The sphaghetto!",

            "Q: What did the sweet potato wear to bed?\n" +
                    "A: His pa-yam-as.",

            "As a wizard, I like turning things into glass.\n\n" +
                    "I just wanted to make that clear.",

            "Q:What do you call a blind dinosaur?\n" +
                    "A: Do-you-think-he-saurus",
    };
}
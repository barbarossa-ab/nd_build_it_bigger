package com.example;

import java.util.Random;

public class JokeGen {

    // taken from http://thoughtcatalog.com/christopher-hudspeth/2013/09/50-terrible-quick-jokes-thatll-get-you-a-laugh-on-demand/
    private static String[] jokes = {
            "It's hard to explain puns to kleptomaniacs because they always take things literally.",
            "I used to think the brain was the most important organ. Then I thought, look what's telling me that.",
            "The midget fortune teller who kills his customers is a small medium at large.",
            "A farmer in the field with his cows counted 196 of them, but when he rounded them up he had 200.",
            "What does a nosey pepper do? Get jalapeño business.",
            "What is Bruce Lee's favorite drink? Wataaaaah!",
            "The dyslexic devil worshipper sold his soul to Santa.",
            "You kill vegetarian vampires with a steak to the heart.",
            "There was a prison break and I saw a midget climb up the fence. As he jumped down her sneered at me and I thought, well that's a little condescending.",
            "If you want to catch a squirrel just climb a tree and act like a nut.So this guy with a premature ejaculation problem comes out of nowhere.",
            "A magician was walking down the street and turned into a grocery store.",
            "A blind man walks into a bar. And a table. And a chair.",
            "Why don't you ever see hippopotamus hiding in trees? Because they're really good at it.",
            "Did you hear about the Mexican train killer? He had locomotives.",
            "How does NASA organize their company parties? They planet.",
            "Why can't you hear a pterodactyl go to the bathroom? Because the \"P\" is silent.",
            "What kind of shoes do ninjas wear? Sneakers.",
            "Why does Snoop Dogg carry an umbrella? Fo' drizzle.",
            "Did you hear about the new corduroy pillows? They're making headlines everywhere!",
            "Why was six afraid of seven? Because seven was a well known six offender.",
            "What time is it when you have to go to the dentist? Tooth-hurtie."
    };

    public static String getJoke() {
        Random generator = new Random();
        int i = generator.nextInt(jokes.length);

        return jokes[i];
    }
}

package org.poc;

import org.poc.social.helpers.FacebookPageHelper;
import org.poc.social.helpers.InstagramHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static final String URL = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/96/Trident_C4_first_launch.jpg/481px-Trident_C4_first_launch.jpg";

    public static void main(String[] args) {

        logger.info("Starting App...");

        FacebookPageHelper facebookPageHelper = new FacebookPageHelper();

        //facebookPageHelper.postImage("L55-19-06-015.jpg", "/L55-19-06-015.jpg", "Hello World");

        InstagramHelper instagramHelper = new InstagramHelper();

       // instagramHelper.postIgImage(URL,"Hello World");

        logger.info("Closing App...");


    }
}
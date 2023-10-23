// Package for handling social connections.
package org.poc.social.connections;

// Imports necessary libraries and classes.
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.Page;
import com.restfb.types.User;
import com.restfb.types.instagram.IgUser;
import org.poc.social.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Enum Singleton for managing Facebook API connections.
public enum FacebookAPIConnection implements IFacebookAPIConnection {
    INSTANCE;  // Singleton instance

    // Logger for debugging and information.
    private static final Logger logger = LoggerFactory.getLogger(FacebookAPIConnection.class);

    // Facebook client for API interactions.
    private static FacebookClient facebookClient;

    // Public method to get the Facebook client.
    @Override
    public FacebookClient getFacebookClient() {
        // Lazy initialization for Facebook client.
        if (facebookClient == null) {
            facebookClient = createFacebookClient();
        }
        return facebookClient;
    }

    // Private method to create a new Facebook client.
    private static FacebookClient createFacebookClient() {

        // Log connection start.
        logger.info("Connecting to Fb...");

        // Initialize Facebook client with latest API version and token.
        facebookClient = new DefaultFacebookClient(Constants.FB_API_ACCESS_TOKEN, Version.LATEST);

        // Fetch current user data.
        User user = facebookClient.fetchObject("me", User.class);

        // Log  user name.
        logger.info("User name: " + user.getName());

        // Fetch specific page data using the page ID.
        Page page = facebookClient.fetchObject(Constants.FB_PAGE_ID, Page.class);

        // Log page name
        logger.info("Page name: " + page.getName());

        // Fields to fetch from Instagram profile.
        String profileFields = "biography,id,ig_id,followers_count,follows_count,media_count,name,profile_picture_url,username,website";

        // Fetch Instagram profile using user ID.
        IgUser igProfile = facebookClient.fetchObject(Constants.MY_IG_ID, IgUser.class, Parameter.withFields(profileFields));

        // Log Instagram username
        logger.info("Ig name: " + igProfile.getUsername());

        // Return the initialized Facebook client.
        return facebookClient;
    }
}
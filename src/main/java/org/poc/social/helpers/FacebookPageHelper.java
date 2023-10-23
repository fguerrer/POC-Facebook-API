// Package for helper classes related to social media.
package org.poc.social.helpers;

// Import required classes and packages.
import com.restfb.BinaryAttachment;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.GraphResponse;
import org.poc.social.connections.FacebookAPIConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.poc.social.Constants;

// Class to manage Facebook page posts.
public class FacebookPageHelper implements IFacebookPageHelper {

    // Logger for debugging and tracking.
    private static final Logger logger = LoggerFactory.getLogger(FacebookAPIConnection.class);

    // Method to post a message on a Facebook page.
    @Override
    public void postMessage(String message){
        // Log the start of message posting.
        logger.info("Posting Message to Fb page..");

        try {
            // Get FacebookClient from singleton instance.
            FacebookClient facebookClient = FacebookAPIConnection.INSTANCE.getFacebookClient();

            // Publish the message to the Facebook page.
            FacebookType response = facebookClient.publish(
                    Constants.FB_PAGE_ID +"/feed",
                    FacebookType.class,
                    Parameter.with("message", message)
            );

            // Print the ID of the published post.
            logger.info("Published post ID: " + response.getId());
        }
        catch(Exception e) {
            // Log any errors.
            logger.error(e.getMessage());
        }
    }

    // Method to post an image on a Facebook page.
    @Override
    public void postImage(String imgFilename, String imgURL, String imgMessage){
        // Log the start of image posting.
        logger.info("Posting Image to Fb page..");

        try {
            // Get FacebookClient from singleton instance.
            FacebookClient facebookClient = FacebookAPIConnection.INSTANCE.getFacebookClient();

            // Publish the image to the Facebook page.
            GraphResponse publishPhotoResponse = facebookClient.publish(
                    Constants.FB_PAGE_ID +"/photos",
                    GraphResponse.class,
                    BinaryAttachment.with(imgFilename, FacebookPageHelper.class.getResourceAsStream(imgURL)),
                    Parameter.with("message", imgMessage)
            );

            // Log the ID of the published photo.
            logger.info("Published photo ID: " + publishPhotoResponse.getId());

        }
        catch(Exception e) {
            // Log any errors.
            logger.error(e.getMessage());
        }
    }
}
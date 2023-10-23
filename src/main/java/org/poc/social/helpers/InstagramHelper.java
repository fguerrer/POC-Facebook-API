// Package for helper classes related to social media.
package org.poc.social.helpers;

// Import required classes and packages.
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.GraphResponse;
import org.poc.social.connections.FacebookAPIConnection;
import org.poc.social.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Class for Instagram-specific helper methods.
public class InstagramHelper implements IInstagramHelper {

    // Logger for debugging and tracking.
    private static final Logger logger = LoggerFactory.getLogger(InstagramHelper.class);

    // Method to post an image to Instagram.
    public void postIgImage(String imgURL, String imgMessage) {

        try {
            // Get FacebookClient from singleton instance.
            FacebookClient facebookClient = FacebookAPIConnection.INSTANCE.getFacebookClient();

            // Publish the image and get the media container ID.
            GraphResponse publishPhotoResponse = facebookClient.publish(
                    Constants.MY_IG_ID + "/media?image_url=" + imgURL,
                    GraphResponse.class,
                    Parameter.with("message", imgMessage));

            // Log the media container ID.
            logger.info(" Media Container ID: " + publishPhotoResponse.getId());

            // Publish the image.
            GraphResponse publishPhotoResponse2 = facebookClient.publish(
                    Constants.MY_IG_ID + "/Media_publish?creation_id=" + publishPhotoResponse.getId(),
                    GraphResponse.class,
                    Parameter.with("message", imgMessage));

            // Log the published photo ID.
            logger.info("Published photo ID: " + publishPhotoResponse2.getId());

            // Add a comment to the published photo.
            GraphResponse response = facebookClient.publish(
                    publishPhotoResponse2.getId() + "/comments",
                    GraphResponse.class,
                    Parameter.with("message", imgMessage));

            // Log the comment ID.
            logger.info("Published photo ID: " + response.getId());

        } catch (Exception e) {
            // Log any errors.
            logger.error(e.getMessage());
        }
    }
}
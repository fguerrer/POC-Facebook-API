package org.poc.social.helpers;

public interface IFacebookPageHelper {
    void postMessage(String message);

    void postImage(String imgFilename, String imgURL, String imgMessage);
}

// Package for managing social connections.
package org.poc.social.connections;

// Import the FacebookClient class from the com.restfb package.
import com.restfb.FacebookClient;

// Interface for managing Facebook API connections.
public interface IFacebookAPIConnection {

    // Method signature for getting a FacebookClient instance.
    public FacebookClient getFacebookClient();
}
/**
 * 
 */
package com.connectthedots.service;

import java.util.logging.Logger;

import com.connectthedots.Constants;
import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthServiceFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;

/**
 * @author mvattipulusu
 * References: http://code.google.com/p/linkedin-j/wiki/OAuthFlow
 */
public class SignInService {
	
	//log
	private static final Logger log = Logger.getLogger(SignInService.class
			.getName());
	
	LinkedInOAuthService linkedInOAuthService = this.getLinkedInOAuthService();
	
	/**
	 * Returns a client which can be used to get profile/connections and other info.
	 * @param accessToken
	 * @return
	 */
	public LinkedInApiClient getlinkedInApiClient(LinkedInAccessToken accessToken){
		log.info("-- In getlinkedInApiClient()");
		final LinkedInApiClientFactory factory = LinkedInApiClientFactory.newInstance(Constants.CONSUMER_KEY_VALUE, Constants.CONSUMER_SECRET_VALUE);
		final LinkedInApiClient client = factory.createLinkedInApiClient(accessToken);
		return client;
	}
	/**
	 * Returns a service for making further calls like getting request token, access token.
	 * @return
	 */
	public LinkedInOAuthService getLinkedInOAuthService(){
		return LinkedInOAuthServiceFactory.getInstance().createLinkedInOAuthService(Constants.CONSUMER_KEY_VALUE, Constants.CONSUMER_SECRET_VALUE);
	}
	
	/**
	 * Gets Request Token, the callBackUrl should be our website mapping url. 
	 * @param callBackUrl
	 * @return
	 */
	public LinkedInRequestToken getLinkedInRequestToken(String callBackUrl){
		return this.linkedInOAuthService.getOAuthRequestToken(callBackUrl);
	}
	/**
	 * Returns accessToken which is long lived and will be storing into db. The accessToken is used to get Client. The accessToken will be stored in session also.
	 * @param requestToken
	 * @param oauthVerifier
	 * @return
	 */
	public LinkedInAccessToken getLinkedInAccessToken(LinkedInRequestToken requestToken, String oauthVerifier){
		return linkedInOAuthService.getOAuthAccessToken(requestToken, oauthVerifier);
	}
	
	
	
}

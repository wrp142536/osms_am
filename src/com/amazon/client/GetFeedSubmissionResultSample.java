/******************************************************************************* 
 *  Copyright 2009 Amazon Services.
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *  
 *  You may not use this file except in compliance with the License. 
 *  You may obtain a copy of the License at: http://aws.amazon.com/apache2.0
 *  This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
 *  CONDITIONS OF ANY KIND, either express or implied. See the License for the 
 *  specific language governing permissions and limitations under the License.
 * ***************************************************************************** 
 *
 *  Marketplace Web Service Java Library
 *  API Version: 2009-01-01
 *  Generated: Wed Feb 18 13:28:48 PST 2009 
 * 
 */



package com.amazon.client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.jws.WebService;
import javax.xml.ws.BindingType;

import com.amazonaws.mws.MarketplaceWebService;
import com.amazonaws.mws.MarketplaceWebServiceClient;
import com.amazonaws.mws.MarketplaceWebServiceConfig;
import com.amazonaws.mws.MarketplaceWebServiceException;
import com.amazonaws.mws.model.GetFeedSubmissionResultRequest;
import com.amazonaws.mws.model.GetFeedSubmissionResultResponse;
import com.amazonaws.mws.model.ResponseMetadata;



/**
 *
 * Get Feed Submission Result  Samples
 *
 *
 */
@WebService
@BindingType(value = "http://www.w3.org/2003/05/soap/bindings/HTTP/")
public class GetFeedSubmissionResultSample {

    /**
     * Just add a few required parameters, and try the service
     * Get Feed Submission Result functionality
     *
     * @param args unused
     * @throws FileNotFoundException 
     */
    public static void main(String... args) throws FileNotFoundException {

        /************************************************************************
         * Access Key ID and Secret Access Key ID, obtained from:
         * http://aws.amazon.com
         ***********************************************************************/
       /* final String accessKeyId = "<Your Access Key ID>";
        final String secretAccessKey = "<Your Secret Access Key>";

        final String appName = "<Your Application or Company Name>";
        final String appVersion = "<Your Application Version or Build Number or Release Date>";*/
    	
        final String accessKeyId = "AKIAIQTRLDH3SQFB3HSA";
        final String secretAccessKey = "k7ubQfyD5LSCA80miOj7TxeM5YAnMkU7GNWlz+tZ";

        final String appName = "sms";
        final String appVersion = "2";

        MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();

        /************************************************************************
         * Uncomment to set the appropriate MWS endpoint.
         ************************************************************************/
        // US
         config.setServiceURL("https://mws.amazonservices.com");
        // UK
        // config.setServiceURL("https://mws.amazonservices.co.uk");
        // Germany
        // config.setServiceURL("https://mws.amazonservices.de");
        // France
        // config.setServiceURL("https://mws.amazonservices.fr");
        // Italy
        // config.setServiceURL("https://mws.amazonservices.it");
        // Japan
        // config.setServiceURL("https://mws.amazonservices.jp");
        // China
        // config.setServiceURL("https://mws.amazonservices.com.cn");
        // Canada
        // config.setServiceURL("https://mws.amazonservices.ca");
        // India
        // config.setServiceURL("https://mws.amazonservices.in");

        /************************************************************************
         * You can also try advanced configuration options. Available options are:
         *
         *  - Signature Version
         *  - Proxy Host and Proxy Port
         *  - User Agent String to be sent to Marketplace Web Service
         *
         ***********************************************************************/

        /************************************************************************
         * Instantiate Http Client Implementation of Marketplace Web Service        
         ***********************************************************************/

        MarketplaceWebService service = new MarketplaceWebServiceClient(
                accessKeyId, secretAccessKey, appName, appVersion, config);

        /************************************************************************
         * Setup request parameters and uncomment invoke to try out 
         * sample for Get Feed Submission Result 
         ***********************************************************************/

        /************************************************************************
         * Marketplace and Merchant IDs are required parameters for all 
         * Marketplace Web Service calls.
         ***********************************************************************/
        final String merchantId = "AJ11J3FSAZ6XV";
        final String sellerDevAuthToken = "<Merchant Developer MWS Auth Token>";

        GetFeedSubmissionResultRequest request = new GetFeedSubmissionResultRequest();
        request.setMerchant( merchantId );
        //request.setMWSAuthToken(sellerDevAuthToken);
        
        request.setFeedSubmissionId( "50041016500" );

        
       // final IdList marketplaces = new IdList(Arrays.asList("ATVPDKIKX0DER"));

        //SubmitFeedRequest request = new SubmitFeedRequest();
        request.setMerchant(merchantId);
        //request.setMWSAuthToken(sellerDevAuthToken);
        request.setMarketplace("ATVPDKIKX0DER");
        // Note that depending on the size of the feed sent in, and the number of errors and warnings,
        // the result can reach sizes greater than 1GB. For this reason we recommend that you _always_ 
        // program to MWS in a streaming fashion. Otherwise, as your business grows you may silently reach
        // the in-memory size limit and have to re-work your solution.
        //
         OutputStream processingResult = new FileOutputStream( "feedSubmissionResult.xml" );
         request.setFeedSubmissionResultOutputStream( processingResult );

        //invokeGetFeedSubmissionResult(service, request);
    }



    public GetFeedSubmissionResultResponse getFeedSubmissionResult() throws MarketplaceWebServiceException{
    	
        final String accessKeyId = "AKIAIQTRLDH3SQFB3HSA";
        final String secretAccessKey = "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";

        final String appName = "sms";
        final String appVersion = "2";

        MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();

        /************************************************************************
         * Uncomment to set the appropriate MWS endpoint.
         ************************************************************************/
        // US
         config.setServiceURL("https://mws.amazonservices.com");
        // UK
        // config.setServiceURL("https://mws.amazonservices.co.uk");
        // Germany
        // config.setServiceURL("https://mws.amazonservices.de");
        // France
        // config.setServiceURL("https://mws.amazonservices.fr");
        // Italy
        // config.setServiceURL("https://mws.amazonservices.it");
        // Japan
        // config.setServiceURL("https://mws.amazonservices.jp");
        // China
        // config.setServiceURL("https://mws.amazonservices.com.cn");
        // Canada
        // config.setServiceURL("https://mws.amazonservices.ca");
        // India
        // config.setServiceURL("https://mws.amazonservices.in");

        /************************************************************************
         * You can also try advanced configuration options. Available options are:
         *
         *  - Signature Version
         *  - Proxy Host and Proxy Port
         *  - User Agent String to be sent to Marketplace Web Service
         *
         ***********************************************************************/

        /************************************************************************
         * Instantiate Http Client Implementation of Marketplace Web Service        
         ***********************************************************************/

        MarketplaceWebService service = new MarketplaceWebServiceClient(
                accessKeyId, secretAccessKey, appName, appVersion, config);

        /************************************************************************
         * Setup request parameters and uncomment invoke to try out 
         * sample for Get Feed Submission Result 
         ***********************************************************************/

        /************************************************************************
         * Marketplace and Merchant IDs are required parameters for all 
         * Marketplace Web Service calls.
         ***********************************************************************/
        final String merchantId = "AJ11J3FSAZ6XV";
        final String sellerDevAuthToken = "<Merchant Developer MWS Auth Token>";

        GetFeedSubmissionResultRequest request = new GetFeedSubmissionResultRequest();
        request.setMerchant( merchantId );
        //request.setMWSAuthToken(sellerDevAuthToken);
        
        request.setFeedSubmissionId( "50041016500" );

        
       // final IdList marketplaces = new IdList(Arrays.asList("ATVPDKIKX0DER"));

        //SubmitFeedRequest request = new SubmitFeedRequest();
        request.setMerchant(merchantId);
        //request.setMWSAuthToken(sellerDevAuthToken);
        request.setMarketplace("ATVPDKIKX0DER");
        // Note that depending on the size of the feed sent in, and the number of errors and warnings,
        // the result can reach sizes greater than 1GB. For this reason we recommend that you _always_ 
        // program to MWS in a streaming fashion. Otherwise, as your business grows you may silently reach
        // the in-memory size limit and have to re-work your solution.
        //
        /* OutputStream processingResult = new FileOutputStream( "feedSubmissionResult.xml" );
         request.setFeedSubmissionResultOutputStream( processingResult );*/
    	
    	 GetFeedSubmissionResultResponse response = service.getFeedSubmissionResult(request);
    	 return response;
    }
    
    /**
     * Get Feed Submission Result  request sample
     * retrieves the feed processing report
     *   
     * @param service instance of MarketplaceWebService service
     * @param request Action to invoke
     */
    private  void invokeGetFeedSubmissionResult(MarketplaceWebService service, GetFeedSubmissionResultRequest request) {
        try {

            GetFeedSubmissionResultResponse response = service.getFeedSubmissionResult(request);


            System.out.println ("GetFeedSubmissionResult Action Response");
            System.out.println ("=============================================================================");
            System.out.println ();

            System.out.print("    GetFeedSubmissionResultResponse");
            System.out.println();
            System.out.print("    GetFeedSubmissionResultResult");
            System.out.println();
            System.out.print("            MD5Checksum");
            System.out.println();
            System.out.print("                " + response.getGetFeedSubmissionResultResult().getMD5Checksum());
            System.out.println();
            if (response.isSetResponseMetadata()) {
                System.out.print("        ResponseMetadata");
                System.out.println();
                ResponseMetadata  responseMetadata = response.getResponseMetadata();
                if (responseMetadata.isSetRequestId()) {
                    System.out.print("            RequestId");
                    System.out.println();
                    System.out.print("                " + responseMetadata.getRequestId());
                    System.out.println();
                }
            } 
            System.out.println();

            System.out.println("Feed Processing Result");
            System.out.println ("=============================================================================");
            System.out.println();
            System.out.println( request.getFeedSubmissionResultOutputStream().toString() );
            System.out.println(response.getResponseHeaderMetadata());
            System.out.println();
            System.out.println();

        } catch (MarketplaceWebServiceException ex) {

            System.out.println("Caught Exception: " + ex.getMessage());
            System.out.println("Response Status Code: " + ex.getStatusCode());
            System.out.println("Error Code: " + ex.getErrorCode());
            System.out.println("Error Type: " + ex.getErrorType());
            System.out.println("Request ID: " + ex.getRequestId());
            System.out.print("XML: " + ex.getXML());
            System.out.println("ResponseHeaderMetadata: " + ex.getResponseHeaderMetadata());
        }
    }

}

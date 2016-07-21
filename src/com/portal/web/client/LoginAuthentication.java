
package com.portal.web.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "LoginAuthentication", targetNamespace = "http://tempuri.org/", wsdlLocation = "http://sso.geely.com/GeelyAuthService/LoginAuthentication.asmx?wsdl")
public class LoginAuthentication
    extends Service
{

    private final static URL LOGINAUTHENTICATION_WSDL_LOCATION;
    private final static WebServiceException LOGINAUTHENTICATION_EXCEPTION;
    private final static QName LOGINAUTHENTICATION_QNAME = new QName("http://tempuri.org/", "LoginAuthentication");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://sso.geely.com/GeelyAuthService/LoginAuthentication.asmx?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        LOGINAUTHENTICATION_WSDL_LOCATION = url;
        LOGINAUTHENTICATION_EXCEPTION = e;
    }

    public LoginAuthentication() {
        super(__getWsdlLocation(), LOGINAUTHENTICATION_QNAME);
    }

    public LoginAuthentication(WebServiceFeature... features) {
        super(__getWsdlLocation(), LOGINAUTHENTICATION_QNAME, features);
    }

    public LoginAuthentication(URL wsdlLocation) {
        super(wsdlLocation, LOGINAUTHENTICATION_QNAME);
    }

    public LoginAuthentication(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, LOGINAUTHENTICATION_QNAME, features);
    }

    public LoginAuthentication(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public LoginAuthentication(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns LoginAuthenticationSoap
     */
    @WebEndpoint(name = "LoginAuthenticationSoap")
    public LoginAuthenticationSoap getLoginAuthenticationSoap() {
        return super.getPort(new QName("http://tempuri.org/", "LoginAuthenticationSoap"), LoginAuthenticationSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns LoginAuthenticationSoap
     */
    @WebEndpoint(name = "LoginAuthenticationSoap")
    public LoginAuthenticationSoap getLoginAuthenticationSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://tempuri.org/", "LoginAuthenticationSoap"), LoginAuthenticationSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (LOGINAUTHENTICATION_EXCEPTION!= null) {
            throw LOGINAUTHENTICATION_EXCEPTION;
        }
        return LOGINAUTHENTICATION_WSDL_LOCATION;
    }

}


package com.portal.web.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.portal.web.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _String_QNAME = new QName("http://tempuri.org/", "string");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.portal.web.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReadTagAddressBySamAccountResponse }
     * 
     */
    public ReadTagAddressBySamAccountResponse createReadTagAddressBySamAccountResponse() {
        return new ReadTagAddressBySamAccountResponse();
    }

    /**
     * Create an instance of {@link ReletTokenResponse }
     * 
     */
    public ReletTokenResponse createReletTokenResponse() {
        return new ReletTokenResponse();
    }

    /**
     * Create an instance of {@link ReadTagAddressBySamAccount }
     * 
     */
    public ReadTagAddressBySamAccount createReadTagAddressBySamAccount() {
        return new ReadTagAddressBySamAccount();
    }

    /**
     * Create an instance of {@link QueryUserNeedCreateByAppkey }
     * 
     */
    public QueryUserNeedCreateByAppkey createQueryUserNeedCreateByAppkey() {
        return new QueryUserNeedCreateByAppkey();
    }

    /**
     * Create an instance of {@link UpdataUserNeedCreateByAppkey }
     * 
     */
    public UpdataUserNeedCreateByAppkey createUpdataUserNeedCreateByAppkey() {
        return new UpdataUserNeedCreateByAppkey();
    }

    /**
     * Create an instance of {@link UserAuthticationByCPCPhone }
     * 
     */
    public UserAuthticationByCPCPhone createUserAuthticationByCPCPhone() {
        return new UserAuthticationByCPCPhone();
    }

    /**
     * Create an instance of {@link UserAuthticationByAccountPwdResponse }
     * 
     */
    public UserAuthticationByAccountPwdResponse createUserAuthticationByAccountPwdResponse() {
        return new UserAuthticationByAccountPwdResponse();
    }

    /**
     * Create an instance of {@link MD5Encrypt }
     * 
     */
    public MD5Encrypt createMD5Encrypt() {
        return new MD5Encrypt();
    }

    /**
     * Create an instance of {@link ReletToken }
     * 
     */
    public ReletToken createReletToken() {
        return new ReletToken();
    }

    /**
     * Create an instance of {@link TokenAuthtication }
     * 
     */
    public TokenAuthtication createTokenAuthtication() {
        return new TokenAuthtication();
    }

    /**
     * Create an instance of {@link UpdataUserNeedCreateByAppkeyResponse }
     * 
     */
    public UpdataUserNeedCreateByAppkeyResponse createUpdataUserNeedCreateByAppkeyResponse() {
        return new UpdataUserNeedCreateByAppkeyResponse();
    }

    /**
     * Create an instance of {@link TokenAuthticationResponse }
     * 
     */
    public TokenAuthticationResponse createTokenAuthticationResponse() {
        return new TokenAuthticationResponse();
    }

    /**
     * Create an instance of {@link UserAuthticationByCPCPhoneResponse }
     * 
     */
    public UserAuthticationByCPCPhoneResponse createUserAuthticationByCPCPhoneResponse() {
        return new UserAuthticationByCPCPhoneResponse();
    }

    /**
     * Create an instance of {@link UserAuthticationByAccount }
     * 
     */
    public UserAuthticationByAccount createUserAuthticationByAccount() {
        return new UserAuthticationByAccount();
    }

    /**
     * Create an instance of {@link UserAuthticationByAccountResponse }
     * 
     */
    public UserAuthticationByAccountResponse createUserAuthticationByAccountResponse() {
        return new UserAuthticationByAccountResponse();
    }

    /**
     * Create an instance of {@link MD5Decrypt }
     * 
     */
    public MD5Decrypt createMD5Decrypt() {
        return new MD5Decrypt();
    }

    /**
     * Create an instance of {@link UserAuthticationByAccountPwd }
     * 
     */
    public UserAuthticationByAccountPwd createUserAuthticationByAccountPwd() {
        return new UserAuthticationByAccountPwd();
    }

    /**
     * Create an instance of {@link MD5EncryptResponse }
     * 
     */
    public MD5EncryptResponse createMD5EncryptResponse() {
        return new MD5EncryptResponse();
    }

    /**
     * Create an instance of {@link QueryUserNeedCreateByAppkeyResponse }
     * 
     */
    public QueryUserNeedCreateByAppkeyResponse createQueryUserNeedCreateByAppkeyResponse() {
        return new QueryUserNeedCreateByAppkeyResponse();
    }

    /**
     * Create an instance of {@link MD5DecryptResponse }
     * 
     */
    public MD5DecryptResponse createMD5DecryptResponse() {
        return new MD5DecryptResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

}

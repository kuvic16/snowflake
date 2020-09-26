package com.bracits.snowflake.security.auth.external;


import com.bracits.snowflake.entity.User;
import com.bracits.snowflake.security.AuthUser;
import com.bracits.snowflake.service.user.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/20/2020
 */
@Service
public class SSOService implements Transform {

    @Autowired
    private UserService userService;

    private final String characterEncoding = "UTF-8";
    private final String cipherTransformation = "AES/CBC/PKCS5Padding";
    private final String aesEncryptionAlgorithm = "AES";

    public boolean authenticate(String multipass, String key) {
        String decrypted = decrypt(multipass, key);
        String payload = decrypted.substring(key.length());
        SSOUser ssoUser = new SSOUser(payload);
        System.out.println(payload);
        if(ssoUser.isAuthenticated()) {
            AuthUser user = new AuthUser(this.getSystemUser(ssoUser));
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        }
        return false;
    }

    private User getSystemUser(SSOUser ssoUser) {
        User _user = userService.findByPin(ssoUser.getPin());
        if(_user == null) {
            _user = userService.createSSOUser(ssoUser);
        }
        return _user;
    }

    @Override
    public String encrypt(String payload, String key) {
        return null;
    }

    @Override
    public String decrypt(String multipass, String key) {
        try {
            byte[] cipheredBytes = Base64.decodeBase64(multipass);
            byte[] keyBytes = getKeyBytes(key);
            return new String(decryptString(cipheredBytes, keyBytes, keyBytes), characterEncoding);
        }catch (Throwable t) {

        }
        return "";
    }

    /**
     * Decrypt a string
     * @param cipherText
     * @param key
     * @param initialVector
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public byte[] decryptString(byte[] cipherText, byte[] key, byte[] initialVector) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(cipherTransformation);
        SecretKeySpec secretKeySpecy = new SecretKeySpec(key, aesEncryptionAlgorithm);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpecy, ivParameterSpec);
        cipherText = cipher.doFinal(cipherText);
        return cipherText;
    }

    /**
     * get Key bytes
     * @param key
     * @return
     * @throws UnsupportedEncodingException
     */
    private byte[] getKeyBytes(String key) throws UnsupportedEncodingException {
        byte[] keyBytes = new byte[16];
        byte[] parameterKeyBytes = key.getBytes(characterEncoding);
        System.arraycopy(parameterKeyBytes, 0, keyBytes, 0, Math.min(parameterKeyBytes.length, keyBytes.length));
        return keyBytes;
    }

    public static void main(String[] args) {
        String payload = "name:158010|password:|fullname:MONIKA PARVEEN|authenticated:true|authorization:true|login:true|platform:null|project:Microfinance (Dabi)|designation:Programme Organizer|joblevel:002|mobile:01770773051|request:login|ref:";

        SSOUser ssoUser = new SSOUser();
        System.out.println(payload.split("\\|"));
        for(String prop : payload.split("\\|")) {
            String[] propvalue = prop.split(":");
            if(propvalue.length == 2) {
                String key = propvalue[0];
                String value = propvalue[1];

                if(key.equalsIgnoreCase("name")) {
                    ssoUser.setPin(value);
                }

                if(key.equalsIgnoreCase("fullname")) {
                    ssoUser.setFullname(value);
                }

                if(key.equalsIgnoreCase("authenticated")) {
                    ssoUser.setAuthenticated(Boolean.parseBoolean(value));
                }

                if(key.equalsIgnoreCase("authorization")) {
                    ssoUser.setAuthorization(Boolean.parseBoolean(value));
                }

                if(key.equalsIgnoreCase("login")) {
                    ssoUser.setLogin(Boolean.parseBoolean(value));
                }

                if(key.equalsIgnoreCase("platform")) {
                    ssoUser.setPlatform(value);
                }

                if(key.equalsIgnoreCase("project")) {
                    ssoUser.setProject(value);
                }

                if(key.equalsIgnoreCase("designation")) {
                    ssoUser.setDesignation(value);
                }

                if(key.equalsIgnoreCase("joblevel")) {
                    ssoUser.setJoblevel(value);
                }

                if(key.equalsIgnoreCase("mobile")) {
                    ssoUser.setMobile(value);
                }

                if(key.equalsIgnoreCase("request")) {
                    ssoUser.setRequest(value);
                }

                if(key.equalsIgnoreCase("ref")) {
                    ssoUser.setRef(value);
                }
            }
        }
        System.out.println(ssoUser);
    }
}

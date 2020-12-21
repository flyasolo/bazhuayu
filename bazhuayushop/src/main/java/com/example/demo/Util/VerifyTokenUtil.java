package com.example.demo.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.apache.tomcat.util.codec.binary.Base64;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author ya
 * @desc jwt验证工具
 * @date 2020/11/23
 * @update sxb
 */
public class VerifyTokenUtil {
    private static String readCert(String certPath) throws Exception {
        CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
        FileInputStream fileInputStream = new FileInputStream(certPath);
        X509Certificate Cert = (X509Certificate) certificatefactory.generateCertificate(fileInputStream);
        PublicKey pk = Cert.getPublicKey();
        BASE64Encoder bse = new BASE64Encoder();
        return bse.encode(pk.getEncoded());
    }

    /**
     * @param publicKeyBase64
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static PublicKey getPublicKey(String publicKeyBase64)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        String pem = publicKeyBase64.replaceAll("/-*BEGIN PUBLIC KEY/-*", "").replaceAll("/-*END PUBLIC KEY/-*", "")
                .trim();
        java.security.Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(pem));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(pubKeySpec);
    }

    /**
     * @param token
     * @return
     * @throws Exception
     * @desc 验证合法性
     */
    public static String verifyToken(String token) throws Exception {
        String certPath = "./config/certificate.crt";
        String accessTokenKey = readCert(certPath);
        PublicKey publicKey = getPublicKey(accessTokenKey);
        Jwt<JwsHeader, Claims> parseClaimsJwt = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
        return parseClaimsJwt.getBody().get("data").toString();
    }
}

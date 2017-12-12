package org.obsidian.tcsp.util;

import com.alibaba.fastjson.JSON;
import org.obsidian.tcsp.util.security.Cryptor;
import org.obsidian.tcsp.dto.Token;

/**
 * @Author Rin
 * @Date 2017/11/24
 */
public class TokenUtil {
    public static String encryptToken(Token token){
        String tokenStr = JSON.toJSONString(token);
        return Cryptor.encrypt(tokenStr);
    }
    public static Token decryptToken(String encryptedToken){
        String decryptedToken = Cryptor.decrypt(encryptedToken);
        Token token = JSON.parseObject(decryptedToken,Token.class);
        return token;
    }
}

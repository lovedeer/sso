package com.wxqts.jwt;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wxqts.constant.SsoConstants;

/**
 * @author zhoulong E-mail:zhoulong@163.com
 * @date 2018年4月20日
 */
public class JwtUtil {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	/**
	 * token有效期,毫秒，默认300秒
	 */
	private static int DEFAULT_MAXAGE = 300000;

	/**
	 * 生成JWT类型的token
	 * 
	 * @param object
	 *            传入对像
	 * @return token
	 */
	public static <T> String createToken(T object) {
		Map<String, Object> headerMap = new HashMap<>(2);
		headerMap.put("alg", "HS256");
		headerMap.put("typ", "JWT");
		long expire = System.currentTimeMillis() + DEFAULT_MAXAGE;

		String token = null;
		try {
			token = JWT.create().withHeader(headerMap).withClaim(SsoConstants.PAYLOAD, JSON.toJSONString(object))
					.withClaim(SsoConstants.EXPIRE, new DateTime(expire).toDate())
					.sign(Algorithm.HMAC256(SsoConstants.SECRET));
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("create token failed :" + e.getMessage());
			}
		}
		return token;
	}

	/**
	 * 解密token
	 * 
	 * @param token
	 *            jwt类型的token
	 * @param classT
	 *            加密时的类型
	 * @param <T>
	 * @return 返回解密后的对象 - 如果token过期返回空对象
	 */
	public static <T> T unsign(String token, Class<T> classT) {
		JWTVerifier verifier = null;
		try {
			verifier = JWT.require(Algorithm.HMAC256(SsoConstants.SECRET)).build();
			DecodedJWT jwt = verifier.verify(token);
			Map<String, Claim> claims = jwt.getClaims();
			if (claims.containsKey(SsoConstants.PAYLOAD)) {
				String json = claims.get(SsoConstants.PAYLOAD).asString();
				// 把json转回对象，返回
				return JSON.parseObject(json, classT);
			}
		} catch (TokenExpiredException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("token expired");
			}
		} catch (SignatureVerificationException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("token signature is wrong");
			}
		} catch (UnsupportedEncodingException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Unsupported Encoding");
			}
		}
		return null;
	}

	/**
	 * 验证token
	 * 
	 * @param token
	 * @return token是否正确
	 */
	public boolean verify(String token) {
		JWTVerifier verifier = null;
		try {
			verifier = JWT.require(Algorithm.HMAC256(SsoConstants.SECRET)).build();
			DecodedJWT jwt = verifier.verify(token);
			Map<String, Claim> claims = jwt.getClaims();
			if (!claims.containsKey(SsoConstants.PAYLOAD)) {
				return false;
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("token verify failed");
			}
			return false;
		}
		return true;
	}
}

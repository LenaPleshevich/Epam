package com.epam.finaltask.logic.impl.util;

import java.math.BigInteger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import com.epam.finaltask.logic.exception.CommandException;

/**
 * This class is used to encode password
 */
public class MD5Util {
	private static final Logger logger = Logger.getRootLogger();
	
	private static final String TYPE = "MD5";
	
	public static String codingMD5(String str) throws CommandException{
		
		MessageDigest messageDigest = null;
		byte[] digest = new byte[0];
		try{
			messageDigest = MessageDigest.getInstance(TYPE);
			messageDigest.reset();
			messageDigest.update(str.getBytes());
			digest = messageDigest.digest();
		} catch (NoSuchAlgorithmException e){
			logger.error("NoSuchAlgorithmException is thrown when trying to encoding password", e);
			throw new CommandException("NoSuchAlgorithmException is thrown when trying to encoding password", e);
		}
		BigInteger bigInteger = new BigInteger(1, digest);
		String md5Hex = bigInteger.toString(16);
		while (md5Hex.length() < 32){
			md5Hex = "0" + md5Hex;
		}
		return md5Hex;
	}
}

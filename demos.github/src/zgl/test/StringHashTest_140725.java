package zgl.test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

public class StringHashTest_140725 {
	public static void main(String[] args) {
		String str = "14489285409331312891222114248171387079695043206702188487544383251317"
				+ "90865258520769342685854318544310101722310703387142962386888831239054577"
				+ "04236766458443148745519153106060105444006347763809544292455226645275426"
				+ "32866708228402344328462757799514424906836680298925402852136798714356838"
				+ "5147907312171682067486421099";
		System.out.println(str.hashCode());
		System.out.println(HashAlgorithms.java(str));
		System.out.println(HashAlgorithms.APHash(str));
		System.out.println(HashAlgorithms.bernstein(str));
		System.out.println(HashAlgorithms.BKDRHash(str));
		System.out.println(HashAlgorithms.DEKHash(str));
		System.out.println(HashAlgorithms.ELFHash(str));
		System.out.println(HashAlgorithms.FNVHash(str.getBytes()));
		System.out.println(HashAlgorithms.FNVHash1(str));
		System.out.println(HashAlgorithms.JSHash(str));
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bs = md.digest(str.getBytes());
			System.out.println(bs.length);
			StringBuffer hexValue = new StringBuffer();
			 
	        for (int i = 0; i < bs.length; i++) {
	            int val = ((int) bs[i]) & 0xff;
	            if (val < 16)
	                hexValue.append("0");
	            hexValue.append(Integer.toHexString(val));
	        }
			System.out.println(hexValue.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		DigestUtils du = new DigestUtils();
		System.out.println(DigestUtils.md5Hex(str));
		System.out.println(DigestUtils.sha512Hex(str));
		
		//System.out.println(BigInteger.probablePrime(1024, new Random()));
		
		
	}
}

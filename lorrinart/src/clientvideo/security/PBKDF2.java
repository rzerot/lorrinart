package clientvideo.security;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PBKDF2 {

	public static String generatePasswordHash(String password, byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		int iterations = 1000;
		char[] chars = password.toCharArray();
		salt = getSalt();
		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
		SecretKeyFactory skf = SecretKeyFactory
				.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = skf.generateSecret(spec).getEncoded();

		return iterations + ":" + toHex(salt) + ":" + toHex(hash);
	}

	private static String toHex(byte[] array) {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}

	}

	public static byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

	public static String saltToString(byte[] salt) {
		StringBuilder sb = new StringBuilder();
		for (byte s : salt) {
			sb.append(s);
		}
		return sb.toString();
	}

	public static boolean validatePassword(String originalPassword,
			String storedPassword) throws NoSuchAlgorithmException,
			InvalidKeySpecException {
		String[] parts = storedPassword.split(":");
		System.out.println("AAAAA " + parts[0].toString());
		System.out.println("");
		int iterations = Integer.parseInt(parts[0]);
		byte[] salt = fromHex(parts[1]);

		StringBuilder sb = new StringBuilder();
		for (byte b : salt) {
			sb.append(b);
		}
		System.out.println(sb.toString());

		byte[] hash = fromHex(parts[2]);
		PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt,
				iterations, hash.length * 8);
		SecretKeyFactory skf = SecretKeyFactory
				.getInstance("PBKDF2WithHmacSHA1");
		byte[] testHash = skf.generateSecret(spec).getEncoded();
		int diff = hash.length ^ testHash.length;

		for (int i = 0; i < hash.length && i < testHash.length; i++) {
			diff |= hash[i] ^ testHash[i];
		}
		return diff == 0;
	}

	private static byte[] fromHex(String hex) {
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2),
					16);
		}
		return bytes;
	}

}

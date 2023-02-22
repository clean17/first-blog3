package shop.mtcoding.blog2.Util;

import java.security.MessageDigest;

public class Sha256 {
	public static String encode(String password) {
		String SHA = "";
		String saltPassword = "best_" + password;
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(saltPassword.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();

		} catch (Exception e) {
			e.printStackTrace();
			SHA = null;
		}
		return SHA;
	}
}

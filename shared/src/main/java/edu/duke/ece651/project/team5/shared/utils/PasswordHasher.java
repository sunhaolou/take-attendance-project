
package edu.duke.ece651.project.team5.shared.utils;

import org.mindrot.jbcrypt.BCrypt;


public class PasswordHasher {
    public static String getHashedPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    // Method to check a password against a hash
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

}
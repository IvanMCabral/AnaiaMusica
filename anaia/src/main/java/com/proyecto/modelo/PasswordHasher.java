package com.proyecto.modelo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {
    public static String hashPassword(String password) {
        try {
            // Obtener instancia de MessageDigest con algoritmo "SHA-256"
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Convertir la contraseña a bytes y aplicar hashing
            byte[] hashedBytes = md.digest(password.getBytes());
            // Convertir los bytes hasheados a una cadena hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // En caso de que el algoritmo no sea soportado por la JVM
            throw new RuntimeException("Error al hashear contraseña", e);
        }
    }
}

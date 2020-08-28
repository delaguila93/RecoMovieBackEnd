/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.util;


public class Util {

    /**
     * Método para transformar argumentos en una cadena de caracteres en formato JSON
     *
     * @param argumentos String argumentos
     * @return String Cadena completa
     */
    public static String parsearArgumentosJSON(String... argumentos) {
        StringBuilder string = new StringBuilder();
        int count = 1;

        for (String argumento : argumentos) {
            if (count == 1 && count == argumento.length()) {
                string.append("{").append(argumento).append("}");
            } else if (count == 1) {
                string.append("{").append(argumento).append(", ");
            } else if (count == argumentos.length) {
                string.append(argumento).append(" }");
            } else {
                string.append(argumento).append(", ");
            }

            count++;
        }

        return string.toString();
    }
}

/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.excepciones;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    /**
     * Metodo para cambiar la fecha de String a Calendar para usar en las entidades
     *
     * @param fecha La fecha en String para cambiar
     * @return La fecha en vez de String en Calendar para su uso en las entidades
     */
    public static Calendar parsearStringFecha(String fecha)  {
        Calendar cal = Calendar.getInstance();

        String[] division = fecha.split("-");
        int year = Integer.parseInt(division[0]);
        int month = Integer.parseInt(division[1]);
        int day = Integer.parseInt(division[2]);

        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month);
        cal.set(Calendar.DAY_OF_MONTH,day);
        return cal;
    }

    /**
     * Metodo para cambiar la fecha dada de Calendar a String con el formato adecuado
     * @param fecha Fecha a cambiar de tipo Calendar a String
     * @return La fecha en String con el formato dado
     */
    public static String parsearCalendarFecha(Calendar fecha){
        String fechaString = fecha.get(Calendar.DAY_OF_MONTH) + "-" + (fecha.get(Calendar.MONTH)+1) + "-" + fecha.get(Calendar.YEAR);
        return fechaString;
    }
}

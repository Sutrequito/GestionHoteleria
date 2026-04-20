package legacy;

import java.util.ArrayList;

// VERSION ANTES DE REFACTORIZAR
// Este archivo contiene CODIGO LEGACY con multiples problemas:
//   - Nombres poco significativos (h1, x, fn1)
//   - Campos publicos sin encapsular
//   - Metodos extensos que mezclan responsabilidades
//   - Bloques de codigo duplicados
//   - Codigo muerto y referencias huerfanas
//   - Sin documentacion JavaDoc
//   - Mezcla logica de negocio con presentacion (System.out)
// Sirve como punto de partida para aplicar los patrones del Tema 4.

public class GestorHotel {

    public ArrayList h1 = new ArrayList();   // habitaciones
    public ArrayList r1 = new ArrayList();   // reservas
    public String n1;                        // nombre del hotel
    public int x;                            // contador no documentado
    public double tmp;                       // variable temporal global

    public GestorHotel(String n) {
        n1 = n;
    }

    // metodo gigante que hace de todo: alta + validacion + impresion + total
    public double fn1(String c, String tipo, int noches, double precioBase, double iva) {
        // calculo de precio segun tipo
        double total = 0;
        if (tipo.equals("STD")) {
            total = precioBase * noches;
        } else if (tipo.equals("SUP")) {
            total = precioBase * noches * 1.25;
        } else if (tipo.equals("SUI")) {
            total = precioBase * noches * 1.75;
        } else {
            total = precioBase * noches;
        }
        // aplica iva
        total = total + (total * iva / 100);
        // imprime ticket
        System.out.println("Cliente: " + c);
        System.out.println("Tipo: " + tipo);
        System.out.println("Noches: " + noches);
        System.out.println("Total: " + total);
        // guarda reserva como string concatenado
        r1.add(c + "|" + tipo + "|" + noches + "|" + total);
        x = x + 1;
        tmp = total;
        return total;
    }

    // duplicado parcial del metodo anterior con otro nombre
    public double fn2(String c, String tipo, int noches, double precioBase, double iva) {
        double total = 0;
        if (tipo.equals("STD")) total = precioBase * noches;
        if (tipo.equals("SUP")) total = precioBase * noches * 1.25;
        if (tipo.equals("SUI")) total = precioBase * noches * 1.75;
        total = total + (total * iva / 100);
        System.out.println("Cliente: " + c + " Tipo: " + tipo + " Noches: " + noches + " Total: " + total);
        r1.add(c + "|" + tipo + "|" + noches + "|" + total);
        x++;
        return total;
    }

    // codigo muerto: nunca se llama desde ningun lado
    public void metodoNoUsado() {
        System.out.println("este metodo no lo llama nadie");
    }

    // exposicion del estado interno
    public ArrayList getR1() {
        return r1;
    }
}

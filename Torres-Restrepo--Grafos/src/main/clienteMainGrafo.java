package main;

import logica.metodoGrafo;
import java.util.Scanner;

/**
 *
 * @author Andres Torres Ciceri y Edwin Orlando Restrepo M.
 */
public class clienteMainGrafo {

    public static void main(String[] args) throws Exception {
        String[][] adyacente;
        metodoGrafo grafo = new metodoGrafo();
        Scanner leer = new Scanner(System.in);
        boolean salir = true;
        int numero = 0;
        System.out.println("\n\n¿Cuantos vertices para el grafo desea crear?");
        numero = leer.nextInt();
        adyacente = new String[numero][numero];
        adyacente = grafo.crearGrafo(numero, adyacente);
        try {
            while (salir == true) {
                switch (menu()) {
                    case 1:
                        grafo.establecerArco(numero, adyacente);
                        break;
                    case 2:
                        grafo.buscarAdyacente(numero, adyacente);
                        break;
                    case 3:
                        grafo.eliminarArco(numero, adyacente);
                        break;
                    case 4:
                        if (numero > 0) {
                            adyacente = grafo.eliminarVertice(numero, adyacente);
                            numero--;
                        } else {
                            System.out.println("No se puede eliminar más vertices");
                        }
                        break;
                    case 5:
                        grafo.imprimirAdyacencias(adyacente);
                        espera();
                        break;
                    case 6:
                        grafo.recorridoAnchura(numero, adyacente);
                        espera();
                        break;
                    case 7:
                        grafo.recorridoProfundidad(numero, adyacente);
                        espera();
                        break;
                    case 8:
                        System.out.println(grafo.algoritmoFloyd(adyacente));
                        espera();
                        break;
                    case 9:
                        salir = false;
                }
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    public static int menu() {
        Scanner leer = new Scanner(System.in);
        int valor;
        System.out.println("\n\n\nMenu:\n"
                + "1. Establecer un arco entre vertices \n"
                + "2. Saber si un vertice es adyacente a otro\n"
                + "3. Eliminar arco\n"
                + "4. Eliminar vertice\n"
                + "5. Imprimir\n"
                + "6. Recorrido en anchura\n"
                + "7. Recorrido en profundidad\n"
                + "8. Caminos minimos por Floyd\n"
                + "9. Salir\n"
                + "Opción:\n");
        valor = leer.nextInt();
        return valor;
    }

    public static void espera() {
        Scanner leer = new Scanner(System.in);
        leer.nextLine();
        System.out.print("\nPress ENTER KEY to continue...");
        leer.nextLine();
    }

}

package logica;

import java.util.Scanner;

/**
 *
 * @author Andres Torres Ciceri y Edwin Orlando Restrepo M.
 */
public class metodoGrafo {

    private String valor, coste;
    Scanner leer = new Scanner(System.in);

    public String[][] crearGrafo(int num, String[][] adyacente) {
        for (int i = 0; i < num; i++) {
            System.out.println("\nInserte el valor del vertice #" + (i + 1));
            leer = new Scanner(System.in);
            valor = leer.nextLine();
            adyacente[i][0] = valor;
            for (int j = 1; j < num; j++) {
                adyacente[i][j] = " ";
            }
        }
        return adyacente;
    }

    public void establecerArco(int numero, String[][] adyacente) {
        boolean existeNodo = false;
        boolean existeArista = false;
        boolean repetir = false;
        System.out.println("\n\nInserte el valor del vertice inicial al cual desea establecer un arco:");
        leer = new Scanner(System.in);
        valor = leer.nextLine();
        for (int i = 0; i < numero; i++) {
            if (valor.equalsIgnoreCase(adyacente[i][0]) && existeNodo == false) {
                existeNodo = true;
                System.out.println("\n\nInserte el valor del vertice destino al cual desea establecer un arco con " + valor);
                valor = leer.nextLine();
                System.out.println("\n\nIngrese el coste de la arista:");
                coste = leer.nextLine();
                for (int j = 0; j < numero; j++) {
                    if (valor.equalsIgnoreCase(adyacente[j][0])) {
                        existeArista = true;
                        for (int k = 1; k < numero; k++) {
                            if (adyacente[i][k].equals(" ") && repetir == false) {
                                adyacente[i][k] = valor + "," + coste;
                                repetir = true;
                                System.out.println("\nArco establecido");
                            }
                        }
                    }
                }
            }
        }
        if (existeNodo != true) {
            System.out.println("\nNo existe ese vertice");
        } else if (existeArista != true) {
            System.out.println("\nNo existe ese vertice y no se puede establecer un arco");
        } else if (repetir != true) {
            System.out.println("\nNo se puede poner otro vertice adyacente");
        }
    }

    public void buscarAdyacente(int numero, String[][] adyacente) {
        boolean encontrado = false;
        System.out.println("\n\nInserte el valor del vertice para buscar su adyacencia:");
        leer = new Scanner(System.in);
        valor = leer.nextLine();
        for (int i = 0; i < numero; i++) {
            for (int j = 1; j < numero; j++) {
                if (adyacente[i][j].equals(valor)) {
                    System.out.println("\nEl vertice " + valor + " es adyacente al vertice " + adyacente[i][0]);
                    encontrado = true;
                    break;
                }
            }
        }
        if (encontrado == false) {
            System.out.println("\nEl vertice " + valor + " no tiene ninguna adyacencia existente o no existe.");
        }
    }

    public void imprimirAdyacencias(String[][] adyacente) {
        System.out.print("\n\nVertices       Adyacencias, Coste\n");
        for (int x = 0; x < adyacente.length; x++) {
            System.out.print("[");
            for (int y = 0; y < adyacente[x].length; y++) {
                System.out.print(adyacente[x][y]);
                if (y == 0) {
                    System.out.print("     =>");
                }
                if (y != adyacente[x].length - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println("]");
        }
    }

    public void eliminarArco(int numero, String[][] adyacente) {
        boolean existeNodo = false;
        boolean existeArista = false;
        boolean repetir = false;
        String[] separar;
        System.out.println("\n\nInserte el valor del vertice inicial al cual desea eliminar un arco:");
        leer = new Scanner(System.in);
        valor = leer.nextLine();
        for (int i = 0; i < numero; i++) {
            if (valor.equalsIgnoreCase(adyacente[i][0]) && existeNodo == false) {
                existeNodo = true;
                System.out.println("\n\nInserte el valor del vertice destino al cual desea eliminar un arco con " + valor);
                valor = leer.nextLine();
                for (int j = 0; j < numero; j++) {
                    separar = adyacente[i][j].split(",");
                    if (valor.equalsIgnoreCase(separar[0])) {
                        existeArista = true;
                        for (int k = 1; k < numero; k++) {
                            if (separar[0].equals(valor) && repetir == false) {
                                adyacente[i][k] = " ";
                                repetir = true;
                                System.out.println("\nArco eliminado");
                            }
                        }
                    }
                }
            }
        }
        if (existeNodo != true) {
            System.out.println("\nNo existe ese vertice");
        } else if (existeArista != true) {
            System.out.println("\nNo existe ese vertice y no se puede eliminar un arco");
        } else if (repetir != true) {
            System.out.println("\nNo se puede eliminar otro vertice adyacente");
        }
    }

    public String[][] eliminarVertice(int num, String[][] adyacente) {
        int numero = num, j = 0;
        String[][] nuevo = {};
        System.out.println("\nInserte el valor del vertice a eliminar:");
        leer = new Scanner(System.in);
        valor = leer.nextLine();
        for (int i = 0; i < num; i++) {
            if (adyacente[i][0].equals(valor)) {
                adyacente[i] = null;
            }
        }
        numero -= 1;
        nuevo = new String[numero][numero];
        for (int i = 0; i < num; i++) {
            if (adyacente[i] != null) {
                if (j == i) {
                    nuevo[j] = adyacente[i];
                    j++;
                } else {
                    while (j < i) {
                        nuevo[j] = adyacente[i];
                        j++;
                    }
                }
            }
        }
        return nuevo;
    }

    public void recorridoAnchura(int numero, String[][] adyacente) {
        String[] separar;
        String anterior = " ";
        System.out.println("\n\nVertice inicial: " + adyacente[0][0] + "\n");
        System.out.print(adyacente[0][0] + " ");
        for (int i = 0; i < numero; i++) {
            for (int j = 1; j < numero; j++) {
                separar = adyacente[i][j].split(",");
                if (!separar[0].equals(" ") && !anterior.equals(separar[0])) {
                    System.out.print(separar[0] + " ");
                    anterior = separar[0];
                }
            }
        }
    }
    
    public void recorridoProfundidad(int numero, String[][] adyacente){
        String[] separar;
        String anterior = " ";
        System.out.println("\n\nRecorrido en profundidad:");
        for (int i = 0; i < numero; i++) {
            for (int j = 0; j < numero; j++) {
                separar = adyacente[j][i].split(",");
                if (!separar[0].equals(" ") && !anterior.equals(separar[0])) {
                    System.out.print(separar[0] + " ");
                    anterior = separar[0];
                }
            }
        }
    }

    //Método para determinar todos los caminos Floyd
    public String algoritmoFloyd(String[][] adyacente) {
        int vertices = adyacente.length;

        String matrizAdyacencia[][] = matriz(adyacente);             //Pesos
        String caminos[][] = new String[vertices][vertices];
        String caminosAuxiliares[][] = new String[vertices][vertices];
        String caminoRecorrido = "", cadena = "", caminitos = "";
        int i, j, k;
        int temporal1, temporal2, temporal3, temporal4, minimo;
        if (esAdyacente(adyacente) == true) {
            //Se inicializa las matrices de caminos y caminosAuxiliares
            for (i = 0; i < vertices; i++) {
                for (j = 0; j < vertices; j++) {
                    caminos[i][j] = " ";
                    caminosAuxiliares[i][j] = " ";
                }
            }
            for (k = 0; k < vertices; k++) {
                for (i = 0; i < vertices; i++) {
                    for (j = 0; j < vertices; j++) {
                        if (matrizAdyacencia[i][j].equals(" ")) {
                            if (matrizAdyacencia[k][j].equals(" ")) {
                                matrizAdyacencia[k][j] = "0";
                            }
                            matrizAdyacencia[i][j] = "0";
                        }
                        temporal1 = Integer.parseInt(matrizAdyacencia[i][j]);
                        temporal2 = Integer.parseInt(matrizAdyacencia[i][k]);
                        temporal3 = Integer.parseInt(matrizAdyacencia[k][j]);
                        temporal4 = temporal2 + temporal3;
                        //Encontrando el camino mínimo
                        minimo = Math.min(temporal1, temporal4);
                        if (temporal1 != temporal4) {
                            if (minimo == temporal4) {
                                caminoRecorrido = " ";
                                caminosAuxiliares[i][j] = k + " ";
                                caminos[i][j] = caminosRecursivo(i, k, caminosAuxiliares, caminoRecorrido) + (k + 1);
                            }
                        }
                        matrizAdyacencia[i][j] = String.valueOf(minimo);
                    }
                }
            }
            //Agregando el camino a cadena
            for (i = 0; i < vertices; i++) {
                for (j = 0; j < vertices; j++) {
                    cadena += "[" + matrizAdyacencia[i][j] + "]";
                }
                cadena += "\n";
            }
            ////////////////////////////////////////
            for (i = 0; i < vertices; i++) {
                for (j = 0; j < vertices; j++) {
                    if (Float.parseFloat(matrizAdyacencia[i][j]) != 100000000) {
                        if (i != j) {
                            if (caminos[i][j].equals(" ")) {
                                caminitos += "De (" + (i + 1) + " ---> " + (j + 1) + ") Irse por... (" + (i + 1) + ", " + (j + 1) + ")\n";
                            } else {
                                caminitos += "De (" + (i + 1) + " ---> " + (j + 1) + ") Irse por... (" + (i + 1) + ", " + caminos[i][j] + ", " + (j + 1) + ")\n";
                            }
                        }
                    }
                }
            }
            return "\nLa matriz de caminos más cortos entre los diferentes vértices es: \n" + cadena + "\nLos diferentes caminos más cortos entre vértices son:\n" + caminitos;
        } else {
            return "\nLa matriz no tiene ninguna adyacencia creada";
        }
    }

    public String caminosRecursivo(int i, int k, String[][] caminosAux, String caminoRecorrido) {
        if (caminosAux[i][k].equals(" ")) {
            return " ";
        } else {
            //Recursividad al millón
            caminoRecorrido += caminosRecursivo(i, Integer.parseInt(caminosAux[i][k]), caminosAux, caminoRecorrido) + (Integer.parseInt(caminosAux[i][k]) + 1) + ", ";
            return caminoRecorrido;
        }
    }

    public String[][] matriz(String[][] adyacente) {
        int tamanio = adyacente.length;
        String[][] nuevaMatriz = new String[tamanio][tamanio];
        String[] separar = {};
        for (int i = 0; i < tamanio; i++) {
            nuevaMatriz[i][0] = adyacente[i][0];
            for (int j = 1; j < tamanio; j++) {
                separar = adyacente[i][j].split(",");
                nuevaMatriz[i][j] = separar[0];
            }
        }
        return nuevaMatriz;
    }

    public boolean esAdyacente(String[][] adyacente) {
        int tamanio = adyacente.length;
        boolean tieneAdyacencias = false;
        for (int i = 0; i < tamanio; i++) {
            for (int j = 1; j < tamanio; j++) {
                if (!adyacente[i][j].equals(" ")) {
                    tieneAdyacencias = true;
                }
            }
        }
        return tieneAdyacencias;
    }
}

public class EscalerizaMatriz {
    private static double[][] matriz = {{1.5,3.0,4.0},
                                        {1.0,2.0,3.0},
                                        {6.0,7.0,8.0}};
    private static int cambios; 
    
    public static void main(String[] args) {
        System.out.println("Matriz original");
        imprimeMatriz();
        escarelizar();
        System.out.println("Matriz escarelizada");
        imprimeMatriz();
        System.out.println("Determinante: "+Math.pow(-1, cambios)*productoDeTraza());
    }
    
    private static void escarelizar(){
        //matriz.length cantidad de filas
        //matriz[0].length cantidad de columnas
        //i fila j columna
        double factor;
        int tmp;
        cambios=0;
        for (int j = 0; j < matriz.length; j++) {   
            for (int i = j+1; i < matriz[0].length; i++) {
                if (matriz[j][j]==0) {
                    tmp=buscarFilaNoNula(i,j);
                    if (tmp==matriz.length) {
                        //No encontro fila sin 0
                    }else{
                        intercambiarFilas(tmp,j);
                        cambios++;
                    }
                }
                factor=matriz[i][j]/matriz[j][j];
                if (factor!=0) {
                    restaFila(i,j,factor);
                }
                System.out.println("----------");
                imprimeMatriz();
            }
        }
    }
    
    private static void intercambiarFilas(int fila1, int fila2){
        //a=a+b
        //b=a-b <<- b=a+b-b=a  
        //a=a-b <<- a=a+b-a=b //parece no funcionar con numeros pequeños
        double tmp;
        for (int i = 0; i < matriz.length; i++) {
            //a=matriz[fila1][i]
            //b=matriz[fila2][i]
            //System.out.println("Origianl "+matriz[fila1][i]+" y "+matriz[fila2][i]);
            tmp=matriz[fila2][i];
            matriz[fila2][i]=matriz[fila1][i];
            matriz[fila1][i]=tmp;
            //matriz[fila1][i]=matriz[fila1][i]+matriz[fila2][i];
            //matriz[fila2][i]=matriz[fila1][i]-matriz[fila2][i];
            //matriz[fila1][i]=matriz[fila2][i]-matriz[fila2][i];
            //System.out.println("Intercambiado "+matriz[fila1][i]+" y "+matriz[fila2][i]);
        }
    }
    
    //a partir de la celda del parametro, busca celdas debajo sin 0
    private static int buscarFilaNoNula(int fila,int columna){
        int i=fila;
        while(i < matriz.length && matriz[i][columna]==0){
            i++;
        }
        return i;
    }
    
    private static void restaFila(int fila1, int fila2, double factor){
        for (int col = 0; col < matriz[0].length; col++) {
            matriz[fila1][col]=matriz[fila1][col]-factor*matriz[fila2][col];
        }
    }
    
    private static void imprimeMatriz(){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j]);
                if (j!=matriz[0].length-1) {
                    System.out.print(",");
                }else{
                    System.out.println("");
                }
            }
        }
    }
    
    private static double productoDeTraza(){
        double result=1;
        for (int i = 0; i < matriz.length; i++) {
            result*=matriz[i][i];
        }
        return result;
    }
}

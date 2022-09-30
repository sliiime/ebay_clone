package com.skaypal.ebay_clone.utils.matrix_factorization;

import java.util.Random;

import static java.lang.Math.pow;

public class MatrixFactorization {


    private static final int k = 10;

    private static final double threshold = 0.001D;



    public static double[][] run(double[][] matrix,int steps,double alpha,double beta){

        int rows = matrix.length;
        int cols = matrix[0].length;

        double[][] nXk = createRandomDouble2dArray(rows,k,1,2);
        double[][] kXm = createRandomDouble2dArray(k,cols,1,2); // generated as transposed

        double[][] improvedMatrix = null;

        for (int step = 0 ; step < steps; step++){
            for (int i = 0 ; i < rows ; i++){
                for (int j = 0 ; j < cols ; j++){
                    if ( isDefined(matrix[i][j]) ) {
                        double approxij = multiplyRowWithCol(nXk[i], kXm,j);
                        double cellError = calculateError(matrix[i][j],approxij);

                        graduallyDescent(i,j,nXk,kXm,cellError,alpha,beta);
                    }
                }
            }

            improvedMatrix = multiplyMatrices(nXk,kXm);

            double matrixError = compareMatrices(matrix,improvedMatrix,nXk,kXm,beta);

            if (matrixError < threshold) break;

        }

        return improvedMatrix;


    }

    private static double compareMatrices(double[][] matrix, double[][] improvedMatrix,double[][] nXk,double[][] kXm,double beta) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        double error = 0;

        for (int i = 0 ; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if ( isDefined(matrix[i][j] )){
                    error += pow(matrix[i][j] - improvedMatrix[i][j],2);
                    for (int c = 0 ; c < k ; c++){
                        error += (beta/2)*(pow(nXk[i][c],2) + pow(kXm[c][j],2) );
                    }
                }

            }
        }
        return error;
    }

    private static void graduallyDescent(int row, int col,double[][] nXk,double[][] kXm, double error,double alpha,double beta) {

        for (int i = 0 ; i < k; i++ ){
            nXk[row][i] += alpha * (2*error*kXm[i][col] - beta*nXk[row][i]);
            kXm[i][col] += alpha * (2*error*nXk[row][i] - beta*kXm[i][col]);
        }
    }


    private static boolean isDefined(double matrix) {
        return matrix != 0 ;
    }


    private static double[][] createRandomDouble2dArray(int rows,int columns,double min,double max){
            double[][] array = new double[rows][columns];

            Random generator = new Random();

            for (int i = 0 ; i < rows ; i ++){
                for (int j = 0; j < columns ; j++){
                    array[i][j] = generator.nextDouble() * (max + min) - min;
                }
            }

            return array;
    }

    private static double[][] multiplyMatrices(double[][] a, double[][] b ){

        if (a[0].length != b.length ) throw new RuntimeException("Matrices are not multipliable");

        double[][] productMatrix = new double[a.length][b[0].length];


        for (int i = 0 ; i < a.length ; i++)
            for (int j = 0 ; j < b[0].length ; j++)
                    productMatrix[i][j] += multiplyRowWithCol(a[i],b,j);

        return productMatrix;
    }

    private static double multiplyRowWithCol(double[] row, double[][] col, int colNumber){
        if (row.length != col.length) throw new RuntimeException("The row and column to be multiplied need to be of the same size");

        double sum = 0;

        for (int i = 0; i < row.length; i++) sum += row[i] * col[i][colNumber];

        return sum;
    }

    private static double calculateError(double val1,double val2){
        return val1 - val2;
    }

}

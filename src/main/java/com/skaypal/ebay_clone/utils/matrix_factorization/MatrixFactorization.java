package com.skaypal.ebay_clone.utils.matrix_factorization;

import java.util.Random;

public class MatrixFactorization {

    public static double[][] createRandomDouble2dArray(int rows,int columns,double min,double max){
            double[][] array = new double[rows][columns];

            Random generator = new Random();

            for (int i = 0 ; i < rows ; i ++){
                for (int j = 0; j < columns ; j++){
                    array[i][j] = generator.nextDouble() * (max + min) - min;
                }
            }

            return array;
    }
}

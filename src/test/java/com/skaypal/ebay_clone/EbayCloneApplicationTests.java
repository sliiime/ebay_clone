package com.skaypal.ebay_clone;

import com.skaypal.ebay_clone.utils.matrix_factorization.MatrixFactorization;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class EbayCloneApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testMatrixFactorization(){
        double[][] matrix = {
                {0D,2D,1D,0D,0D},
                {2D,2D,1D,2D,2D},
                {0D,0D,1D,0D,0D},
                {1D,1D,1D,1D,1D},
                {1D,2D,1D,2D,2D},
                {2D,2D,1D,0D,0D},
        };

        double[][] improvedMatrix = MatrixFactorization.run(matrix,50000,0.0002,0.02);

        for (int i = 0 ; i < improvedMatrix.length; i++) {
            System.out.println(Arrays.toString(improvedMatrix[i]));
        }


    }

}

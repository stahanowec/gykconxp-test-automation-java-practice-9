package com.epam.test.automation.java.practice9;

import org.junit.Test;

import static org.testng.Assert.assertThrows;

public class MatrixTest {
    @Test
    public void dummy() {
        // given
        var row = -1;
        var column = 10;

        assertThrows(UnsupportedOperationException.class, () -> new Matrix(row, column));
    }

}
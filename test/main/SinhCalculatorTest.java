package main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for methods in SinhCalculatorGuiV1 class.
 */
class SinhCalculatorTest {

  @Test
  void testPower() {
    assertEquals(1.0, SinhCalculatorGuiV1.power(5, 0), 1e-6);
    assertEquals(8.0, SinhCalculatorGuiV1.power(2, 3), 1e-6);
    assertEquals(1.0, SinhCalculatorGuiV1.power(1, 100), 1e-6);
    assertEquals(0.25, SinhCalculatorGuiV1.power(0.5, 2), 1e-6);
  }

  @Test
  void testFactorial() {
    assertEquals(1.0, SinhCalculatorGuiV1.factorial(0), 1e-6);
    assertEquals(1.0, SinhCalculatorGuiV1.factorial(1), 1e-6);
    assertEquals(2.0, SinhCalculatorGuiV1.factorial(2), 1e-6);
    assertEquals(120.0, SinhCalculatorGuiV1.factorial(5), 1e-6);
  }

  @Test
  void testComputeSinh() {
    assertEquals(0.0, SinhCalculatorGuiV1.computeSinh(0), 1e-6);
    assertEquals(1.175201, SinhCalculatorGuiV1.computeSinh(1), 1e-6);
    assertEquals(-1.175201, SinhCalculatorGuiV1.computeSinh(-1), 1e-6);
  }
}

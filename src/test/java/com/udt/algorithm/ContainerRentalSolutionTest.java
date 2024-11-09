package com.udt.algorithm;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContainerRentalSolutionTest {

  @Test
  public void testCase1() {
    int neededContainer = 3;
    List<ContainerListing> listings =
        List.of(
            new ContainerListing("Container renter A", 1, 1),
            new ContainerListing("Container renter B", 2, 1),
            new ContainerListing("Container renter C", 3, 3));
    String expectedOutput =
        "[Contract with] Container renter B 2 container, price: 1\n"
            + "[Contract with] Container renter A 1 container, price: 1\n"
            + "[Summary] total cost 2";
    assertEquals(
        expectedOutput, ContainerRentalSolution.findMinimumCost(neededContainer, listings));
  }

  @Test
  public void testCase2() {
    int neededContainer = 10;
    List<ContainerListing> listings =
        List.of(
            new ContainerListing("Container renter A", 5, 5),
            new ContainerListing("Container renter B", 2, 10),
            new ContainerListing("Container renter C", 2, 3));
    String expectedOutput = "not enough container";
    assertEquals(
        expectedOutput, ContainerRentalSolution.findMinimumCost(neededContainer, listings));
  }

  @Test
  public void testCase3() {
    int neededContainer = 10;
    List<ContainerListing> listings =
        List.of(
            new ContainerListing("Container renter A", 5, 5),
            new ContainerListing("Container renter B", 2, 10),
            new ContainerListing("Container renter C", 10, 3));
    String expectedOutput =
        "[Contract with] Container renter C 10 container, price: 3\n[Summary] total cost 3";
    assertEquals(
        expectedOutput, ContainerRentalSolution.findMinimumCost(neededContainer, listings));
  }
}

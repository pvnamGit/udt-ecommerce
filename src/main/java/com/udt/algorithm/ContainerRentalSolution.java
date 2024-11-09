package com.udt.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContainerRentalSolution {
    public static String findMinimumCost(int neededContainer, List<ContainerListing> listings) {
        int[] dp = new int[neededContainer + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        ContainerListing[] selectedProvider = new ContainerListing[neededContainer + 1];

        for (ContainerListing listing : listings) {

            for (int i = neededContainer; i >= listing.container; i--) {
                if (dp[i - listing.container] != Integer.MAX_VALUE && dp[i - listing.container] + listing.totalCost < dp[i]) {
                    dp[i] = dp[i - listing.container] + listing.totalCost;
                    selectedProvider[i] = listing;
                }
            }
        }

        if (dp[neededContainer] == Integer.MAX_VALUE) {
            return "not enough container";
        } else {
            List<ContainerListing> result = new ArrayList<>();
            int remainingContainer = neededContainer;
            while (remainingContainer > 0) {
                result.add(selectedProvider[remainingContainer]);
                remainingContainer -= selectedProvider[remainingContainer].container;
            }
            return printResult(result, dp, neededContainer);
        }
    }

    private static String printResult(List<ContainerListing> result, int[] dp, int neededContainer) {
        StringBuilder output = new StringBuilder();
        for (ContainerListing listing : result) {
            output.append("[Contract with] ")
                    .append(listing.name)
                    .append(" ")
                    .append(listing.container)
                    .append(" container, price: ")
                    .append(listing.totalCost)
                    .append("\n");
        }
        output.append("[Summary] total cost ").append(dp[neededContainer]);
        return output.toString();
    }

    public static void main(String[] args) {
        List<ContainerListing> listings = List.of(
                new ContainerListing("Container renter A", 2, 1),
                new ContainerListing("Container renter B", 1, 1),
                new ContainerListing("Container renter C", 3, 4),
                new ContainerListing("Container renter C", 3, 4)

        );

        int neededContainer = 6;

        String expectedOutput = "[Contract with] Container renter C 3 container, price: 4\n" +
                "[Contract with] Container renter B 1 container, price: 1\n" +
                "[Contract with] Container renter A 2 container, price: 1\n" +
                "[Summary] total cost 6";

        String actualOutput = findMinimumCost(neededContainer, listings);

        if (actualOutput.equals(expectedOutput)) System.out.println("TRUE");
        else System.out.println("FALSE");
    }
}

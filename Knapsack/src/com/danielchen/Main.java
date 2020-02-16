package com.danielchen;

class Entry {
    int val;
    int prev;
    int weight;
}

public class Main {
    public static void main(String[] args) {
        int totalWeight = 503;
        int[] weights = {1, 4, 6, 7, 8, 9};
        int[] values = {10, 41, 20, 50, 60, 40};
        Entry[] dp = new Entry[totalWeight + 1];
        for(int i = 0; i < totalWeight + 1; ++i) {
            dp[i] = new Entry();
            dp[i].val = 0;
            dp[i].weight = 0;
            dp[i].prev = -1;
        }
        for(int i = 0; i < dp.length; i++) {
            for (int j = 0; j < weights.length; j++) {
                if(i - weights[j] < 0) continue;
                int currVal = dp[i - weights[j]].val + values[j];
                if(currVal > dp[i].val) {
                    dp[i].val = currVal;
                    dp[i].weight = weights[j];
                    dp[i].prev = i - weights[j];
                }
            }
        }
        System.out.println(dp[totalWeight].val);
        Entry entry = dp[totalWeight];
        while(entry.prev != -1) {
            System.out.println("The value and weight is " + Integer.toString(entry.val - dp[entry.prev].val) + " and " + Integer.toString(entry.weight));
            entry = dp[entry.prev];
        }
    }
}

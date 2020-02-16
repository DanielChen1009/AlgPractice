package com.danielchen;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Stop implements Comparable<Stop> {
    String name;
    int totalPrice = Integer.MAX_VALUE;
    Stop previous;
    Map<Stop, Integer> stops;
    public Stop(String name) {
        this.name = name;
        stops = new HashMap<>();
    }

    public void addStop(Stop dest, int price) {
        stops.put(dest, price);
    }


    @Override
    public int compareTo(Stop o) {
        return totalPrice - o.totalPrice;
    }
}

public class Main {

    public static void main(String[] args) {
        int numStops = 5;
        Stop sydney = new Stop("sydney");
        Stop orlando = new Stop("orlando");
        Stop portland = new Stop("portland");
        Stop beijing = new Stop("beijing");
        Stop moscow = new Stop("moscow");
        Stop SF = new Stop("SF");
        Stop start = sydney;
        Stop end = orlando;
        sydney.addStop(moscow, 15);
        moscow.addStop(beijing, 20);
        beijing.addStop(portland, 1);
        portland.addStop(orlando, 15);
        sydney.addStop(beijing, 40);
        beijing.addStop(moscow, 10);
        moscow.addStop(SF, 50);
        SF.addStop(orlando, 12);
        beijing.addStop(SF, 35);
        PriorityQueue<Stop> distances = new PriorityQueue<>(5);
        start.totalPrice = 0;
        distances.add(sydney);
        distances.add(orlando);
        distances.add(portland);
        distances.add(beijing);
        distances.add(moscow);
        distances.add(SF);
        Stop first = distances.poll();
        while(!first.name.equals(end.name)) {
            for (Stop stop : first.stops.keySet()) {
                if(first.previous != null && first.previous.name.equals(stop.name)) continue;
                int totalPrice = first.totalPrice + first.stops.get(stop);
                if(totalPrice < stop.totalPrice) {
                    stop.totalPrice = totalPrice;
                    stop.previous = first;
                    distances.remove(stop);
                    distances.add(stop);
                }
            }
            first = distances.poll();
        }
        System.out.println(end.totalPrice);
        Stop previous = end.previous;
        while(previous != null) {
            System.out.println(previous.name);
            previous = previous.previous;
        }
    }
}

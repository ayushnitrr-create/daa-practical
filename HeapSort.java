import java.io.*;
import java.util.*;

class Order {
    String name;
    int amount;

    public Order(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }
}

public class HeapSort {
    // Build max heap
    public static void buildMaxHeap(Order[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }
    public static void heapify(Order[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left].amount > arr[largest].amount)
            largest = left;
        if (right < n && arr[right].amount > arr[largest].amount)
            largest = right;

        if (largest != i) {
            Order temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }

    // HeapSort
    public static void heapSort(Order[] arr) {
        int n = arr.length;
        buildMaxHeap(arr, n);
        for (int i = n - 1; i > 0; i--) {
            Order temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(br.readLine().trim());
        Order[] orders = new Order[n];
        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().split(" ");
            orders[i] = new Order(parts[0], Integer.parseInt(parts[1]));
        }
        br.close();

        heapSort(orders);

        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        bw.write("Sorted Orders by Amount (Descending):\n");
        for (Order o : orders) {
            bw.write(o.name + " " + o.amount + "\n");
        }
        bw.close();

        //real life situation for customers oeders priority by amount they ordered 
        PriorityQueue<Order> pq = new PriorityQueue<>((a, b) -> b.amount - a.amount);
        for (Order o : orders) pq.add(o);
        System.out.println("Processing tasks by priority (amount):");
        while (!pq.isEmpty()) {
            Order o = pq.poll();
            System.out.println(o.name + " " + o.amount);
        }
    }
}

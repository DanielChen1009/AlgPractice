import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//class Node {
//    int[] nums;
//    List<int[]> children = new ArrayList<>();
//
//    public Node(int[] nums) {
//        this.nums = nums;
//    }
//
//    public int[] constructChildren() {
//
//    }
//
//}
class Num {
    double num;
    String expr;

    public Num(double num, String expr) {
        this.num = num;
        this.expr = expr;
    }

    @Override
    public String toString() {
        return num + " " + expr;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("24.in")));
        PrintWriter pw = new PrintWriter(new FileWriter(new File("24.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Num> nums = new ArrayList<>();
        while (st.hasMoreTokens()) {
            double num = Double.parseDouble(st.nextToken());
            String expr = isInteger(num) ? Integer.toString((int)num) : Double.toString(num);
            nums.add(new Num(num, expr));
        }
        int maxDepth = 10;
        pw.print(dfs(nums, 24, maxDepth));
        br.close();
        pw.close();
    }

    public static boolean isInteger(double n) {
        return Math.abs(n - Math.round(n)) < Double.MIN_VALUE;
    }

    public static int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; ++i) {
            result *= i;
        }
        return result;
    }

    public static String dfs(List<Num> nums, int K, int maxDepth) {
        if (nums.size() == 1 && Math.abs(nums.get(0).num - K) < Double.MIN_VALUE) {
            return nums.get(0).expr;
        }
        if (maxDepth <= 0) {
            return null;
        }
        Num first;
        Num second;
        String result = null;
        for (int i = 0; i < nums.size(); ++i) {
            first = nums.get(i);
            // handles binary operators
            for (int j = i + 1; j < nums.size(); ++j) {
                second = nums.get(j);
                nums.remove(j);
                nums.remove(i);

                for (int k = 0; k < 9; ++k) {
                    double newNum = 0;
                    String expr = "";
                    if (k == 0) {
                        newNum = first.num * second.num;
                        expr = "(" + first.expr + " * " + second.expr + ")";
                    } else if (k == 1 && second.num != 0) {
                        newNum = first.num / second.num;
                        expr = "(" + first.expr + " / " + second.expr + ")";
                    } else if (k == 2) {
                        newNum = first.num + second.num;
                        expr = "(" + first.expr + " + " + second.expr + ")";
                    } else if (k == 3) {
                        newNum = first.num - second.num;
                        expr = "(" + first.expr + " - " + second.expr + ")";
                    } else if (k == 4) {
                        newNum = second.num - first.num;
                        expr = "(" + second.expr + " - " + first.expr + ")";
                    } else if (k == 5 && first.num != 0) {
                        newNum = second.num / first.num;
                        expr = "(" + second.expr + " / " + first.expr + ")";
                    } else if (k == 6 && first.num < 100) {
                        newNum = Math.pow(second.num, first.num);
                        expr = "(" + second.expr + " ^ " + first.expr + ")";
                    } else if (k == 7 && second.num < 100) {
                        newNum = Math.pow(first.num, second.num);
                        expr = "(" + first.expr + " ^ " + second.expr + ")";
                    } else continue;

                    if (Double.isNaN(newNum) || Double.isInfinite(newNum)) continue;
                    nums.add(new Num(newNum, expr));
                    result = dfs(nums, K, maxDepth - 1);
                    nums.remove(nums.size() - 1);
                    if (result != null) {
                        break;
                    }
                }
                nums.add(i, first);
                nums.add(j, second);
                if (result != null) return result;
            }
            if ((first.num > 2 || first.num == 0) && first.num < 10 && isInteger(first.num)) {
                nums.remove(i);
                double newNum = factorial((int) first.num);
                String expr = first.expr + "!";
                nums.add(new Num(newNum, expr));
                result = dfs(nums, K, maxDepth - 1);
                nums.remove(nums.size() - 1);
                nums.add(i, first);
                if (result != null) return result;
            }
        }
        return null;
    }
}

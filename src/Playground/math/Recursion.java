package Playground.math;

public class Recursion {

    public static void main(String[] args) {
        System.out.println(fibonacci1(10, 0));
        System.out.println(fibonacci2(10));
    }

    public static int fibonacci1(int n, int sum) {
        if(n == 1 || n == 2)
            return 1;

        return sum + fibonacci1(n-1, sum) + fibonacci1(n-2, sum);
    }

    public static int fibonacci2(int n) {
        if(n == 0)
            return 0;
        if(n == 1)
            return 1;

        return fibonacci2(n - 1) + fibonacci2(n - 2);
    }

}

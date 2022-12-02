package Tuan2;

import java.util.Scanner;

public class Doitien {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int max=8;
        int []c = {200,100,50,20,10,5,2,1};
        int []a = new int[max];
        System.out.println("Nhap N = ");
        int n = sc.nextInt();
        int i =0;
        while (n!=0){
            a[i] = n/c[i];
            if(a[i]!=0){
                System.out.println(a[i]+" tờ "+c[i]+"");
            }
//            System.out.println(c[i]+"==="+a[i]);
            n = n-(c[i]*a[i]);
            i++;
        }
    }
}

package Tuan3;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ToMau {
    int n = 10;
    int a[][]={
            {0,1,1,1,1,0,1,1,0,0},
            {1,0,1,0,1,1,0,0,0,0},
            {1,1,0,1,0,1,0,0,0,0},
            {1,0,1,0,1,1,1,0,1,0},
            {1,1,0,1,0,1,0,1,0,0},
            {0,1,1,1,1,0,0,0,0,0},
            {1,0,0,1,0,0,0,1,1,1},
            {1,0,0,0,1,0,1,0,0,1},
            {0,0,0,1,0,0,1,0,0,0},
            {0,0,0,0,0,0,1,1,0,0}
    };
    public ArrayList<Integer> tinh_bac(int[][] v){
        ArrayList<Integer> sx = new ArrayList<>();
        int d=0;
        for(int i= 0 ;i<n;i++){
            for(int j = 0;j<n;j++){
                if(v[i][j] == 1){
                    d++;
                }
            }
//            sx[i] = d;
            sx.add(d);
            d=0;
        }
        return sx;
    }
    public LinkedHashMap<Integer,Integer> sapxep(ArrayList<Integer> a){
        LinkedHashMap<Integer, Integer> hm
                = new LinkedHashMap<Integer, Integer>();
        int max = Integer.MIN_VALUE;
        int key=-1;
        while (a.size()!=0){
            for(int i =0;i<a.size();i++){
                if(max<a.get(i) && a.get(i)!=-1){
                    max = a.get(i);
                    key = i;
                }
            }
            if(key !=-1){
                hm.put(key,max);
                a.set(key,-1);
                max = Integer.MIN_VALUE;
                key = -1;
            }else {
                break;
            }
        }
        return (hm);
    }
    public static void main(String[] args) {
        ToMau t= new ToMau();
        ArrayList<Integer> tinhBac = t.tinh_bac(t.a);
        LinkedHashMap<Integer,Integer> hm =  t.sapxep(tinhBac);
        boolean[] visited = new boolean[10];
        int mau = 0;
        int []result = new int[10];
        for (int i : hm.keySet()) {
            for(int j = 0;j< t.a[i].length;j++){
                if(t.a[i][j] == 0 && visited[j]==false){
                    result[j] =mau;
                    visited[j]= true;
                }
            }
            mau ++;
        }
        for (int i = 0;i<result.length;i++){
            System.out.println("Đỉnh "+i+" tô màu thứ "+ result[i]);
        }
    }
}

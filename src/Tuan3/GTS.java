package Tuan3;

import java.util.ArrayList;
import java.util.LinkedList;

public class GTS {
    int n=0;
    int a[][] ={};
    boolean visited[] ;
    ArrayList<Integer> tour ;
    int cost;

    public GTS(int n, int[][] a, boolean[] visited, ArrayList<Integer> tour, int cost) {
        this.n = n;
        this.a = a;
        this.visited = visited;
        this.tour = tour;
        this.cost = cost;
    }
    public int tp_ke_sau(int v){
        int vt=-1;
        for (int j=0;j<n;j++){
            if(chiphi_thapnhat(v)==a[v][j] && a[v][j]!=0 && !visited[j]){
                vt = j;
                break;
            }
        }
        return vt;
    }
    public int chiphi_thapnhat(int v){
        int minValue = Integer.MAX_VALUE;
        for (int j=0;j<n;j++){
            if(minValue > a[v][j] &&!visited[j] &&a[v][j]!=0){
                minValue = a[v][j];
            }
        }
        return minValue;
    }
    public void GTS1(int n,int u,int c[][]){
        tour= new ArrayList<>();
        cost = 0;
        visited = new boolean[n];

        int v = u;
        visited[v]=true;
        tour.add(u);
        LinkedList<Integer> tp_dang_xet = new LinkedList<>();
        tp_dang_xet.add(v);
        int w;
        while (tp_dang_xet.size()!=0){
            v = tp_dang_xet.poll();
            w = tp_ke_sau(v);
            tour.add(w);
            if(w!=-1 && !visited[w]){
                cost+=chiphi_thapnhat(v);
                tp_dang_xet.add(w);
                visited[w]=true;
            }
        }
        tour.set(n,u);
        cost+=c[v][u];
    }
    public void GTS2(int b[]){
        ArrayList<Integer> bestTour  = new ArrayList<>();;
        int bestCode = Integer.MAX_VALUE;
        for(int i= 0;i<b.length;i++){
            GTS1(n,b[i],a);
            if(bestCode > cost){
                bestTour = tour;
                bestCode = cost;

            }
        }
        System.out.println(bestTour);
    }
    public static void main(String[] args) {
        int a[][]= {
                {0,	20,	42,	31,	6,24} ,
                {10,0,	17,	6,	35,18},
                {25,5,	0,	27,14,	9},
                {12,9,	24,	0,	30,12},
                {14,7,	21,	15,	0,38},
                {40,15,	16,	5,	20,0},
        };
        boolean visited [] = new boolean[6];

        ArrayList tour = new ArrayList();

        int cost = 0;


        GTS g= new GTS(6,a,visited,tour,cost);
        int b[]={1,2,4,5};
        g.GTS2(b);
    }
}

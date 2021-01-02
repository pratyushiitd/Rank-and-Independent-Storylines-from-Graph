import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Graph{
    
    public static class Vertex{
        public int id;
        public int countN;
        public int co_occur;
        Vertex next;
        Vertex prev;
        String val;
        public Vertex(int id){
            this.id = id;
            this.next = null;
            this.prev = null;
            this.countN = 0;
            this.co_occur = 0;
            this.val = "";
        }
    }
    static boolean compare1(int ac, int bc, String a, String b){
        if ((ac < bc) || (ac == bc && a.compareTo(b) < 0)){
            return false;
        }
        return true;
    }
    static boolean compare2(String a, String b){
        if (a.compareTo(b) < 0){
            return false;
        }
        return true;
    }
    static void merge (Vertex arr[], int l, int m, int r){
        int n1 = m-l+1;
        int n2 = r - m;
        Vertex arr1[] = new Vertex[n1];
        Vertex arr2[] = new Vertex[n2];
        for(int it1 = 0; it1 < n1; it1++){
            arr1[it1] = arr[l+it1];
        } 
        for(int it2 = 0; it2 < n2; it2++){
            arr2[it2] = arr[m+1+it2];
        }
        int i=0, j=0, k = l;
        while(i < n1 && j < n2){
            boolean comp = compare1(arr1[i].co_occur, arr2[j].co_occur, arr1[i].val, arr2[j].val);
            if (comp == false){
                arr[k] = arr2[j];
                k++; j++;
            }else{
                arr[k] = arr1[i];
                i++; k++;
            }
        }
        while(i<n1){
            arr[k] = arr1[i];
            i++;k++;
        }
        while(j<n2){
            arr[k] = arr2[j];
            j++;k++;
        }
    }
    static void mergesort(Vertex arr[], int l, int r){
        if (l < r){
            int m = (l+r)/2;
            mergesort(arr, l, m);
            mergesort(arr, m+1, r);
            merge(arr, l, m, r);
        }
    }

    static void merge2(ArrayList<Vertex> arr, int l, int m, int r){
        int n1 = m-l+1;
        int n2 = r - m;
        Vertex arr1[] = new Vertex[n1];
        Vertex arr2[] = new Vertex[n2];
        for(int it1 = 0; it1 < n1; it1++){
            arr1[it1] = arr.get(l+it1);
        } 
        for(int it2 = 0; it2 < n2; it2++){
            arr2[it2] = arr.get(m+1+it2);
        }
        int i=0, j=0, k = l;
        while(i < n1 && j < n2){
            boolean comp = compare2(arr1[i].val, arr2[j].val);
            if (comp == false){
                arr.set(k, arr2[j]);
                k++; j++;
            }else{
                arr.set(k, arr1[i]);
                i++; k++;
            }
        }
        while(i<n1){
            arr.set(k, arr1[i]);
            i++;k++;
        }
        while(j<n2){
            arr.set(k, arr2[j]);
            j++;k++;
        }
    }
    static void mergesort2(ArrayList<Vertex> arrL, int l, int r ){
        if (l < r){
            int m = (l+r)/2;
            mergesort2(arrL, l, m);
            mergesort2(arrL, m+1, r);
            merge2(arrL, l, m, r);
        }
    }
    static boolean compare3(ArrayList<Vertex> ar1, ArrayList<Vertex> ar2){
        int n1 = ar1.size();
        int n2 = ar2.size();
        if(n1<n2) return false;
        if(n1>n2) return true;
        for(int k1 = 0; k1 < n2; k1++){
            if (ar1.get(k1).val.compareTo(ar2.get(k1).val) == 0) continue;
            else if (ar1.get(k1).val.compareTo(ar2.get(k1).val) < 0) return false;
            return true;
        }
        return true;
    }
    static void merge3(ArrayList<ArrayList<Vertex>> arr, int l, int m, int r){
        int n1 = m-l+1;
        int n2 = r - m;
        ArrayList<ArrayList<Vertex>> arr1= new ArrayList<ArrayList<Vertex>>();
        ArrayList<ArrayList<Vertex>> arr2= new ArrayList<ArrayList<Vertex>>();
        for(int it1 = 0; it1 < n1; it1++){
            arr1.add(arr.get(l+it1));
        } 
        for(int it2 = 0; it2 < n2; it2++){
            arr2.add(arr.get(m+1+it2));
        }
        int i=0, j=0, k = l;
        while(i < n1 && j < n2){
            boolean comp = compare3(arr1.get(i), arr2.get(j));
            if (comp == false){
                arr.set(k, arr2.get(j));
                k++; j++;
            }else{
                arr.set(k, arr1.get(i));
                i++; k++;
            }
        }
        while(i<n1){
            arr.set(k, arr1.get(i));
            i++;k++;
        }
        while(j<n2){
            arr.set(k, arr2.get(j));
            j++;k++;
        }
    }
    static void mergesort3(ArrayList<ArrayList<Vertex>> arrL, int l, int r ){
        if (l < r){
            int m = (l+r)/2;
            mergesort3(arrL, l, m);
            mergesort3(arrL, m+1, r);
            merge3(arrL, l, m, r);
        }
    }
    static void dfs(int i, boolean visited[], int ind, Vertex adjList[], ArrayList<Vertex> temp){
        if (visited[i]) return;
        visited[i] = true;
        Vertex current = adjList[i];
        temp.add(adjList[i]);
        current = current.next;
        while(current!=null){
            int id = current.id;
            if (visited[id] == true) {
                current = current.next;
                continue;
            }
            Graph.dfs(id, visited, ind, adjList, temp);
            current = current.next;
        }
    }
    public static void main(String[] args)  throws IOException {
        //long startTime = System.nanoTime();
        File file = new File(args[0]);
        //FileOutputStream f = new FileOutputStream("prat_dfs_data2.txt");
        //System.setOut(new PrintStream(f));
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        line = reader.readLine();
        int nodecount = 0;
        HashMap <String , Integer> nodeMap = new HashMap <String , Integer>(); 
        while ((line = reader.readLine()) != null ) {
            String data[] = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            if (!nodeMap.containsKey(data[0].replaceAll("^\"|\"$", ""))){
                nodeMap.put(data[0].replaceAll("^\"|\"$", ""), nodecount);
                nodecount++;
            }
        }
        reader.close();
        Vertex adjList[] = new Vertex[nodecount];
        Vertex sortVer[] = new Vertex[nodecount];
        for(int it = 0; it < nodecount; it++){
            adjList[it] = new Vertex(it);
        }
        nodeMap.forEach((k, v) -> adjList[v].val = k);
        File file2 = new File(args[1]);
        BufferedReader reader2 = new BufferedReader(new FileReader(file2));
        line = reader2.readLine();
        while ((line = reader2.readLine()) !=null) {
            String data[] = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            String source = data[0].replaceAll("^\"|\"$", "");
            String target = data[1].replaceAll("^\"|\"$", "");

            int weight = Integer.parseInt(data[2]);
            Vertex current = adjList[nodeMap.get(source)];
            Vertex newbie = new Vertex(nodeMap.get(target));
            current.countN++;
            current.co_occur += weight;
            if (adjList[nodeMap.get(source)].next == null){
                current.next = newbie;
                newbie.prev = current;
            }
            else{
                current.next.prev = newbie;
                newbie.next = current.next;
                current.next = newbie;
                newbie.prev = current;
            }
            Vertex current1 = adjList[nodeMap.get(target)];
            Vertex newbie1 = new Vertex(nodeMap.get(source));
            current1.co_occur += weight;
            current1.countN++;
            if (current1.next == null){
                current1.next = newbie1;
                newbie1.prev = current1;
            }
            else{
                current1.next.prev = newbie1;
                newbie1.next = current1.next;
                current1.next = newbie1;
                newbie1.prev = current1;
            }
        }
        reader2.close();
        
        for(int it = 0; it < nodecount; it++){
            sortVer[it] = new Vertex(adjList[it].id);
            sortVer[it].val = adjList[it].val;
            sortVer[it].co_occur = adjList[it].co_occur;
        }
        if (args[2].equals("average")){
            double countdeg = 0;
            for(int it = 0; it<nodecount; it++){
                countdeg+= (double) adjList[it].countN;
            }
            countdeg = countdeg / (double) (nodecount);
            System.out.printf("%.2f", countdeg);
            System.out.println();
            //long toc= System.nanoTime();
            //System.out.println((toc- startTime)/1000000000.0);
        }
        
        if(args[2].equals("rank")){
            Graph.mergesort(sortVer, 0, nodecount-1);
            // long toc= System.nanoTime();
            // System.out.println((toc- startTime)/1000000000.0);
            System.out.print(sortVer[0].val);
            for(int kk=1; kk<nodecount; kk++){
                System.out.print("," + sortVer[kk].val.toString());
            }
            System.out.println();
        }
        if(args[2].equals("independent_storylines_dfs")){
            boolean visited[] = new boolean[nodecount];
            int ind = 0;
            ArrayList<ArrayList<Vertex>> comps = new ArrayList<ArrayList<Vertex>>();
            for(int it1 = 0; it1<nodecount; it1++){
                visited[it1] = false;
            }
            for(int it1 = 0; it1<nodecount; it1++){
                if (visited[it1] == false){
                    ArrayList<Vertex> temp = new ArrayList<Vertex>();
                    Graph.dfs(it1, visited, ind, adjList, temp);
                    ind++;
                    Graph.mergesort2(temp, 0, temp.size()-1);
                    comps.add(temp);
                }
            }
            Graph.mergesort3(comps, 0, comps.size()-1);
            //  long toc= System.nanoTime();
            //  System.out.println((toc- startTime)/1000000000.0);
            for(int a1 = 0; a1 < ind; a1++){
                System.out.print(comps.get(a1).get(0).val);
                for(int j1 = 1; j1<comps.get(a1).size(); j1++){
                    System.out.print("," + comps.get(a1).get(j1).val);
                }
                System.out.println();
            }
        }
    }
}

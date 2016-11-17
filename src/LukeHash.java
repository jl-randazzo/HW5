import java.io.FileWriter;
import java.io.IOException;

/*
 * Pretty simple implementation. Size is in the constructor method, just creates an array of the object
 * and then checks in the array entry is empty or not after an item has been hashed for entry,
 * and then enters into the newly formed BinaryTree at that location.
 */


public class LukeHash {

    int m;
    final int k = 31;
    LukeBinaryTree3[] hashMap;
    int collisions;


    public LukeHash (int m){
        //this.selector = selector;
        this.m = m;
        this.hashMap = new LukeBinaryTree3[m];
    }

    public void add(CityOb item){
        long a = item.hashCode();
        int b = Math.abs((int)((a * k) % m));
        if(hashMap[b]==null){
            hashMap[b] = new LukeBinaryTree3();
            hashMap[b].add(item);
        }
        else {
            hashMap[b].add(item);
            collisions++;
        }
    }

    public void delete(String key){
        long a = key.hashCode();
        int b = (int)((a * k) % m);

        if(hashMap[b]!=null){
            hashMap[b].delete(key);
            if(hashMap[b].root==null) hashMap[b] = null;
        }
    }

    public CityOb get(String key){

        long a = key.hashCode();
        int b = (int)((a * k) % m);
        return hashMap[b].search(key);

    }

    public void output(FileWriter fw, boolean selector) throws IOException{
        if(selector) fw.write("\n\nPREORDER OUTPUT (For each hashing location):\n\n");
        else fw.write("\n\nINORDER OUTPUT (For each hashing location):\n\n");
        for(int i = 0; i < m; i++){
            fw.write(i + ")\n");

            if(hashMap[i]!= null) hashMap[i].output(selector, fw);
            else fw.write("<<EMPTY>>\n");
            fw.write("\n");
        }
    }
}

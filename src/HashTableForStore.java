//import java.util.Scanner;

public class HashTableForStore {
    public store[] stores;
    public int num;
    public int size;

    public HashTableForStore(int size){
        stores = new store[size];
        num = 0;
        this.size = size;
    }

    public void add(store s){
        if (!existed(s.getName())){
            if (num == size){
                store[] temp = new store[size+20];
                System.arraycopy(stores, 0, temp, 0, num);
                stores = temp;
            }
            stores[num++] = s;
        } else {
            System.out.println("Store Exists.");
        }
    }

    // O(n) delete the store.
    public void delete(store s){
        boolean r = false;
        for (int i = 0; i < num; i++){
            if (stores[i].getName().equals(s.getName()) && stores[i].getAddress().equals(s.getAddress())){
                stores[i] = null;
                num--;
                r = true;
            }
        }
        if (r){
            System.out.println("Successfully Deleted.");
        } else {
            System.out.println("Store Not Found.");
        }
    }

    // O(n) look up for the store with the given name
    public boolean existed(String name){
        for (store store : stores){
            if (store != null){
                if (store.getName().equals(name) && store.getAddress().equals(name)){
                    return true;
                }
            }
        }
        return false;
    }

    // O(n) return the position of the given store, if not found, return -1.
    public int find(store s){
        int i = 0;
        for (store store : stores){
            if (store != null){
                if (store.getName().equals(s.getName()) && store.getAddress().equals(s.getAddress())){
                    return i;
                }
            }
            i++;
        }
        return -1;
    }

    public void optimization(){
        double p = num / size;
        if (p < 0.6){
            store[] temp = new store[(int) (size*0.6)];
            int i = 0;
            for (store store : stores){
                if (store!=null){
                    temp[i++] = store;
                }
            }
            num = i;
            stores = temp;
        }
    }

    public int getNum() {
        return num;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
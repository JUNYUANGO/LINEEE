import java.util.Scanner;

public class HashTable {
    public tenantNode[] tenants;
    public int size;
    public int p;
    public int num;

    //constructor: HashTable with Linked List
    public HashTable (int size){
        try {
            this.size = size;
            this.tenants = new tenantNode[size];
            this.num = 0;

            //find an abnormal value for p.
            this.p = size;
            while (p-- > 0){
                if (p % 2 == 0) {
                    break;
                }
            }
        } catch (IllegalArgumentException e){
            System.out.println("Please enter a correct size.");
            throw e;
        }
    }

    public int hash(String id){
        int index = 0;
        boolean result = false;
        while (!result){
            /*
              Assume that an ID will be valid if with a length of 6 or larger.
              This part will be changed if checking the validity of ID.
             */
            if (id.length() < 6){
                System.out.println("Please enter correct id.");
                return -1;
            }
            else {
                index = ((26*26*(id.charAt(0)+id.charAt(1))+26*(id.charAt(2)+id.charAt(3))+id.charAt(id.length()-1)) % p);
                result = true;
            }
        }
        return index;
    }

    /**
     * @param t: tenant we are interested in.
     *
     * Collision:
     *   if t is already in the database, it will print "Account Existed" and not do anything.
     *   if there is a collision (seldom), it will link it up as a linked list.
     */
    public void add(Tenant t){
        num++;
        if (existed(t.getId())){
            System.out.println("Account Existed.");
            num--;
        } else {
            int index = hash(t.getId());
            Tenant temp = new Tenant(t.getName(), t.getId(), t.getNickname(), t.getApt(), t.getAmount(), t.getCredit(), t.getPhone());
            if (tenants[index] == null) {
                tenants[index] = new tenantNode(temp, null);
            } else {
                tenantNode ptr = tenants[index];
                while (ptr.getNext() != null) {
                    ptr = ptr.getNext();
                }
                ptr.setNext(new tenantNode(temp, null));
            }
        }
    }

    /**
     *
     * @param t: the tenant we want to delete
     *         if it does not exist, "The account does not exist. Fail to delete." will be printed.
     */
    public void delete(Tenant t){
        if (!existed(t.getId())){
            System.out.println("The account does not exist. Fail to delete.");
        } else {
            int index = hash(t.getId());
            tenantNode ptr = tenants[index];

            //check the first one
            if (ptr.getT().getId().equals(t.getId())){
                tenants[index] = ptr.getNext();
            } else {
                tenantNode trailer = ptr;
                ptr = ptr.getNext();
                while (ptr.getNext() != null){
                    if (ptr.getT().getId().equals(t.getId())){
                        trailer.setNext(ptr.getNext());
                        num--;
                        System.out.println("Account is successfully deleted. (Name: "+ptr.getT().getName()+")");
                        break;
                    } else {
                        trailer = trailer.getNext();
                        ptr = ptr.getNext();
                    }
                }
            }
        }
    }

    /**
     *
     * @param id: to check if the tenant with the given id is already in database.
     * @return return true if it exists, false for no.
     */
    public boolean existed(String id) {
        int index = hash(id);
        if (tenants[index] != null) {
            tenantNode ptr = tenants[index];
            if (ptr.getT().getId().equals(id)){
                return true;
            } else {
                while (ptr != null) {
                    if (ptr.getT().getId().equals(id)) {
                        return true;
                    } else {
                        ptr = ptr.getNext();
                    }
                }
            }
        }
        return false;
    }

    public boolean findByName(String name) {
        for (tenantNode t : tenants){
            if (t!=null){
                tenantNode ptr = t;
                if (ptr.getT().getName().equals(name)){
                    return true;
                } else {
                    while (ptr != null) {
                        if (ptr.getT().getName().equals(name)){
                            return true;
                        } else {
                            ptr = ptr.getNext();
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     *
     * @return size: the size of HashTable.
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @return num: the number of tenants.
     */
    public int getNum() {
        return num;
    }

    //display the Hash Table and its Performance
    public void display(){
        countHelper();
        System.out.println("average collision length: "+avg);
        System.out.println("length of the longest chain: "+longest);
        System.out.println("number of empty indices: "+empty);
        System.out.println("number of non-empty indices: "+(size-empty));

    }

    //variables for counter():
    private int longest = 0;
    private int avg = 0;
    private int empty = 0;

    //counter method to count for unique tokens, longest chain, avg collision length, empty and non-empty indices
    public void countHelper(){
        for (int i = 0; i < size; i++){
            if (tenants[i] == null){
                empty++;
            } else {
                //check for longest chain
                int temp = 0;
                tenantNode ptr = tenants[i];
                while (ptr != null){
                    ptr = ptr.getNext();
                    temp++;
                }
                if (temp > longest){
                    longest = temp;
                }
            }
        }
        avg = getNum()/(size-empty);
    }

//    /*
//    Return a random ID like: X0123456X
//     */
//    public String getRandomID(){
//        String[] s = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
//        StringBuilder temp = new StringBuilder();
//        boolean r = false;
//        while (!r) {
//            int f = (int) (s.length*Math.random());
//            temp.append(s[f]);
//            int temp1 = (int) (10000000 * Math.random());
//            if (Integer.toString(temp1).length() == 7){
//                temp.append(temp1);
//                break;
//            }
//        }
//        int l = (int) (s.length*Math.random());
//        temp.append(s[l]);
//        return temp.toString();
//    }


//    test for HashTable
//    public static void main(String[] args) {
//        Scanner t = new Scanner(System.in);
//        System.out.println("Please enter the size you want to test (Integer only): ");
//        int size = t.nextInt();
//        HashTable test = new HashTable(size);
//        int num = (int) (size*0.75);
//
//        String[] id = new String[num];
//        String[] s = new String[]{"Abel", "Apple", "Bella", "Chris", "Dave", "Eason", "Frozen", "Git", "Hud",
//                "Intellij", "Jason", "Kris", "Lonely", "Money", "Niko", "OVO", "Pokemon", "Qatar", "Rstar", "Stewart", "TwT"};
//        String[] name = new String[num];
//
//        for (int i = 0; i < id.length; i++) {
//            id[i] = test.getRandomID();
//            int f = (int) (s.length*Math.random());
//            int l = (int) (s.length*Math.random());
//            String temp2 = s[f]+" "+s[l];
//            name[i] = temp2;
//        }
//
//        for (int j = 0; j < num; j++){
//            test.add(new Tenant(name[j], id[j], name[j], "test", 100.0, 100, "phoneNumber"));
//        }
//
//
//        //num check
//        System.out.println("---getNum check---");
//        System.out.println("Supposed to have "+test.getNum()+" tenants. Test gives: "+test.getNum()+"");
//
//        //size check
//        System.out.println("---getSize check---");
//        System.out.println("Supposed to have "+test.getSize()+" tenants. Test gives: "+test.getSize()+"");
//
//        //existed check
//        System.out.println("---Existed check---");
//        for (int j = 0; j < num; j++){
//            boolean result = test.existed(id[j]);
//            if (!result){
//                System.out.println("test failed x"+j);
//            }
//        }
//
//        //collision performance check
//        System.out.println("---Performance check---");
//        test.display();
//
//        //delete check
//        System.out.println("---Delete check---");
//        for (int k = 0; k < num; k++){
//            Tenant temp = new Tenant(name[k], id[k], name[k], "test", 0.0, 100, "phoneNumber");
//            test.delete(temp);
//        }
//        for (int x = 0; x < size; x++){
//            if (test.tenants[x] != null){
//                System.out.println("test failed x"+x);
//            }
//        }
//
//        System.out.println("---test close---");
//    }
}

//linked list for class Tenant
class tenantNode {
    //instances
    private Tenant t;
    private tenantNode next;

    //constructor
    public tenantNode(Tenant t1, tenantNode t2) {
        this.t = t1;
        this.next = t2;
    }

    //setter && getter
    public Tenant getT(){
        return t;
    }

    public void setT(Tenant t){
        this.t = t;
    }

    public tenantNode getNext(){
        return next;
    }

    public void setNext(tenantNode next){
        this.next = next;
    }
}
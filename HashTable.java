import java.util.Scanner;

public class HashTable {
    private final tenantNode[] tenants;
    private final int size;
    private int p;
    private int num;

    //constructor: HashTable with Linked List
    public HashTable (int size){
        try {
            this.size = size;
            this.tenants = new tenantNode[size];
            this.num = 0;

            //find an abnormal value for p.
            this.p = size;
            while (p-- > 0){
                if (p % 2 != 0) {
                    if (p % 3 != 0) {
                        if (p % 5 != 0) {
                            break;
                        }
                    }
                }
            }
        } catch (Exception e){
            System.out.println("Please enter a correct size.");
            throw e;
        }
    }

    public int hash(int id){
        int index = 0;
        String s = Integer.toString(id);
        boolean result = false;
        while (!result){
            /*
              Assume that an ID will be valid if with a length of 5 or larger.
              This part will be changed if checking the validity of ID.
             */
            if (s.length() < 5){
                System.out.println("Please enter correct id.");
                return -1;
            }
            else {
                index = (26*26*26*s.charAt(0)+26*26*s.charAt(1)+26*s.charAt(2)+13*s.charAt(3)+7*s.charAt(4)) % p;
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
            Tenant temp = new Tenant(t.getName(), t.getId(), t.getNickname(), t.getApt(), t.getAmount(), t.getCredit());
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
            if (ptr.getT().getId() == t.getId()){
                tenants[index] = ptr.getNext();
            } else {
                tenantNode trailer = ptr;
                ptr = ptr.getNext();
                while (ptr.getNext() != null){
                    if (ptr.getT().getId() == t.getId()){
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
    public boolean existed(int id) {
        int index = hash(id);
        if (tenants[index] != null) {
            tenantNode ptr = tenants[index];
            if (ptr.getT().getId() == id){
                return true;
            } else {
                while (ptr != null) {
                    if (ptr.getT().getId() == id) {
                        return true;
                    } else {
                        ptr = ptr.getNext();
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

    /**
     * print out the number of collisions with their indices, empty space, the length of the longest chain.
     */
    public void findCollision(){
        int countCol = 0;
        int empty = 0;
        int longest = 0;
        for (tenantNode tenant : tenants) {
            int c = 0;
            if (tenant != null) {
                tenantNode ptr = tenant;
                while (ptr != null) {
                    ptr = ptr.getNext();
                    c++;
                }
                if (c > 1) {
                    countCol++;
                }
            } else {
                empty++;
            }
            if (c > longest) {
                longest = countCol;
            }
        }
        System.out.println("Number of Collisions (c > 1): "+countCol);
        System.out.println("Number of empty space (c = 0): "+empty);
        System.out.println("Longest Chain: "+longest);
    }


    public static void main(String[] args) {
        Scanner t = new Scanner(System.in);
        System.out.println("Please enter the size you want to test (Integer only): ");
        int size = t.nextInt();
        HashTable test = new HashTable(size);
        int num = (int) (size*0.75);
        for (int i = 0; i < num; i++){
            Tenant temp = new Tenant("test", 20000+i*num*13, "test_nickname", 1000+i, 0.0, 100);
            test.add(temp);
        }

        //num check
        System.out.println("---getNum check---");
        System.out.println("Supposed to have "+test.getNum()+" tenants. Test gives: "+test.getNum()+"");

        //size check
        System.out.println("---getSize check---");
        System.out.println("Supposed to have "+test.getSize()+" tenants. Test gives: "+test.getSize()+"");

        //collision check
        test.findCollision();

        //existed check
        System.out.println("---Existed check---");
        for (int j = 0; j < num; j++){
            boolean result = test.existed(20000+j*num*13);
            if (!result){
                System.out.println("test failed x"+j);
            }
        }

        //delete check
        System.out.println("---Delete check---");
        for (int k = 0; k < num; k++){
            Tenant temp = new Tenant("test", 20000+k*num*13, "test_nickname", 1000+k, 0.0, 100);
            test.delete(temp);
        }
        for (int x = 0; x < size; x++){
            if (test.tenants[x] != null){
                System.out.println("test failed x"+x);
            }
        }
        System.out.println("---test close---");
    }
}

//linked list of tenants
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
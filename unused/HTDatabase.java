public class HTDatabase implements Database{
    private final HashTable database;
    private final HashTableForStore storeDB;

    public HTDatabase(int sizeT, int sizeS){
        database = new HashTable(sizeT);
        storeDB = new HashTableForStore(sizeS);
    }

    public HTDatabase(){
        database = new HashTable(100);
        storeDB = new HashTableForStore(20);
    }

    @Override
    public void insert(Tenant t) {
        database.add(t);
    }

    @Override
    public void delete(Tenant t) {
        database.delete(t);
    }

    @Override
    public void findByID(String id) {
        if (database.existed(id)){
            System.out.println("Account Exists!");
        } else {
            System.out.println("Account Not Found.");
        }
    }

    @Override
    public void findByName(String name) {
        if (database.findByName(name)){
            System.out.println("Tenant Found!");
        } else {
            System.out.println("Tenant Not Found.");
        }
    }

    @Override
    public void updateID(Tenant t, String id) {
        if (!database.existed(t.getId())){
            System.out.println("Tenant Not Found. Fail to Update.");
        } else {
            t.setId(id);
            int idx = database.hash(t.getId());
            String oldID = t.getId();
            tenantNode ptr = database.tenants[idx];
            if (ptr.getT().getId().equals(oldID)){
                ptr.getT().setId(id);
            } else {
                while (ptr != null){
                    if (ptr.getT().getId().equals(oldID)) {
                        ptr.getT().setId(id);
                    } else {
                        ptr = ptr.getNext();
                    }
                }
            }
            System.out.println("ID is successfully updated");
        }
    }

    @Override
    public void updateName(Tenant t, String name) {
        if (!database.existed(t.getId())){
            System.out.println("Tenant Not Found. Fail to Update.");
        } else {
            t.setName(name);
            int idx = database.hash(t.getId());
            String oldName = t.getName();
            tenantNode ptr = database.tenants[idx];
            if (ptr.getT().getId().equals(oldName)){
                ptr.getT().setId(name);
            } else {
                while (ptr != null){
                    if (ptr.getT().getId().equals(oldName)) {
                        ptr.getT().setId(name);
                    } else {
                        ptr = ptr.getNext();
                    }
                }
            }
            System.out.println("Name is successfully updated");
        }
    }

    @Override
    public void updateNickName(Tenant t, String nickname) {
        if (!database.existed(t.getId())){
            System.out.println("Tenant Not Found. Fail to Update.");
        } else {
            t.setNickname(nickname);
            System.out.println("Nickname is successfully updated");
        }
    }

    @Override
    public void updateApt(Tenant t, String apt) {
        if (!database.existed(t.getId())){
            System.out.println("Tenant Not Found. Fail to Update.");
        } else {
            t.setApt(apt);
            System.out.println("Apt is successfully updated");
        }
    }

    @Override
    public void updateAmount(Tenant t, double amount) {
        if (!database.existed(t.getId())){
            System.out.println("Tenant Not Found. Fail to Update.");
        } else {
            t.setAmount(amount);
            System.out.println("Amount is successfully updated");
        }
    }

    @Override
    public void updateCredit(Tenant t, int credit) {
        if (!database.existed(t.getId())){
            System.out.println("Tenant Not Found. Fail to Update.");
        } else {
            t.setAmount(credit);
            System.out.println("Credit is successfully updated");
        }
    }

    @Override
    public void updatePhone(Tenant t, String phoneNum) {
        if (!database.existed(t.getId())){
            System.out.println("Tenant Not Found. Fail to Update.");
        } else {
            t.setPhone(phoneNum);
            System.out.println("Phone number is successfully updated");
        }
    }

    //methods for the store database
    public void insertS(store s) {
        storeDB.add(s);
    }

    public void delete(store s) {
        storeDB.delete(s);
    }

    public void updateNameS(store s, String name){
        int r = storeDB.find(s);
        if (r != -1){
            s.setName(name);
            storeDB.stores[r].setName(name);
            System.out.println("Name is successfully updated");
        } else {
            System.out.println("Store Not Found. Fail to Update.");
        }
    }

    public void findByNameS(String name){
        if (storeDB.existed(name)){
            System.out.println("Store Found!");
        } else {
            System.out.println("Store Not Found.");
        }
    }
}

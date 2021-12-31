public class HTDatabase implements Database{
    private final HashTable database;

    public HTDatabase(int size){
        database = new HashTable(size);
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
            System.out.println("ID is successfully updated");
        }
    }

    @Override
    public void updateName(Tenant t, String name) {
        if (!database.existed(t.getId())){
            System.out.println("Tenant Not Found. Fail to Update.");
        } else {
            t.setName(name);
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
}

interface Database {
    void insert(Tenant t);
    void delete(Tenant t);
    void findByID(String id);
    void findByName(String name);
    void updateID(Tenant t, String id);
    void updateName(Tenant t, String name);
    void updateNickName(Tenant t, String nickname);
    void updateApt(Tenant t, String apt);
    void updateAmount(Tenant t, double amount);
    void updateCredit(Tenant t, int credit);
    void updatePhone(Tenant t, String phoneNum);
}
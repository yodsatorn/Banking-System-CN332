public interface DatabaseCSVInterface {
    public void savingAcc(BankAccount bankAcc,Client clientAcc, int accType);
    public void savingTransaction(String transaction,String amountOftransaction,BankAccount bankAcc);
}

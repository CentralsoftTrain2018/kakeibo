package vo;

import java.sql.Date;

public class ExpenseVo {
    private int expenseId;
    private int expenseKingaku;
    private int categoryId;
    private String expenseName;
    private Date expenseDate;
    private String userId;


    public ExpenseVo() {
        super();
    }

    public ExpenseVo(int expenseId, int expenseKingaku, int categoryId, String expenseName, Date expenseDate,
            String userId) {
        super();
        this.expenseId = expenseId;
        this.expenseKingaku = expenseKingaku;
        this.categoryId = categoryId;
        this.expenseName = expenseName;
        this.expenseDate = expenseDate;
        this.userId = userId;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getExpenseKingaku() {
        return expenseKingaku;
    }

    public void setExpenseKingaku(int expenseKingaku) {
        this.expenseKingaku = expenseKingaku;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ExpenseVo [expenseId=" + expenseId + ", expenseKingaku=" + expenseKingaku + ", categoryId="
                + categoryId + ", expenseName=" + expenseName + ", expenseDate=" + expenseDate + ", userId=" + userId
                + "]";
    }


}

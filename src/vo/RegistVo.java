package vo;

public class RegistVo
{
    private String userId;
    private String password;
    private String mail;
    private int income;
    private String message;
    private String registMonth;

    public RegistVo()
    {
        super();
    }

    public RegistVo(String userId, String password, String mail, int income, String registMonth)
    {
        this.userId = userId;
        this.password = password;
        this.mail = mail;
        this.income = income;
        this.registMonth = registMonth;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public int getIncome()
    {
        return income;
    }

    public void setIncome(int income)
    {
        this.income = income;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getRegistMonth() {
        return registMonth;
    }

    public void setRegistMonth(String registMonth) {
        this.registMonth = registMonth;
    }

    @Override
    public String toString() {
        return "RegistVo [userId=" + userId + ", password=" + password + ", mail=" + mail + ", income=" + income
                + ", message=" + message + "]";
    }

}

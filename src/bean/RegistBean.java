package bean;

public class RegistBean
{
    private String userId;
    private String password;
    private String mail;
    private int income;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId( String userId )
    {
        this.userId = userId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail( String mail )
    {
        this.mail = mail;
    }

    public int getIncome()
    {
        return income;
    }

    public void setIncome( int income )
    {
        this.income = income;
    }

    @Override
    public String toString()
    {
        return "userId: " + userId + ", password: " + password + ", mail: " + mail + ", income: " + income;
    }
}

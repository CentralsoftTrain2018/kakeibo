package bean;

public class KakeiboBean {
    private String message;

    public KakeiboBean() {
        super();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "KakeiboBean [message=" + message + "]";
    }
}

package user;

public abstract class User {
    private String name;
    private String email;
    private Thread thread;

    public abstract void work();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void start() {
        if(thread == null) {
            thread = new Thread((Runnable) this);
            thread.start();
        }
    }

    @Override
    public String toString() {
        return  "name: " + name + "\n" +
                "email: " + email + "\n";
    }
}

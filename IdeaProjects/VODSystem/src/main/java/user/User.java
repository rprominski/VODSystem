package user;

import java.io.Serializable;

public abstract class User implements Serializable {
    private String name;
    private String email;
    private transient Thread thread;

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

    public void stop() {
        Thread.currentThread().interrupt();
    }

    @Override
    public String toString() {
        return  "name: " + name + "\n" +
                "email: " + email + "\n";
    }
}

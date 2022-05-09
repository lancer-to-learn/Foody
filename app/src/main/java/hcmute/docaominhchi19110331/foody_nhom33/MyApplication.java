package hcmute.docaominhchi19110331.foody_nhom33;

import android.app.Application;

public class MyApplication extends Application {
    private int userId = 0;

    public int getuserId() {
        return userId;
    }

    public void setuserId(int id) {
        this.userId = id;
    }
    public boolean checkUser(){
        return this.userId != 0;
    }
    // set
    //((MyApplication) this.getApplication()).setuserId(4);

    // get
    //int s = ((MyApplication) this.getApplication()).getuserId();
}

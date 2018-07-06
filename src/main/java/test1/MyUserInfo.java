package test1;

import com.jcraft.jsch.UserInfo;

public class MyUserInfo implements UserInfo {

    public String getPassphrase() {
        // TODO Auto-generated method stub
        System.out.println("MyUserInfo.getPassphrase()");
        return null;
    }

    public String getPassword() {
        // TODO Auto-generated method stub
        System.out.println("MyUserInfo.getPassword()");
        return null;
    }

    public boolean promptPassphrase(String arg0) {
        // TODO Auto-generated method stub
        System.out.println("MyUserInfo.promptPassphrase()");
        System.out.println(arg0);
        return false;
    }

    public boolean promptPassword(String arg0) {
        // TODO Auto-generated method stub
        System.out.println("MyUserInfo.promptPassword()");
        System.out.println(arg0);
        return false;
    }

    public boolean promptYesNo(String arg0) {
        // TODO Auto-generated method stub'
        System.out.println("MyUserInfo.promptYesNo()");
        System.out.println(arg0);
        if (arg0.contains("The authenticity of host")) {
            return true;
        }
        return true;
    }

    public void showMessage(String arg0) {
        // TODO Auto-generated method stub
        System.out.println("MyUserInfo.showMessage()");
    }
}

package Model;

public class Session {
    private static Students curStd;
    private static boolean isAdmin = false;

    public static void setStd(Students s) {
        curStd = s;
        isAdmin = false;
    }

    public static void setAdm() {
        curStd = null;
        isAdmin = true;
    }

    public static void logOut() {
        curStd = null;
        isAdmin = false;
    }

    public static boolean getAdmin() {
        return isAdmin;
    }

    public static Students getStudent() {
        return curStd;
    }
}

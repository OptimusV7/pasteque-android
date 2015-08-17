package fr.pasteque.client.data.DataSavable;


import android.content.Context;
import fr.pasteque.client.models.Login;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nsvir on 14/08/15.
 * n.svirchevsky@gmail.com
 */
public class LoginData extends AbstractDataSavable {

    public static final String FILENAME = "login.data";

    private Login login = null;

    public void setLogin(Login login) {
        this.login = login;
    }

    public Login getLogin(Context ctx) {
        if (this.login == null) {
            this.loadNoMatterWhat(ctx);
        }
        return this.login;
    }

    @Override
    protected String getFileName() {
        return LoginData.FILENAME;
    }

    @Override
    protected List<Object> getObjectList() {
        List<Object> result = new ArrayList<>();
        result.add(this.login);
        return result;
    }

    @Override
    protected int getNumberOfObjects() {
        return 1;
    }

    @Override
    protected void recoverObjects(List<Object> objs) {
        this.login = (Login) objs.get(0);
    }
}
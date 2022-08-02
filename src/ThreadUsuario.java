import instances.DBInstance;
import instances.LogInstance;
import instances.LoginInstance;

import java.util.Objects;

public class ThreadUsuario extends Thread {

    LoginInstance loginInstance;
    DBInstance dbInstance;
    String senha;
    String usuario;

    public ThreadUsuario(LoginInstance loginInstance, DBInstance dbInstance, String usuario, String senha) {
        this.loginInstance = loginInstance;
        this.dbInstance = dbInstance;
        this.usuario = usuario;
        this.senha = senha;
    }

    @Override
    public void run() {
        Long id = loginInstance.realizaLogin(usuario, senha);
        if(!Objects.equals(id, Long.valueOf(-1))) {
            dbInstance.realizaInsert(id, "trabalho", "cargo, salario", "'desenvolvedor', 1000");
            dbInstance.realizaSelect(id, "trabalho", "cargo = 'desenvolvedor'");
            dbInstance.realizaUpdate(id, "trabalho", "cargo = 'desenvolvedor'", "cargo", "'desempregado'");
            dbInstance.realizaDelete(id, "trabalho", "cargo = 'desempregado'");
        }
    }
}

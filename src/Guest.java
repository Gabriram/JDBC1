import java.sql.SQLException;

public class Guest {
    private int id;
    private String nome, email, phone;

    public Guest(int id, String nome, String email, String phone) throws SQLException {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Guest [id=" + id + ", nome=" + nome + ", email=" + email + ", phone=" + phone + "]";
    }

}

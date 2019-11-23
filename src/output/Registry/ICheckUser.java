package output.Registry;

//Factory Method
public interface ICheckUser {
    Boolean checkUser(String userName, byte[] password);
}

package output.Registry;

//Factory Method
public class registryFactory {
    public ICheckUser getRegistryType(String regType){
        if(regType.equals("Login")){
            return new Login();
        }
        else if(regType.equals("Register")){
            return new Register();
        }
        return null;
    }
}

package bo.util;

import bo.impl.UserBoImpl;

public class BoFactory {
    public static BoFactory boFactory;
    private BoFactory(){

    }
    public static BoFactory getBoFactory(){
        return (boFactory==null)?boFactory= new BoFactory():boFactory;

    }

    public SuperBo getBO(BoTypes boTypes){
        switch (boTypes){
            case USER:
                return new UserBoImpl();

            default:
                return null;
        }
    }
}

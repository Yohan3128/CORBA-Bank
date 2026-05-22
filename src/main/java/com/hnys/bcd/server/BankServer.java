package com.hnys.bcd.server;

import BankingApp.Account;
import BankingApp.AccountHelper;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class BankServer {
    public static void main(String[] args) {
        ORB orb = ORB.init(args, null);
        try {
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();

            AccountImpl account = new AccountImpl();
            Object object = rootPOA.servant_to_reference(account);

            Account href = AccountHelper.narrow(object);

            Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            String name = "Bank";

            NameComponent[] path = ncRef.to_name(name);
            ncRef.rebind(path, href);

            System.out.println("Bank Server Started!");

            orb.run();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package deliveryConfigurationSanity.pages;

import java.util.List;

public class ModuleJDBC extends BasePage{


    protected static DataBaseCon DBCon = new DataBaseCon();
    public static void main(String[] args) {
        List<String> Module =
                DBCon.readDataBasePDK("Select DISPLAY_NAME,RUN_DAEMON Module From NPDREPORTMANAGER_DQIBLD.TB_FF_MODULE_SUMMARY_SCHEMA WHERE DISPLAY_NAME='Canada Beauty POS Monthly - FF Profile' ", "Module");
        System.out.print(Module);

        System.out.println();

        List<String> data= DBCon.readDataBasePDK("Select DISPLAY_NAME,RUN_DAEMON Module From NPDREPORTMANAGER_DQIBLD.TB_FF_MODULE_SUMMARY_SCHEMA WHERE DISPLAY_NAME='Housewares POS - FF Profile' ", "Module");

        System.out.print(data);
    }


}


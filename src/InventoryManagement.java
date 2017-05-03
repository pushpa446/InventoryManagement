import java.util.ArrayList;
import java.util.HashMap;

public class InventoryManagement {
    public static void main(String args[]){
        
        HashMap<String,HashMap<String,Integer>> inventory = new HashMap<>();
        ArrayList<HashMap<String,String>> transactionLog = new ArrayList<>();
        
        HashMap<String,Integer> warehouseItems = new HashMap<>();
        warehouseItems.put("Paracetamol",10);
        warehouseItems.put("Ibuprofen",50);
        warehouseItems.put("Syringe",80);
        warehouseItems.put("Scalpel",100);
        
        HashMap<String,Integer> ICUItems = new HashMap<>();
        ICUItems.put("Paracetamol",0);
        ICUItems.put("Ibuprofen",20);
        ICUItems.put("Syringe",7);
        ICUItems.put("Scalpel",8);
       
        HashMap<String,Integer> dispensaryItems = new HashMap<>();
        dispensaryItems.put("Paracetamol",50);
        dispensaryItems.put("Ibuprofen",33);
        dispensaryItems.put("Syringe",10);
        dispensaryItems.put("Scalpel",80);
    
        inventory.put("Warehouse",warehouseItems);
        inventory.put("ICU",ICUItems);
        inventory.put("Dispensary",dispensaryItems);
        
        HashMap<String,String> log1 = new HashMap<>();
        log1.put("from","Warehouse");
        log1.put("to","Dispensary");
        log1.put("item","Paracetamol");
        log1.put("quantity","2");
        
        HashMap<String,String> log2 = new HashMap<>();
        log2.put("from","Warehouse");
        log2.put("to","Dispensary");
        log2.put("item","Ibuprofen");
        log2.put("quantity","5");
        
        HashMap<String,String> log3 = new HashMap<>();
        log3.put("from","Dispensary");
        log3.put("to","ICU");
        log3.put("item","Syringe");
        log3.put("quantity","2");
        
        HashMap<String,String> log4 = new HashMap<>();
        log4.put("from","Warehouse");
        log4.put("to","ICU");
        log4.put("item","Scalpel");
        log4.put("quantity","10");
        
        transactionLog.add(log1);
        transactionLog.add(log2);
        transactionLog.add(log3);
        transactionLog.add(log4);
        
        InventoryManagement inventoryManagement = new InventoryManagement();
        System.out.println(inventoryManagement.processTransactions(inventory,transactionLog));
        inventoryManagement.displayInventory(inventory);
    }
    
    private void displayInventory(HashMap<String, HashMap<String, Integer>> inventory) {
        for (String location :
                inventory.keySet()) {
            System.out.println(location+":");
            for (String item:
                 inventory.get(location).keySet()) {
                System.out.println(item+" : "+inventory.get(location).get(item));
            }
        }
    }
    
    private HashMap<String, HashMap<String, Integer>> processTransactions(HashMap<String, HashMap<String, Integer>> inventory, ArrayList<HashMap<String, String>> transactionLog) {
        if( transactionLog.isEmpty()){
            return inventory;
        }
        HashMap<String, Integer> from = inventory.get(transactionLog.get(0).get("from"));
        HashMap<String, Integer> to = inventory.get(transactionLog.get(0).get("to"));
        
        from.put(transactionLog.get(0).get("item"), from.get(transactionLog.get(0).get("item")) - Integer.valueOf(transactionLog.get(0).get("quantity")));
        to.put(transactionLog.get(0).get("item"), to.get(transactionLog.get(0).get("item")) + Integer.valueOf(transactionLog.get(0).get("quantity")));
        
        inventory.put(transactionLog.get(0).get("from"),from);
        inventory.put(transactionLog.get(0).get("to"),to);
        
        transactionLog.remove(0);
        return processTransactions(inventory, transactionLog);
    }
}

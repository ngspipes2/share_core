package pt.isel.ngspipes.share_core.logic.service.permission;

public class Access<K> {

    public enum Operation {
        GET, INSERT, UPDATE, DELETE
    }



    public String userName;
    public Operation operation;
    public K entityId;



    public Access(String userName, Operation operation, K entityId) {
        this.userName = userName;
        this.operation = operation;
        this.entityId = entityId;
    }

    public Access() { }

}

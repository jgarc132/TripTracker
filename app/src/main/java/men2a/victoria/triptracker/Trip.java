package men2a.victoria.triptracker;

import java.util.Date;

//**********************************//
//*Created by Vicky on 6/30/2017.***//
//**********************************//

//part 3.1.3
public class Trip implements IntentData{

    public String objectId;
    public String name;
    public String description;
    public Date startDate;
    public Date endDate;
    public boolean shared;
    public String ownerId;
    public String ownerID;

    public String getObjectId() {
        return objectId;
    }
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getName() {

        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isShared() {
        return shared;
    }
    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public String getOwnerId(){
        return ownerId;
    }
    public void setOwnerId(String ownerId){
        this.ownerId = ownerId;
    }

    public String getOwnerID(){
        return objectId;
    }
    public void setOwnerID(String objectId){
        this.ownerID = objectId;
    }

}

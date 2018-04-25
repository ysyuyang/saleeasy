package cn.supstore.biz.model;

 public class TmHouseFabDetail{
  public   Long detailId;
  public   Long fabId;
  public   Long houseId;
  public   String appId;

  public   Long getDetailId(){
    return detailId;
  }
  public   void setDetailId(Long DetailId){
    detailId=DetailId;
  }
  public   Long getFabId(){
    return fabId;
  }
  public   void setFabId(Long FabId){
    fabId=FabId;
  }
  public   Long getHouseId(){
    return houseId;
  }
  public   void setHouseId(Long HouseId){
    houseId=HouseId;
  }
  public   String getAppId(){
    return appId;
  }
  public   void setAppId(String AppId){
    appId=AppId;
  }

}
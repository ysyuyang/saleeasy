package cn.supstore.biz.model;

 public class TmHouseFab{
  public   Long fabId;
  public   Long attrId;
  public   String val;
  public   String appId;

  public   Long getFabId(){
    return fabId;
  }
  public   void setFabId(Long FabId){
    fabId=FabId;
  }
  public   Long getAttrId(){
    return attrId;
  }
  public   void setAttrId(Long AttrId){
    attrId=AttrId;
  }
  public   String getVal(){
    return val;
  }
  public   void setVal(String Val){
    val=Val;
  }
  public   String getAppId(){
    return appId;
  }
  public   void setAppId(String AppId){
    appId=AppId;
  }

}
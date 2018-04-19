package cn.supstore.biz.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by liusijin on 2016/5/23.
 */
@Entity
@Table(name = "tm_event")
public class TmEvent {

    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDHexGenerator")
    @GeneratedValue(generator = "uuid")
    @Column(length = 32)
    @Id
    private String id;

    private String description;
    private Double randomNum = 12.655433;
    private Float displayNum = 12.5f;
    private boolean isDelete;
    private Long createBy;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.s1Prepare;

    @Column(name = "EVENT_DATE", nullable = false)
    private LocalDateTime date = LocalDateTime.now();
    private Integer ver = 0;

    public enum OrderStatus {s1Prepare, s2AliPay, s2WxPay, s2GuiderPay, s3CashPayed, s3OnlinePayed, s4Logistics, e5Received, e5WaitInstore, s6ReqBack, s6Rollback}

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public Double getRandomNum() {
        return randomNum;
    }

    public void setRandomNum(Double randomNum) {
        this.randomNum = randomNum;
    }

    public Float getDisplayNum() {
        return displayNum;
    }

    public void setDisplayNum(Float displayNum) {
        this.displayNum = displayNum;
    }

    public Integer getVer() {
        return ver;
    }

    public void setVer(Integer ver) {
        this.ver = ver;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public TmEvent() {
    }

    public TmEvent(String description, LocalDateTime date) {
        this.description = description;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}


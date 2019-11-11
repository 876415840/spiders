package com.example.demo.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * ShuangSeQiu实体
 * @author 系统生成
 */
public class ShuangSeQiu implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 表名
     */
    public static final String TABLE_NAME = "shuang_se_qiu";

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 期数
     */
    private String period;

    /**
     * 红1
     */
    private String red1;

    /**
     * 红2
     */
    private String red2;

    /**
     * 红3
     */
    private String red3;

    /**
     * 红4
     */
    private String red4;

    /**
     * 红5
     */
    private String red5;

    /**
     * 红6
     */
    private String red6;

    /**
     * 蓝球
     */
    private String blue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShuangSeQiu that = (ShuangSeQiu) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getRed1() {
        return red1;
    }

    public void setRed1(String red1) {
        this.red1 = red1;
    }

    public String getRed2() {
        return red2;
    }

    public void setRed2(String red2) {
        this.red2 = red2;
    }

    public String getRed3() {
        return red3;
    }

    public void setRed3(String red3) {
        this.red3 = red3;
    }

    public String getRed4() {
        return red4;
    }

    public void setRed4(String red4) {
        this.red4 = red4;
    }

    public String getRed5() {
        return red5;
    }

    public void setRed5(String red5) {
        this.red5 = red5;
    }

    public String getRed6() {
        return red6;
    }

    public void setRed6(String red6) {
        this.red6 = red6;
    }

    public String getBlue() {
        return blue;
    }

    public void setBlue(String blue) {
        this.blue = blue;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

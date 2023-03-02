package xyz.cdbxinhe.cat.backend.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
/**
 * 猫猫实体类
 * package xyz.cdbxinhe.cat.backend.entity
 * project backend
 * Created by @author CaoXin on date 2023/03/01
 */
@Data
@Entity
@RequiredArgsConstructor
public class Cat {
    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 猫猫名字
     */
    @NonNull
    private String name;
    /**
     * 猫猫出现地点
     */
    private String location;
    /**
     * 猫猫简介信息
     */
    private String description;
    /**
     * 海报路径
     */
    @NonNull
    private String poster;

    public Cat() {

    }
}

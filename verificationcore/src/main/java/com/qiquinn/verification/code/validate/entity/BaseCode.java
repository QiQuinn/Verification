package com.qiquinn.verification.code.validate.entity;

import java.time.LocalDateTime;

/**
 * @Author:QiQuinn
 * @Desicription: 验证码基础性
 * @Date:Created in 2019/7/31
 * @Modified By:
 */
public class BaseCode
{
    private String code;
    private LocalDateTime loseTime;

    public BaseCode(Integer time) {
        this.loseTime = LocalDateTime.now().plusSeconds(time);
    }

    public BaseCode(String code, Integer time) {
        this.code = code;
        this.loseTime = LocalDateTime.now().plusSeconds(time);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getLoseTime() {
        return loseTime;
    }

    public void setLoseTime(LocalDateTime loseTime) {
        this.loseTime = loseTime;
    }

    public boolean isExpried()
    {
        if (loseTime.isBefore(LocalDateTime.now()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

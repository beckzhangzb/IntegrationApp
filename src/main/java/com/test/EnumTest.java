package com.test;


public enum EnumTest{
    WALK(1, "慢走"),
    QUICK(2, "疾步"),
    RUN(3, "跑步");

    public static EnumTest getByCode(int code) {

        for(EnumTest et : EnumTest.values()) {
            if (et.getCode().intValue() == code) {
                System.out.println(et.getCode() + "_" + et.ordinal());
                return et;
            }
        }
        return null;
    }

    private Integer code;
    private String desc;

    EnumTest(Integer code, String desc) {
        System.out.println(code + "_" + desc);
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

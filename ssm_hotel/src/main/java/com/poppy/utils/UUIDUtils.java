package com.poppy.utils;

import java.util.UUID;

/**
 * @Author poppy
 * @Date 2021/11/4 14:12
 * @Version 1.0
 */
public class UUIDUtils { public static String randomUUID() { return UUID.randomUUID().toString().replace("-",""); } }
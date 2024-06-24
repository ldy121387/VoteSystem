package com.example.equipmentmanagent.Handle;
import com.example.equipmentmanagent.utils.HttpClientUtils;

import java.io.IOException;
import java.util.HashMap;

public class test {
    public static void main(String[] args) {

        test test = new test();
        //测试get请求
        test.testGet();
//        //测试String类型Post请求
//        test.testStringPost();
//        //测试Map类型Post请求
        test.testMapPost();
    }


    /**
     * 测试POST请求（String入参）
     */
    private void testStringPost() {

        String url = "http://127.0.0.1:5000/enc/hash_public_key";
        String str = "-----BEGIN PUBLIC KEY-----\\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDrcnX+krov3h68oVk0OwROtmB+\\nEJkdMCpUFawSofyIorun4nfcBrVtANpwNuRYKHG+FKPZ6Fu26g9c8qlueewlPBMe\\nY8MJ/mSRzdqG1V/Uo1YM7cLbkIwZPcd9XCIbP7bq1FVs1lhuOu8ZDYm9ZD/2NiZm\\nuP+dHk+7SwJ1vTHnKwIDAQAB\\n-----END PUBLIC KEY-----";
        try {
            String result = HttpClientUtils.post(url, str);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试POST请求（Map入参）
     */
    private void testMapPost() {
        String url = "http://110.41.56.14:5000/enc/hash_public_key";
        HashMap<String, String> map = new HashMap<>();
        map.put("public_key", "\\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDrcnX+krov3h68oVk0OwROtmB+\\nEJkdMCpUFawSofyIorun4nfcBrVtANpwNuRYKHG+FKPZ6Fu26g9c8qlueewlPBMe\\nY8MJ/mSRzdqG1V/Uo1YM7cLbkIwZPcd9XCIbP7bq1FVs1lhuOu8ZDYm9ZD/2NiZm\\nuP+dHk+7SwJ1vTHnKwIDAQAB\\n");
        try {
            String result = HttpClientUtils.post(url, map);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试GET请求
     */
    private void testGet() {
        String url = "http://110.41.56.14:5000/enc/keys";
        try {
            String result = HttpClientUtils.get(url);
            System.out.println("result");
            System.out.println(result);
            String privateKeyPEM = extractKey(result, "RSA PRIVATE KEY");
            String publicKeyPEM = extractKey(result, "PUBLIC KEY");

            // 打印私钥和公钥
            System.out.println("Private Key: \n" + privateKeyPEM);
            System.out.println("Public Key: \n" + publicKeyPEM);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String extractKey(String result, String keyType) {
        String beginMarker = "-----BEGIN " + keyType + "-----";
        String endMarker = "-----END " + keyType + "-----";

        int beginIndex = result.indexOf(beginMarker);
        if (beginIndex == -1) {
            return null; // 没有找到起始标记
        }
        int endIndex = result.indexOf(endMarker, beginIndex);
        if (endIndex == -1) {
            return null; // 没有找到结束标记
        }

        // 提取密钥内容，不包括起始和结束标记
        String keyContent = result.substring(beginIndex + beginMarker.length(), endIndex).trim();
        return keyContent.replaceAll("\\s+", ""); // 移除所有空格换行
    }
}

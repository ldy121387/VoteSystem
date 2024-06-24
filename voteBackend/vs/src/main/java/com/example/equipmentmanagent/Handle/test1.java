package com.example.equipmentmanagent.Handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class test1 {
    public static void main(String[] args) {
        String res = "[\"{\\\"difficulty\\\": 3, \\\"hash\\\": \\\"f443038fb35057021bda71d2c5ccf55fd80b649a2ff305b233df1a17d5e8bb7b\\\", \\\"height\\\": 1, \\\"merkle\\\": \\\"625b48660f2d06367c15673d01b3da41224b072039fffc1ec23901dda68837d1\\\", \\\"nonce\\\": 2765, \\\"number_of_votes\\\": 1, \\\"prevHash\\\": \\\"c0c945d68f11d1188b7e2716f0746e48affe645090515afb583d5535992cfffa\\\", \\\"timeStamp\\\": 1718717914.0115714, \\\"votecount\\\": {\\\"1\\\": 1}, \\\"votedata\\\": [{\\\"Candidate\\\": \\\"1\\\", \\\"TimeStamp\\\": \\\"1718717914011\\\", \\\"Voter Public Key\\\": \\\"\\\\\\\\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1jBivOYLSKhq0J6Va5qmn7kzC\\\\\\\\nH+HKbCwcsHqliBgwUR97906Wg+RmevbQKy0H0ylPGympZTRA+RDlCpjYEMDRwDw6\\\\\\\\njTF7NTlWag0o/jILc8JSCN/LhTx41db9mwPHPA3Gnn3fuVzQeSbNdBTEIZO0Sel+\\\\\\\\nOL/UPbYZff5BxBQ/DQIDAQAB\\\\\\\\n\\\"}]}\",\"{\\\"difficulty\\\": 3, \\\"hash\\\": \\\"c95aaa6ebcc0c82d8dfde9cc9f0a6c99aa189a846fc78951fdf57177fd68b13b\\\", \\\"height\\\": 2, \\\"merkle\\\": \\\"689dabff0d4fa7a3bdb4d6f67dd36eb201b4bf97b20a31c78f84af857c696523\\\", \\\"nonce\\\": 2467, \\\"number_of_votes\\\": 1, \\\"prevHash\\\": \\\"f443038fb35057021bda71d2c5ccf55fd80b649a2ff305b233df1a17d5e8bb7b\\\", \\\"timeStamp\\\": 1718717914.054416, \\\"votecount\\\": {\\\"1\\\": 1}, \\\"votedata\\\": [{\\\"Candidate\\\": \\\"1\\\", \\\"TimeStamp\\\": \\\"1718717914054\\\", \\\"Voter Public Key\\\": \\\"\\\\\\\\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1jBivOYLSKhq0J6Va5qmn7kzC\\\\\\\\nH+HKbCwcsHqliBgwUR97906Wg+RmevbQKy0H0ylPGympZTRA+RDlCpjYEMDRwDw6\\\\\\\\njTF7NTlWag0o/jILc8JSCN/LhTx41db9mwPHPA3Gnn3fuVzQeSbNdBTEIZO0Sel+\\\\\\\\nOL/UPbYZff5BxBQ/DQIDAQAB\\\\\\\\n\\\"}]}\",\"{\\\"difficulty\\\": 3, \\\"hash\\\": \\\"adba1a9407f0f04c8fee6ee89da67036dde0797191b655a3e725c85acb373a71\\\", \\\"height\\\": 3, \\\"merkle\\\": \\\"37d4b0e8660ee94ee0fcf3c1d0c30efd90ef10a1de3d82aa461c3445775c49b6\\\", \\\"nonce\\\": 4458, \\\"number_of_votes\\\": 4, \\\"prevHash\\\": \\\"c95aaa6ebcc0c82d8dfde9cc9f0a6c99aa189a846fc78951fdf57177fd68b13b\\\", \\\"timeStamp\\\": 1718717917.06251, \\\"votecount\\\": {\\\"1\\\": 1, \\\"2\\\": 3}, \\\"votedata\\\": [{\\\"Candidate\\\": \\\"2\\\", \\\"TimeStamp\\\": \\\"1718717914273\\\", \\\"Voter Public Key\\\": \\\"\\\\\\\\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1jBivOYLSKhq0J6Va5qmn7kzC\\\\\\\\nH+HKbCwcsHqliBgwUR97906Wg+RmevbQKy0H0ylPGympZTRA+RDlCpjYEMDRwDw6\\\\\\\\njTF7NTlWag0o/jILc8JSCN/LhTx41db9mwPHPA3Gnn3fuVzQeSbNdBTEIZO0Sel+\\\\\\\\nOL/UPbYZff5BxBQ/DQIDAQAB\\\\\\\\n\\\"}, {\\\"Candidate\\\": \\\"2\\\", \\\"TimeStamp\\\": \\\"1718717914317\\\", \\\"Voter Public Key\\\": \\\"\\\\\\\\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1jBivOYLSKhq0J6Va5qmn7kzC\\\\\\\\nH+HKbCwcsHqliBgwUR97906Wg+RmevbQKy0H0ylPGympZTRA+RDlCpjYEMDRwDw6\\\\\\\\njTF7NTlWag0o/jILc8JSCN/LhTx41db9mwPHPA3Gnn3fuVzQeSbNdBTEIZO0Sel+\\\\\\\\nOL/UPbYZff5BxBQ/DQIDAQAB\\\\\\\\n\\\"}, {\\\"Candidate\\\": \\\"1\\\", \\\"TimeStamp\\\": \\\"1718717916204\\\", \\\"Voter Public Key\\\": \\\"\\\\\\\\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1jBivOYLSKhq0J6Va5qmn7kzC\\\\\\\\nH+HKbCwcsHqliBgwUR97906Wg+RmevbQKy0H0ylPGympZTRA+RDlCpjYEMDRwDw6\\\\\\\\njTF7NTlWag0o/jILc8JSCN/LhTx41db9mwPHPA3Gnn3fuVzQeSbNdBTEIZO0Sel+\\\\\\\\nOL/UPbYZff5BxBQ/DQIDAQAB\\\\\\\\n\\\"}, {\\\"Candidate\\\": \\\"2\\\", \\\"TimeStamp\\\": \\\"1718717916422\\\", \\\"Voter Public Key\\\": \\\"\\\\\\\\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1jBivOYLSKhq0J6Va5qmn7kzC\\\\\\\\nH+HKbCwcsHqliBgwUR97906Wg+RmevbQKy0H0ylPGympZTRA+RDlCpjYEMDRwDw6\\\\\\\\njTF7NTlWag0o/jILc8JSCN/LhTx41db9mwPHPA3Gnn3fuVzQeSbNdBTEIZO0Sel+\\\\\\\\nOL/UPbYZff5BxBQ/DQIDAQAB\\\\\\\\n\\\"}]}\",\"{\\\"difficulty\\\": 3, \\\"hash\\\": \\\"a4e4a3b2d420c449aff895a02b72815dedfd3c96cef9028c067b9e92aa2d87a3\\\", \\\"height\\\": 4, \\\"merkle\\\": \\\"da37d749f2bea269d0689e937e697f5a6dfa6c89aecb3bc210b417533c7dfedf\\\", \\\"nonce\\\": 1224, \\\"number_of_votes\\\": 2, \\\"prevHash\\\": \\\"adba1a9407f0f04c8fee6ee89da67036dde0797191b655a3e725c85acb373a71\\\", \\\"timeStamp\\\": 1718717920.075997, \\\"votecount\\\": {\\\"1\\\": 1, \\\"2\\\": 1}, \\\"votedata\\\": [{\\\"Candidate\\\": \\\"1\\\", \\\"TimeStamp\\\": \\\"1718717918528\\\", \\\"Voter Public Key\\\": \\\"\\\\\\\\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1jBivOYLSKhq0J6Va5qmn7kzC\\\\\\\\nH+HKbCwcsHqliBgwUR97906Wg+RmevbQKy0H0ylPGympZTRA+RDlCpjYEMDRwDw6\\\\\\\\njTF7NTlWag0o/jILc8JSCN/LhTx41db9mwPHPA3Gnn3fuVzQeSbNdBTEIZO0Sel+\\\\\\\\nOL/UPbYZff5BxBQ/DQIDAQAB\\\\\\\\n\\\"}, {\\\"Candidate\\\": \\\"2\\\", \\\"TimeStamp\\\": \\\"1718717919234\\\", \\\"Voter Public Key\\\": \\\"\\\\\\\\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1jBivOYLSKhq0J6Va5qmn7kzC\\\\\\\\nH+HKbCwcsHqliBgwUR97906Wg+RmevbQKy0H0ylPGympZTRA+RDlCpjYEMDRwDw6\\\\\\\\njTF7NTlWag0o/jILc8JSCN/LhTx41db9mwPHPA3Gnn3fuVzQeSbNdBTEIZO0Sel+\\\\\\\\nOL/UPbYZff5BxBQ/DQIDAQAB\\\\\\\\n\\\"}]}\",\"{\\\"difficulty\\\": 3, \\\"hash\\\": \\\"44b85d1e1c6fc741eead40592faf209ba127e72138e69d39724ae9986938547a\\\", \\\"height\\\": 5, \\\"merkle\\\": \\\"335c90d4ee4c07d6a7147b80b7544b6cbe43f5e213a3d9bc840b907376e84cd2\\\", \\\"nonce\\\": 191, \\\"number_of_votes\\\": 1, \\\"prevHash\\\": \\\"a4e4a3b2d420c449aff895a02b72815dedfd3c96cef9028c067b9e92aa2d87a3\\\", \\\"timeStamp\\\": 1718717923.0806572, \\\"votecount\\\": {\\\"1\\\": 1}, \\\"votedata\\\": [{\\\"Candidate\\\": \\\"1\\\", \\\"TimeStamp\\\": \\\"1718717921455\\\", \\\"Voter Public Key\\\": \\\"\\\\\\\\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1jBivOYLSKhq0J6Va5qmn7kzC\\\\\\\\nH+HKbCwcsHqliBgwUR97906Wg+RmevbQKy0H0ylPGympZTRA+RDlCpjYEMDRwDw6\\\\\\\\njTF7NTlWag0o/jILc8JSCN/LhTx41db9mwPHPA3Gnn3fuVzQeSbNdBTEIZO0Sel+\\\\\\\\nOL/UPbYZff5BxBQ/DQIDAQAB\\\\\\\\n\\\"}]}\"]";
        JSONArray array = JSONArray.parseArray(res);
        if (array != null && array.size() > 0) {
            // 初始化一个空的JSONObject用于存放累加结果
            JSONObject voteCount = new JSONObject();
            voteCount = new JSONObject();

            // 遍历数组中的每个元素
            for (int i = 0; i < array.size(); i++) {
                String element = array.getString(i);
                JSONObject elementObject = JSON.parseObject(element);

                // 获取当前元素的votecount对象
                JSONObject currentVoteCount = elementObject.getJSONObject("votecount");

                // 累加votecount到总的voteCount对象中
                for (String key : currentVoteCount.keySet()) {
                    int currentValue = currentVoteCount.getIntValue(key);
                    int currentTotal = voteCount.getIntValue(key);
                    voteCount.put(key, currentTotal + currentValue);
                }
            }

            System.out.println(voteCount);

        }
    }
}

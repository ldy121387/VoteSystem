package com.example.equipmentmanagent.Handle;

import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TestHandeler {

    public static void main(String[] args) {

        //获取最长时间
        String startDateStr = "2024-06-10";
        String endDateStr = "2024-06-20";

        // 将字符串解析为 LocalDate 对象
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        // 计算持续天数
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        // 输出持续天数
        System.out.println("持续天数: " + daysBetween + " 天");

//
//        PythonInterpreter interpreter = new PythonInterpreter();
//        interpreter.execfile("D:\\VoteSystem\\VoteSystem\\voteBackend\\vs\\src\\main\\java\\com\\example\\equipmentmanagent\\py\\enc.py");
//
//        PyFunction function = interpreter.get("rsakeys", PyFunction.class);
//
//        PyObject pyObject = function.__call__();
//
//        System.out.println(String.format("result: %s", pyObject));;

//        String pyPath = "D:\\VoteSystem\\VoteSystem\\voteBackend\\vs\\src\\main\\java\\com\\example\\equipmentmanagent\\py\\enc.py"; //python文件路径
//        String[] args1 = new String[] { "python", pyPath};  //设定命令行
//        try {
//            Process proc = Runtime.getRuntime().exec(args1);  //执行py文件
//            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//            String line = null;
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//            }
//            in.close();
//            proc.waitFor();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//            try {
//                // 定义要调用的Python脚本和方法
//                String scriptPath = "D:\\VoteSystem\\VoteSystem\\voteBackend\\vs\\src\\main\\java\\com\\example\\equipmentmanagent\\py\\enc.py";
//                String methodName = "rsakeys";
//
//                // 构建调用命令
//                String[] command = {"python", scriptPath, "-c",
//                        "from " + scriptPath.substring(0, scriptPath.lastIndexOf(".")).replace("/", ".")
//                                + " import " + methodName + "; " + methodName + "()"};
//
//                // 在运行时调用脚本方法
//                Process process = Runtime.getRuntime().exec(command);
//                process.waitFor();
//                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
//                String line = null;
//                while ((line = in.readLine()) != null) {
//                    System.out.println(line);
//                }
//                in.close();
//                process.waitFor();
//
//            } catch (IOException | InterruptedException e) {
//                e.printStackTrace();
//            }

            // TODO Auto-generated method stub
//            Process proc;
//            try {
//                proc = Runtime.getRuntime().exec("python D:\\VoteSystem\\VoteSystem\\voteBackend\\vs\\src\\main\\java\\com\\example\\equipmentmanagent\\py\\enc.py\n");// 执行py文件
//                //用输入输出流来截取结果
//                BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//                String line = null;
//                StringBuilder output1 = new StringBuilder();
//                StringBuilder output2 = new StringBuilder();
//                StringBuilder output3 = new StringBuilder();
//                StringBuilder publicKey = new StringBuilder();
//                StringBuilder privateKey = new StringBuilder();
////                while ((line = in.readLine()) != null) {
////                    System.out.println(line);
////                    if (line.startsWith("t")) {
////                        output1.append(line).append("\n");
////                        System.out.println(output1.toString());
////                    } else if (line.startsWith("s")) {
////                        output2.append(line.substring(26)).append("\n");
////                        System.out.println(output2.toString());
////                    } else if (line.startsWith("ss")) {
////                        output3.append(line.substring(31)).append("\n");
////                        System.out.println(output3.toString());
////                    }
////                }
//                boolean isPublicKey = false;
//                boolean isPrivateKey = false;
//                while ((line = in.readLine()) != null) {
////                    System.out.println(line);
//                    if (line.startsWith("-----BEGIN PUBLIC KEY-----")) {
//                        isPublicKey = true;
//                    } else if (line.startsWith("-----BEGIN RSA PRIVATE KEY-----")) {
//                        isPublicKey = false;
//                        isPrivateKey = true;
//                    } else if (line.startsWith("-----END RSA PRIVATE KEY-----")) {
//                        isPrivateKey = false;
//                    } else if (line.startsWith("-----END PUBLIC KEY-----")) {
//                        isPublicKey = false;
//                    } else {
//                        if (isPublicKey) {
//                            publicKey.append(line);
//                        } else if (isPrivateKey) {
//                            privateKey.append(line);
//                        }
//                    }
//                }
//                System.out.println(publicKey);
//                System.out.println(privateKey);
//                in.close();
//                proc.waitFor();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }



    }

}


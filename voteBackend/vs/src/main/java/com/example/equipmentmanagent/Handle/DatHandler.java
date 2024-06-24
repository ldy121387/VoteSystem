package com.example.equipmentmanagent.Handle;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.io.*;

public class DatHandler {
    public static void main(String[] args) {
        // 创建Python解释器对象
        PythonInterpreter interpreter = new PythonInterpreter();

        // 加载pickle模块
        interpreter.exec("import pickle");

//        // 读取pickle序列化的dat文件
//        interpreter.exec("with open('D:\\VoteSystem\\VoteSystem\\voteBackend\\vs\\src\\main\\java\\com\\example\\equipmentmanagent\\Handle\\blockchain.dat', 'rb') as file:\n"
//                + "    data = pickle.load(file)");
        interpreter.exec("with open(\"D:/VoteSystem/VoteSystem/voteBackend/vs/src/main/java/com/example/equipmentmanagent/Handle/block-1.dat\", 'rb') as file:\n"
                + "    data = pickle.load(file)");

        // 获取读取的数据
        PyObject pyData = interpreter.get("data");
        // 在Java中将PyObject转换为相应的数据类型
        // 例如：String value = pyData.toString();

        // 打印读取的数据
        System.out.println(pyData);
    }

//    public static void main(String[] args) {
//        String fileName = "D:\\VoteSystem\\VoteSystem\\voteBackend\\vs\\src\\main\\java\\com\\example\\equipmentmanagent\\Handle\\blockchain.dat";
//
//        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(fileName))) {
//            while (inputStream.available() > 0) {
//                int value = inputStream.readInt();
//                System.out.println(value);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }



//    public static void displayDat() {
//        try (FileInputStream fileInputStream = new FileInputStream("D:\\VoteSystem\\VoteSystem\\voteBackend\\vs\\src\\main\\java\\com\\example\\equipmentmanagent\\Handle\\blockchain.dat");
//             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
//
//            while (true) {
//                try {
//                    Object data = new ObjectInputStream(bufferedInputStream).readObject();
//
////                    // --print all data of a block
////                    System.out.println("Block Height: " + data.height);
////                    System.out.println("Data in block: " + data.votedata);
////                    System.out.println("Total in block: " + data.votecount);
////                    System.out.println("Number of votes: " + data.number_of_votes);
////                    System.out.println("Merkle root: " + data.merkle);
////                    System.out.println("Difficulty: " + data.DIFFICULTY);
////                    System.out.println("Time stamp: " + data.timeStamp);
////                    System.out.println("Previous hash: " + data.prevHash);
////                    System.out.println("Block Hash: " + data.hash);
////                    System.out.println("Nonce: " + data.nonce + "\n\t\t|\n\t\t|");
//                } catch (EOFException e) {
//                    break;  // End of file reached
//                }
//            }
//
//        } catch (FileNotFoundException e) {
//            System.out.println("\n.\n.\n.\n<<<File not found!!>>>");
//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Error reading file: " + e.getMessage());
//        }
//    }

//    public static void main(String[] args) {
//        displayDat();
//    }

}

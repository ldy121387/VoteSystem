package com.example.equipmentmanagent.Manage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.equipmentmanagent.DTO.Vote;
import com.example.equipmentmanagent.DTO.voteUser;
import com.example.equipmentmanagent.Mapper.VoteMapper;
import com.example.equipmentmanagent.Mapper.voteUserMapper;
import com.example.equipmentmanagent.Service.VoteService;
import com.example.equipmentmanagent.Service.VoteUserService;
import com.example.equipmentmanagent.utils.HttpClientUtils;
import com.example.equipmentmanagent.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.example.equipmentmanagent.Mapper.vDetailMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
@AllArgsConstructor
public class VoteManageImpl implements VoteManage{

    private final VoteMapper voteMapper;
    private final VoteService voteService;
    private final vDetailMapper vDetailMapper;
    private final voteUserMapper voteUserMapper;
    private final VoteUserService voteUserService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Override
    public R add(Vote vote) {
        //处理时间
        String startDateStr = null;
        String endDateStr = null;

        String start = vote.getStartTime();
        String end = vote.getEndTime();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = inputFormat.parse(start);
            startDateStr = outputFormat.format(startDate);

            Date endDate = inputFormat.parse(end);
            endDateStr = outputFormat.format(endDate);

            System.out.println(startDateStr);
            System.out.println(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //获取hash
        String pk = String.valueOf(stringRedisTemplate.opsForValue().get("pk"));
        String userId = String.valueOf(stringRedisTemplate.opsForValue().get("userId"));
        String url = "http://127.0.0.1:5000/enc/hash_public_key";
//        String url = "http://110.41.56.14:5000/enc/hash_public_key";
        HashMap<String, String> map = new HashMap<>();
        map.put("public_key", pk);
        String pkHash = null;
        try {
            String json = HttpClientUtils.post(url, map);

            JSONObject jsonObject = JSON.parseObject(json);

            // 获取 msg 字段的值
            pkHash =  jsonObject.getString("details");


            System.out.println("pkHash:" + pkHash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String hash = null;
        if(pkHash != null){
            hash = pkHash.replaceAll("\\n", "").replaceAll("\"",""); // 去除换行符
        } else {
            System.out.println("pkHash是空");
        }

        Integer maxNumber = vote.getNumber() + 1;
        vote.setPk(hash);
        if (voteMapper.insert(vote) == 1) {
            //创建时 需要生成n个hash 用于绑定

            //调取接口获取hash

            String urlManyHash = "http://127.0.0.1:5000/enc/many_hash";
//            String urlManyHash = "http://110.41.56.14:5000/enc/many_hash";
            HashMap<String, String> mapHash = new HashMap<>();
            mapHash.put("blockchain_id", String.valueOf(vote.getId()));
            mapHash.put("public_key", String.valueOf(vote.getPk()));
            mapHash.put("max_votes", String.valueOf(maxNumber));
            try {
                String json = HttpClientUtils.post(urlManyHash, mapHash);
                JSONObject jsonObject = JSON.parseObject(json);
                JSONArray array = jsonObject.getJSONArray("details");

                List<String> hashList = array.toJavaList(String.class);

                //将这些hash插入voteUser voteId hashValue
//                for (String hashValue : hashList){
////                    System.out.println(hashValue);
//                    voteUser voteUser = new voteUser();
//                    voteUser.setVoteId(vote.getId());
//                    voteUser.setHashValue(hashValue);
//                    voteUserMapper.insert(voteUser);
//                }

                for (int i = 0; i < hashList.size(); i++) {
                    String hashValue = hashList.get(i);
                    voteUser voteUser = new voteUser();

                    // 在第一个位置插入userId
                    if (i == 0) {
                        voteUser.setUserId(Integer.valueOf(userId));
                    }

                    voteUser.setVoteId(vote.getId());
                    voteUser.setHashValue(hashValue);
                    voteUserMapper.insert(voteUser);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }



            //创建时 需要生成csv文件
            //获取最大票数
            int maxNumber2 = vote.getNumber();

            //获取最长时间
//            String startDateStr = "2024-06-10";
//            String endDateStr = "2024-06-20";


            // 将字符串解析为 LocalDate 对象
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(startDateStr, formatter);
            LocalDate endDate = LocalDate.parse(endDateStr, formatter);

            // 计算持续天数
            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);



            String fileName = vote.getId() + "-" + maxNumber2 + "-" + daysBetween + "-" + hash +".csv";
//            String directoryPath = "D:\\VoteSystem\\VoteSystem\\voteBackend"; // CSV文件所在的目录路径
            String directoryPath = "/usr/vbb/records/vote_pool"; // CSV文件所在的目录路径
            System.out.println(fileName);

            try {
                File directory = new File(directoryPath);
                if (!directory.exists()) { // 检查目录是否存在，如果不存在则创建
                    directory.mkdirs();
                }

                File file = new File(directory, fileName); // 创建文件对象
                if (file.createNewFile()) { // 尝试创建文件
                    System.out.println("File created: " + file.getAbsolutePath()); // 文件创建成功
                } else {
                    System.out.println("File already exists."); // 文件已存在
                }
            } catch (IOException e) {
                System.out.println("An error occurred."); // 发生错误
                e.printStackTrace();
            }



            return R.ok("新增投票记录成功");
        }
        return R.failed("新增投票记录失败");
    }

    @Override
    public R delete(Integer id) {
        //统计票数
        Integer voteNumber = 0;
        Vote vote = voteMapper.selectById(id);
        int maxNumber = vote.getNumber();
        boolean delete = true;
        String urlBlock = "http://127.0.0.1:5000/data/load_blockchain";
//        String urlBlock = "http://110.41.56.14:5000/data/load_blockchain";
        HashMap<String, String> mapHash = new HashMap<>();
        mapHash.put("blockchain_id", String.valueOf(id));
        try {
            String json = HttpClientUtils.post(urlBlock, mapHash);
            JSONObject jsonObject = JSON.parseObject(json);
            JSONArray array = jsonObject.getJSONArray("details");

            if (array != null && array.size() > 0) {
                // 遍历数组中的每个元素
                for (int i = 1; i < array.size(); i++) {
                    String element = array.getString(i);
                    JSONObject elementObject = JSON.parseObject(element);

                    // 获取当前元素的votecount对象
                    JSONObject currentVoteCount = elementObject.getJSONObject("votecount");

                    // 累加votecount到总的voteCount对象中
                    for (String key : currentVoteCount.keySet()) {
                        int currentValue = currentVoteCount.getIntValue(key);
                        voteNumber += currentValue;
                    }
                }
            } else {
                // 数组为空或者长度为0，需要进行相应的处理
                System.out.println("array为空");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //删除csv
        System.out.println("totalNumber:" + voteNumber);
        if (voteNumber < maxNumber / 4) {
            System.out.println("voteNumber小于maxNumber的四分之一");
            //删除csv
            String urlCsv = "http://127.0.0.1:5000/protocol/delete_csv";

            try {
                String json = HttpClientUtils.post(urlCsv, mapHash);
                JSONObject jsonObject = JSON.parseObject(json);
                String res =  jsonObject.getString("details");

                if("Not_empty".equals(res)){
                    //睡眠后 再次尝试
                    Thread.sleep(3000);
                    try {
                        String json2 = HttpClientUtils.post(urlCsv, mapHash);
                        JSONObject jsonObject2 = JSON.parseObject(json2);
                        String res2 =  jsonObject2.getString("details");
                        if("Not_empty".equals(res2)){
                            R.failed("Failed to delete csv file");
                            delete = false;
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            //删除chain
            String urlChain = "http://127.0.0.1:5000/protocol/delete_chain";
            if(delete){
                try {
                    String json = HttpClientUtils.post(urlChain, mapHash);
                    String msg = getMsgFromJson(json);
                    if("Error".equals(msg)){
                        delete = false;
                        return R.failed("Failed to delete blockchain file");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            return  R.failed("The total number of votes has reached one quarter of the maximum number of votes and deletion is not allowed");
        }

        if(delete){
            if (voteMapper.deleteById(id) == 1) {
                return R.ok("Delete Voting Record Successfully");
            } else {
                return R.failed("Failed to delete voting record");
            }
        } else {
            return R.failed("Failed to delete voting record");
        }

    }

    @Override
    public R update(Vote vote) {
        if (voteMapper.updateById(vote) == 1) {
            return R.ok("更新投票记录成功");
        }
        return R.failed("更新投票记录失败");
    }

    @Override
    public R getById(Integer id) {
        return null;
    }

    @Override
    public R getVoteByPage(Integer current, Integer page,String voteName) {

        //根据userId 获取voteId
        Integer userId = Integer.valueOf(stringRedisTemplate.opsForValue().get("userId"));
        List<Integer> voteIdList = voteUserService.getVoteIdByUserId(userId);
        //根据voteId分页查询
        IPage<Vote> voteIPage = voteService.getVotePageByVoteId(current,page,voteName,voteIdList);
        if (voteIPage.getSize()>0){
            return R.success(voteIPage);
        }
        else return R.failed("获取投票失败");
    }

    @Override
    public R getVoteByTime() {
        List<Vote> votes = voteService.getVoteByTime();
        if (!votes.isEmpty()){
            return R.success(votes);
        }
        else return R.failed("当前无未截止投票");
    }

    @Override
    public R verify(Integer voteId) {
        String syn_blocks = "http://127.0.0.1:5000/protocol/syn_blocks";
        String verify_blocks = "http://127.0.0.1:5000/protocol/verify_blocks";
        String verify_merkles = "http://127.0.0.1:5000/protocol/verify_merkles";
        HashMap<String, String> mapHash = new HashMap<>();
        mapHash.put("blockchain_id", String.valueOf(voteId));
        String res_syn_blocks = null;
        String res_verify_blocks = null;
        String res_verify_merkles = null;

        try {
            res_syn_blocks= getMsgFromJson(HttpClientUtils.post(syn_blocks, mapHash));
            res_verify_blocks = getMsgFromJson(HttpClientUtils.post(verify_blocks, mapHash));
            res_verify_merkles = getMsgFromJson(HttpClientUtils.post(verify_merkles, mapHash));


            System.out.println(res_syn_blocks);
            System.out.println(res_verify_blocks);
            System.out.println(res_verify_merkles);

        } catch (Exception e) {
            e.printStackTrace();
        }

        String msg = null;
        String s = "Success";
        if(s.equals(res_syn_blocks)){
            if(s.equals(res_verify_blocks)){
                if(s.equals(res_verify_merkles)){
                    msg = "Success: All verification checks passed successfully. <br> All the data is secure and authentic.";
                } else {
                    msg = "Error: Vote data tampering detected! <br> Please contact the top administrator.";
                }
            } else {
                msg = "Error: Data tampering detected in a block! <br> Please contact the top administrator.";
            }
        } else {
            msg = "Error: Block connection issue detected! <br> Please contact the top administrator.";
        }
        return R.success(msg);
    }

    @Override
    public R getHash(Integer voteId) {
        List<voteUser> hashList = new ArrayList<>();
        hashList = voteService.getHashByVoteId(voteId);

        if(hashList != null && hashList.size() != 0){
            return R.success(hashList);
        } else {
            return R.failed("获取hash列表失败");
        }

    }

    @Override
    public R bind(String hashValue) {
        Integer userId = Integer.valueOf(stringRedisTemplate.opsForValue().get("userId"));
        boolean res = voteUserService.bind(hashValue,userId);

        if(res){
            return R.ok("Bind successfully");
        } else {
            return R.failed("Binding Failure");
        }

    }

    @Override
    public R getVoteByPageAdmin(Integer current, Integer size, String voteName) {

        //根据voteId分页查询
        IPage<Vote> voteIPage = voteService.getVoteByPage(current,size,voteName);
        if (voteIPage.getSize()>0){
            return R.success(voteIPage);
        }
        else return R.failed("获取投票失败");
    }

    @Override
    public R getVoteByTimeAndUser() {
        //根据userId 获取voteId
        Integer userId = Integer.valueOf(stringRedisTemplate.opsForValue().get("userId"));
        List<Integer> voteIdList = voteUserService.getVoteIdByUserId(userId);

        List<Vote> votes = voteService.getVoteByTimeAndUser(voteIdList);
        if (!votes.isEmpty()){
            return R.success(votes);
        }
        else return R.failed("当前无未截止投票");
    }

    public static String getMsgFromJson(String jsonString) {
        // 解析 JSON 字符串
        JSONObject jsonObject = JSON.parseObject(jsonString);

        // 获取 msg 字段的值
        return jsonObject.getString("msg");
    }
}

package com.example.equipmentmanagent.Manage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.equipmentmanagent.DTO.Vote;
import com.example.equipmentmanagent.DTO.vDetail;
import com.example.equipmentmanagent.Mapper.VoteMapper;
import com.example.equipmentmanagent.Mapper.vDetailMapper;
import com.example.equipmentmanagent.Service.vDetailService;
import com.example.equipmentmanagent.utils.HttpClientUtils;
import com.example.equipmentmanagent.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Component
@AllArgsConstructor
public class vDetailManageImpl implements vDetailManage{

    private final vDetailService vDetailService;
    private final vDetailMapper vDetailMapper;
    private final VoteMapper voteMapper;

//    private final RedisTemplate<String,String> redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public R add(vDetail vDetail) {
        if (vDetailMapper.insert(vDetail) == 1) {
            return R.ok("新增投票记录成功");
        }
        return R.failed("新增投票记录失败");
    }

    @Override
    public R delete(Integer id) {
        if (vDetailMapper.deleteById(id) == 1) {
            return R.ok("删除投票记录成功");
        }
        return R.failed("删除投票记录失败");
    }

    @Override
    public R update(vDetail vDetail) {
        if (vDetailMapper.updateById(vDetail) == 1) {
            return R.ok("更新投票记录成功");
        }
        return R.failed("更新投票记录失败");
    }

    @Override
    public R getById(Integer id) {
        return null;
    }

    @Override
    public R getVoteByPage(Integer current, Integer page,String voteName,Integer voteId) {
        IPage<vDetail> voteIPage = vDetailService.getVoteDetailByPage(current,page,voteName,voteId);
        if (voteIPage.getSize()>0){
            return R.success(voteIPage);
        }
        else return R.failed("获取投票失败");
    }

    @Override
    public R voteById(Integer voteId) {
        vDetail vDetailOne = vDetailMapper.selectById(voteId);
        int number = vDetailOne.getNumber();
        number++;
        String nickName = String.valueOf(stringRedisTemplate.opsForValue().get("nickName"));
        String pk = String.valueOf(stringRedisTemplate.opsForValue().get("pk"));
        vDetailOne.setVoteName(nickName);
        vDetailOne.setNumber(number);




        if (vDetailMapper.updateById(vDetailOne) == 1) {

            String id = String.valueOf(vDetailOne.getVoteId()); // 指定的ID
//            String directoryPath = "D:\\VoteSystem\\VoteSystem\\voteBackend"; // CSV文件所在的目录路径
            String directoryPath = "/usr/vbb/records/vote_pool"; // CSV文件所在的目录路径
            File directory = new File(directoryPath);

            // 获取目录中以指定ID开头的CSV文件
            File[] csvFiles = directory.listFiles((dir, name) -> name.startsWith(id) && name.endsWith(".csv"));

            if (csvFiles != null && csvFiles.length == 1) {
                File file = csvFiles[0];
                try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(pk);
                    sb.append(',');
                    sb.append(vDetailOne.getName());
                    sb.append(',');
                    sb.append(System.currentTimeMillis());
                    sb.append('\n');
                    writer.write(sb.toString());

                    System.out.println("Data has been written to " + file.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (csvFiles == null || csvFiles.length == 0) {
                System.out.println("No CSV files found with the specified ID.");
            } else {
                System.out.println("Multiple CSV files found with the specified ID. Please ensure there is only one such file.");
            }

//            String fileName = "csv_vote" + vDetailOne.getVoteId()+".csv";
//
//
//            try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
//
//                StringBuilder sb = new StringBuilder();
//                sb.append(pk);
//                sb.append(',');
//                sb.append(vDetailOne.getName());
//                sb.append(',');
//                sb.append(System.currentTimeMillis());
//                sb.append('\n');
//                writer.write(sb.toString());
//
//                System.out.println("Data has been written to " + fileName);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            return R.ok("投票成功");
        }
        return R.failed("更新失败");

    }

    @Override
    public R getVoteByBlock(Integer voteId) {

//        String pk = String.valueOf(stringRedisTemplate.opsForValue().get("pk"));

        //获取pk
//        Vote vote = voteMapper.selectById(voteId);
//        String pkHash = vote.getPk();
//        String id = String.valueOf(vote.getId());


//        String url = "http://127.0.0.1:5000/enc/hash_public_key";
//        HashMap<String, String> map = new HashMap<>();
//        map.put("public_key", pk);
//        String pkHash = null;
//        try {
//            pkHash = HttpClientUtils.post(url, map);
//            System.out.println(pkHash);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String hash = pkHash.replaceAll("\\n", "").replaceAll("\"",""); // 去除换行符


        String urlBlock = "http://127.0.0.1:5000/data/load_blockchain";
//        String urlBlock = "http://110.41.56.14:5000/data/load_blockchain";
        HashMap<String, String> mapHash = new HashMap<>();
        mapHash.put("blockchain_id", String.valueOf(voteId));
//        mapHash.put("blockchain_name", "43aaee7dc8fc47201fb4ec60e53ff3ced373fba0fad0f977c6919e84785f0c");
        JSONObject voteCount = new JSONObject();
        try {
            String json = HttpClientUtils.post(urlBlock, mapHash);
            String res = null;
            JSONObject jsonObject = JSON.parseObject(json);
            JSONArray array = jsonObject.getJSONArray("details");

            System.out.println(res);
//            JSONArray array = JSONArray.parseArray(res);
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
                        int currentTotal = voteCount.getIntValue(key);
                        voteCount.put(key, currentTotal + currentValue);
                    }
                }
                // 打印votecount对象
                System.out.println(voteCount.toJSONString());
            } else {
                // 数组为空或者长度为0，需要进行相应的处理
                System.out.println("array为空");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return R.success(voteCount);
    }

    @Override
    public R getVoteDetailByBlock(Integer voteId) {
        String urlBlock = "http://127.0.0.1:5000/data/load_blockchain";
//        String urlBlock = "http://110.41.56.14:5000/data/load_blockchain";
        HashMap<String, String> mapHash = new HashMap<>();
        mapHash.put("blockchain_id", String.valueOf(voteId));
        JSONObject voteDetailCount = new JSONObject();
        JSONArray voteDetails = new JSONArray();
        try {
            String json = HttpClientUtils.post(urlBlock, mapHash);
            JSONObject jsonObject = JSON.parseObject(json);
            JSONArray array = jsonObject.getJSONArray("details");

            if (array != null && array.size() > 0) {
                // 遍历数组中的每个元素
                for (int i = 1; i < array.size(); i++) {
                    String element = array.getString(i);
                    JSONObject elementObject = JSON.parseObject(element);

                    // 获取当前元素的votedata数组
                    JSONArray currentVoteData = elementObject.getJSONArray("votedata");

                    // 遍历votedata数组中的每个投票信息
                    for (int j = 0; j < currentVoteData.size(); j++) {
                        JSONObject voteInfo = currentVoteData.getJSONObject(j);

                        // 提取候选项、时间戳和投票人的公钥
                        String candidate = voteInfo.getString("Candidate");
                        String timeStamp = voteInfo.getString("TimeStamp");
                        String voterPublicKey = voteInfo.getString("Voter Public Key").replace("\\n", "");

                        // 将字符串形式的时间戳转换为 long 类型
                        long stamp = Long.parseLong(timeStamp);
                        Date date = new Date(stamp);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String TimeStamp = sdf.format(date);

                        // 创建新的JSONObject并加入voteDetails数组
                        JSONObject voteDetail = new JSONObject();
                        voteDetail.put("Candidate", candidate);
                        voteDetail.put("TimeStamp", TimeStamp);
                        voteDetail.put("VoterPublicKey", voterPublicKey);
                        voteDetails.add(voteDetail);
                    }

                }

                // 打印voteDetails数组
                System.out.println(voteDetails.toJSONString());

            } else {
                // 数组为空或者长度为0，需要进行相应的处理
                System.out.println("array为空");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return R.success(voteDetails);
    }
}

package ideatc.jinxiang.utils;

import android.util.Log;

import com.loopj.android.http.RequestParams;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @author ccb
 *         create at 2015/9/11 10:01
 */
public class ParamsUtil {
    //	 * 获取参数
//	 *
//	 * @return
//	 */
    public static RequestParams getSignParams() {
        RequestParams params = new RequestParams();
        String timestamp = new Date().getTime() + "";
        String nonce = getRandomInt();// 随机数

        String signature = getSignature(timestamp, nonce);// 加密的

        Log.e("加密的微信签名", signature);
        params.add("timestamp", timestamp);
        params.add("nonce", nonce);

        params.add("signature", signature);
        return params;
    }

    /**
     * 获取随机字符串
     *
     * @return
     */
    private static String getRandomInt() {
        String[] ints = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        int c = ints.length;
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            sb.append(ints[random.nextInt(c)]);
        }
        return sb.toString();
    }

    /**
     * 随机字符串
     *
     * @return
     */
    private static String getReandomStr() {
        String[] ints = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"};
        int c = ints.length;
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(ints[random.nextInt(c)]);
        }
        return sb.toString();
    }

    /**
     * 校验签名
     *
     * @param timestamp
     * 时间戳
     * @param nonce
     * 随机数
     * @return
     */
    public final static String KEY = "ideatc";

    public static String getSignature(String timestamp, String nonce) {
        // 对token、timestamp和nonce按字典排序
        String[] paramArr = new String[]{KEY, timestamp, nonce};
        Arrays.sort(paramArr);

        // 将排序后的结果拼接成一个字符串
        String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);

        String ciphertext = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // 对接后的字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            ciphertext = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // 将sha1加密后的字符串与signature进行对比
        return ciphertext;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }
}

package cool.delete.sms.utils;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * 获取随机数
 * 
 * @author qianyi
 *
 */
public class RandomUtil {

	private static final Random random = new Random();


	private static final DecimalFormat SMS_CODE = new DecimalFormat("000000");


	public static String getSixBitRandom() {
		return SMS_CODE.format(random.nextInt(1000000));
	}
}

package dislab.rfidaction.utils;

import java.util.List;

/**
 * 对rssi进行编码
 *
 */
public class TagRssiEncoder {

	private double[] preRssi;
	private List<String> tagIds;

	public TagRssiEncoder(List<String> tagIds) {
		this.tagIds = tagIds;
		this.preRssi = new double[tagIds.size()];
	}

	/**
	 * 对rssi进行编码，大于阈值为1,小于负的阈值为-1，否则为0
	 * @param tagRssi
	 * 原始rssi
	 * @param refRssi
	 * 基准rssi
	 * @param threhold
	 * 阈值
	 * @return
	 */
	public int[] encode(double[] tagRssi, double[] refRssi, int threhold) {
		// TODO Auto-generated method stub
		int[] tagUpDown = new int[tagIds.size()];
		for (int i = 0; i < tagRssi.length; i++) {
			double delta = tagRssi[i] - refRssi[i];
			preRssi[i] = tagRssi[i];
			if (delta > threhold) {
				tagUpDown[i] = 1;
			} else if (delta < -threhold) {
				tagUpDown[i] = -1;
			}
		}
		return tagUpDown;
	}

	public int[] preBasedEncode(double[] tagRssi, int threhold) {
		int[] encodeArray = encode(tagRssi, preRssi, threhold);
		return encodeArray;
	}

}

import java.util.HashMap;
import java.util.Map;

public class Format {
	private int MaxLength, Value;
	public static final int DefaultLength = 0x14, DefaultValue = 0x4;
	private Map<String, String> string;
	private static MyData myData;
	private String Padding, Vertical, Across;
	private static final String DefaultPadding = " ", DefaultVertical = "|", DefaultAcross = "-";

	/**
	 * �ı���ʽ��
	 * 
	 * @param string Ҫ��ʽ�����ı�Map����
	 */
	public Format(Map<String, String> string) {
		this(string, DefaultLength, DefaultValue);
	}

	/**
	 * �ı���ʽ��
	 * 
	 * @param string    Ҫ��ʽ�����ı�Map����
	 * @param MaxLength �ı������򳤶�
	 *                  (<b>ע��</b>�Ⲣ��һ�����ı�����ʵ���ȣ���ʵ���Ȼ�����ı���ʵ�ʳ��ȸı䣬�������ı�����С�ڸ�ֵ����Զ���䳤������ֵ)
	 */
	public Format(Map<String, String> string, int MaxLength) {
		this(string, MaxLength, DefaultValue);
	}

	/**
	 * �ı���ʽ��
	 * 
	 * @param string    Ҫ��ʽ�����ı�Map����
	 * @param MaxLength �ı������򳤶�
	 *                  (<b>ע��</b>�Ⲣ��һ�����ı�����ʵ���ȣ���ʵ���Ȼ�����ı���ʵ�ʳ��ȸı䣬�������ı�����С�ڸ�ֵ����Զ���䳤������ֵ)
	 * @param Value     ǰ���ı���϶��Сֵ
	 */
	public Format(Map<String, String> string, int MaxLength, int Value) {
		this(string, MaxLength, Value, DefaultPadding);
	}

	/**
	 * �ı���ʽ��
	 * 
	 * @param string    Ҫ��ʽ�����ı�Map����
	 * @param MaxLength �ı������򳤶�
	 *                  (<b>ע��</b>�Ⲣ��һ�����ı�����ʵ���ȣ���ʵ���Ȼ�����ı���ʵ�ʳ��ȸı䣬�������ı�����С�ڸ�ֵ����Զ���䳤������ֵ)
	 * @param Value     ǰ���ı���϶��Сֵ
	 * @param Padding   �ı���ʽ��������ַ�
	 */
	public Format(Map<String, String> string, int MaxLength, int Value, String Padding) {
		this.MaxLength = MaxLength;
		this.string = string;
		this.Value = Value;
		this.Padding = Padding;
		this.Across = DefaultAcross;
		this.Vertical = DefaultVertical;
	}

	/**
	 * ����Ҫ��Χ�ı��ַ�������Χ��ʾ�����ı�
	 * 
	 * @param vertical
	 */
	public Format setAcross(String across) {
		Across = across;
		return this;
	}

	/**
	 * ���ø�ʽ�������ַ���
	 * 
	 * @param padding
	 */
	public Format setPadding(String padding) {
		Padding = padding;
		return this;
	}

	/**
	 * ����Ҫ��Χ�ı��ַ�������Χ��ʾ����ֱ�ı�
	 * 
	 * @param vertical
	 */
	public Format setVertical(String vertical) {
		Vertical = vertical;
		return this;
	}

	/**
	 * ������Ҫ��ʽ�����ı�Mydata����
	 * 
	 * @param string
	 */
	public Format setString(MyData string) {
		this.string = string.getMap();
		return this;
	}

	/**
	 * ������Ҫ��ʽ�����ı�Map����
	 * 
	 * @param string
	 */
	public Format setString(Map<String, String> string) {
		this.string = string;
		return this;
	}

	/**
	 * ��ȡ��ʽ���ұ���Χ���ı�
	 * 
	 * @return
	 */
	public String getStringparcel() {
		String string = getString();
		String s1 = "";
		for (int i = 0; i < ((MaxLength / getLength(Across)) - (getLength(Across) - 1)) + 2; i++)
			s1 += Across;
		String[] strings = string.split("\n");
		String ss = "";
		for (String s2 : strings)
			ss += (ss.isEmpty() ? "" : "\n") + Vertical + s2 + Vertical;
		return s1 + "\n" + ss + "\n" + s1;
	}

	/**
	 * ��ȡ��ʽ������ı�
	 * 
	 * @return
	 */
	public String getString() {
		String s = "", s3, s4;
		int spacing, JJLength;
		for (String s2 : string.keySet()) {
			JJLength = getLength(s2) + Value + getLength(string.get(s2));
			if (JJLength > MaxLength)
				setMaxLength(JJLength);
		}
		for (String s2 : string.keySet()) {
			s4 = "";
			s3 = string.get(s2);
			spacing = MaxLength - (getLength(s2) + getLength(s3));
			spacing = (spacing < Value ? Value : spacing);
			double s5 = spacing % getLength(Padding);
			for (int i = 0; i < (spacing / getLength(Padding)); i++)
				s4 += Padding;
			s += (s.isEmpty() ? "" : "\n") + s2 + s4 + (s5 != 0 ? " " : "") + s3;
		}
		myData = null;
		return s;
	}

	/**
	 * ��ȡ�ı����򳤶�
	 * 
	 * @return
	 */
	public int getMaxLength() {
		return MaxLength;
	}

	/**
	 * �����ı����򳤶�
	 * 
	 * @param maxLength
	 * @return
	 */
	public Format setMaxLength(int maxLength) {
		MaxLength = maxLength;
		return this;
	}

	/**
	 * ������Сǰ���ı���϶����
	 * 
	 * @param value
	 * @return
	 */
	public Format setValue(int value) {
		Value = value;
		return this;
	}

	private int getLength(String v) {
		int valueLength = 0;
		String chinese = "[\u4e00-\u9fa5]";
		for (int i = 0; i < v.length(); i++) {
			String temp = v.substring(i, i + 1);
			if (temp.matches(chinese)) {
				valueLength += 2;
			} else
				valueLength += 1;
		}
		return valueLength;
	}

	/**
	 * �����Ҫ��ʽ�����ı�
	 * 
	 * @param Str ǰ����ı�
	 * @param End �����ı�
	 * @return
	 */
	public static MyData put(String Str, String End) {
		myData = myData == null ? new MyData() : myData;
		return myData.put(Str, End);
	}

	public static class MyData {
		private Map<String, String> map = new HashMap<>();

		/**
		 * ��ȡ�ı����л�Map����
		 * 
		 * @return
		 */
		public Map<String, String> getMap() {
			return map;
		}

		/**
		 * �����Ҫ��ʽ�����ı�
		 * 
		 * @param Str ǰ����ı�
		 * @param End �����ı�
		 * @return
		 */
		public MyData put(String Str, String End) {
			map.put(Str, End);
			return this;
		}

		/**
		 * ��ȡ��ʽ������ı�
		 * 
		 * @return
		 */
		public String getStrng() {
			return new Format(map).getString();
		}

		/**
		 * ��ȡ��ʽ���ұ���Χ���ı�
		 * 
		 * @return
		 */
		public String getStringparcel() {
			return new Format(map).getStringparcel();
		}
	}
}
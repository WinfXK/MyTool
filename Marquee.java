package cn.winfxk.acaterina.tool;

/**
 * 字符跑马灯
 * 
 * @Createdate 2020/07/07 22:46:13
 * @author Winfxk
 */
public class Marquee {
	private String string;
	private String Color = "";

	public Marquee(String string) {
		this.string = string;
		while (Color.length() < string.length())
			Color += Tool.getRandString("123456789abcdef");
	}

	public String getString() {
		String s = "";
		int length = string.length() - 1;
		for (int i = 0; i < length; i++)
			s += "§" + Color.substring(i, i + 1) + string.substring(i, i + 1);
		Color = Tool.getRandString("123456789abcdef") + Color.substring(0, Color.length() - 1);
		while (Color.length() < string.length())
			Color += Tool.getRandString("123456789abcdef");
		return s;
	}
}

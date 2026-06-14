package prismatic.shards.stellarity.util;

public interface ColorUtil {
	/**
	 * i dont do java doc usually but im sure someone is gonna use this wrong
	 * <a href="https://www.rapidtables.com/convert/color/hsv-to-rgb.html">the formula</a>
	 *
	 * @param h will be modulo 360
	 * @param s 0-1
	 * @param v 0-1
	 * @return rgb encoded into int
	 */
	static int hsvToRgb(float h, float s, float v) {
		h %= 360;
		float c = v * s;
		float x = c * (1 - Math.abs((h / 60) % 2 - 1));
		float m = v - c;
		float rp = 0, gp = 0, bp = 0;
		if (h < 60) {
			rp = c;
			gp = x;
		} else if (h < 120) {
			rp = x;
			gp = c;
		} else if (h < 180) {
			gp = c;
			bp = x;
		} else if (h < 240) {
			gp = x;
			bp = c;
		} else if (h < 300) {
			rp = x;
			bp = c;
		} else {
			rp = c;
			bp = x;
		}

		return joinFloatRgb(rp + m, gp + m, bp + m);
	}

	static int joinFloatRgb(float r, float g, float b) {
		return joinRgb(Math.round(r * 255), Math.round(g * 255), Math.round(b * 255));
	}


	static int joinRgb(int r, int g, int b) {
		return r << 16 | g << 8 | b;
	}

}

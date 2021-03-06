package me.retrodaredevil.solarthing.program;

import java.net.URISyntaxException;

public final class JarUtil {
	private JarUtil() { throw new UnsupportedOperationException(); }

	public static String getJarFileName() {
		final String path;
		try {
			path = JarUtil.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		String[] split = path.split("/");
		return split[split.length - 1];
	}
}

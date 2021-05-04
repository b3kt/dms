package id.alinea.dms.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;

import com.google.common.collect.Lists;

public class SpringUtils {

	private static ApplicationContext context;

	private SpringUtils() {
	}

	public static void setApplicationContext(ApplicationContext context) {
		if (SpringUtils.context == null) {
			SpringUtils.context = context;
		}
	}

	/**
	 * Looks up a bean with a given name.
	 * 
	 * @param beanName
	 * @return
	 */
	public static synchronized Object lookupBean(final String beanName) {
		ApplicationContext applicationContext = SpringUtils.context;

		if (applicationContext != null) {
			return applicationContext.containsBean(beanName) ? applicationContext.getBean(beanName) : null;
		}

		return null;
	}

	/**
	 * Looks up a bean with a given name and type.
	 * 
	 * @param beanName
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static synchronized <T> T lookupBean(final String beanName, final Class<T> clazz) {
		ApplicationContext applicationContext = SpringUtils.context;

		if (applicationContext != null) {
			return applicationContext.containsBean(beanName) ? (T) applicationContext.getBean(beanName) : null;
		}

		return null;
	}

	/**
	 * Looks up a bean with a given type. Only returns the first bean that is of the
	 * type.
	 * 
	 * @param clazz
	 * @return
	 */
	public static synchronized <T> T lookupBean(final Class<T> clazz) {
		ApplicationContext applicationContext = SpringUtils.context;

		if (applicationContext != null) {
			Map<String, T> map = applicationContext.getBeansOfType(clazz);
			Iterator<Entry<String, T>> iterator = map.entrySet().iterator();

			return iterator.hasNext() ? (T) iterator.next().getValue() : null;
		}

		return null;
	}

	/**
	 * Looks up for all beans of a given type.
	 * 
	 * @param clazz
	 * @return
	 */
	public static synchronized <T> List<T> lookupBeans(final Class<T> clazz) {
		ApplicationContext applicationContext = SpringUtils.context;

		if (applicationContext != null) {
			Map<String, T> map = applicationContext.getBeansOfType(clazz);
			return Lists.newArrayList(map.values());
		}

		return null;
	}

}
